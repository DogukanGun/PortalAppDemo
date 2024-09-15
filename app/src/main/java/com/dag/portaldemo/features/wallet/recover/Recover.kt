package com.dag.portaldemo.features.wallet.recover

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
import io.portalhq.android.mpc.data.BackupMethods
import org.koin.androidx.compose.koinViewModel


@Composable
fun Recover(
    recoverVM: RecoverVM = koinViewModel()
) {
    val state = recoverVM.viewState.collectAsState()
    val password = mutableStateOf("")
    CommonView(button = {
        TextButton(onClick = {
            recoverVM.recover(password.value, BackupMethods.Password)
        }) {
            Text(text = "Recover with Password")
        }
        TextButton(onClick = {
            recoverVM.recover(null, type = BackupMethods.Gdrive)
        }) {
            Text(text = "Recover with GDrive")
        }
        TextButton(onClick = {
            recoverVM.recover(null, type = BackupMethods.Passkey)
        }) {
            Text(text = "Recover with Passkey")
        }
    }) {

        TextField(value = password.value, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ), placeholder = {
            Text(text = "Value")
        }, onValueChange = {
            password.value = it
        })
        Text(
            text = "Recover status is ${state.value}", modifier = Modifier.padding(top = 16.dp)
        )
    }
}