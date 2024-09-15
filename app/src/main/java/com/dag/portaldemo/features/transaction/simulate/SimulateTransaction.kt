package com.dag.portaldemo.features.transaction.simulate

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dag.portaldemo.features.wallet.CommonView
import org.koin.androidx.compose.koinViewModel

@Composable
fun SimulateTransaction(
    simulateTransactionVM: SimulateTransactionVM = koinViewModel()
) {
    val state = simulateTransactionVM.viewState.collectAsState()
    val toAddress = mutableStateOf("")
    val value = mutableStateOf("")
    CommonView(button = {
        TextButton(onClick = {
            simulateTransactionVM.simulate(
                to = if (toAddress.value.isNotEmpty()) toAddress.value
                else "0x5596D66388555273eF90163f5e7314C8CE14F73c",
                value = if (value.value.isNotEmpty()) value.value else "0x10DE4A2A"
            )
        }) {
            Text(text = "Simulate Transaction")
        }
    }) {
        TextField(value = toAddress.value, placeholder = {
            Text(text = "To Address")
        }, onValueChange = {
            toAddress.value = it
        })
        TextField(value = value.value, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ), placeholder = {
            Text(text = "Value")
        }, onValueChange = {
            value.value = it
        })
        Text(
            text = "Transaction Simulate status is ${state.value}", modifier = Modifier.padding(top = 16.dp)
        )
    }
}
