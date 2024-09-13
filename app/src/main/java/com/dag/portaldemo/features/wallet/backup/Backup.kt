package com.dag.portaldemo.features.wallet.backup

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.dag.portaldemo.features.wallet.CommonView
import com.dag.portaldemo.features.wallet.create.AddressBox
import com.dag.portaldemo.features.wallet.create.CreateWalletVM
import org.koin.androidx.compose.koinViewModel

@Composable
fun Backup(
    backupVM: BackupVM = koinViewModel()
){
    val state = backupVM.viewState.collectAsState()
    CommonView(button = {
        TextButton(onClick = {
            backupVM.backupWallet()
        }) {
            Text("Backup Wallet")
        }
    }) {
        AddressBox(label = "CipherText", value = state.value)
    }
}

@Composable
@Preview
fun BackupPreview(){
    Backup()
}