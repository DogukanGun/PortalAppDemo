package com.dag.portaldemo.features.transaction.sign

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.portaldemo.features.wallet.CommonView
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignTransaction(
    signTransactionVM: SignTransactionVM = koinViewModel()
) {
    val state = signTransactionVM.viewState.collectAsState()
    val fromAddress = mutableStateOf("")
    val toAddress = mutableStateOf("")
    val lamport = mutableStateOf("")
    CommonView(button = {
        TextButton(onClick = {
            signTransactionVM.signTransactionSolana(
                fromAddress.value,
                toAddress.value,
                lamport.value.toLong()
            )
        }) {
            Text(text = "Sign Transaction")
        }
    }) {
        TextField(
            value = fromAddress.value,
            placeholder = {
                Text(text = "From Address")
            },
            onValueChange = {
                fromAddress.value = it
            }
        )
        TextField(
            value = toAddress.value,
            placeholder = {
                Text(text = "To Address")
            },
            onValueChange = {
                toAddress.value = it
            }
        )
        TextField(
            value = lamport.value,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            placeholder = {
                Text(text = "Lamport")
            },
            onValueChange = {
                lamport.value = it
            }
        )
        Text(
            text = "Transaction Hash is ${state.value}",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}


@Composable
@Preview
fun SignTransactionPreview() {
    SignTransaction()
}