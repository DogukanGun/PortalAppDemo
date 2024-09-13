package com.dag.portaldemo.features.wallet.backup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dag.portaldemo.features.wallet.create.CreateWalletVS
import io.portalhq.android.Portal
import io.portalhq.android.mpc.data.BackupMethods
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BackupVM(private val portal: Portal) : ViewModel() {

    private var _viewState: MutableStateFlow<String> =
        MutableStateFlow("")
    val viewState: StateFlow<String> = _viewState.asStateFlow()
    fun backupWallet(){
        viewModelScope.launch {
            val cipherText = portal.backupWallet() { status ->
                // Do something with the status, such as update a progress bar
                // or log the progress
                Log.println(Log.INFO, "[PortalEx]", "Backup status: ${status.status} is done: ${status.done}")
            }
            _viewState.value = cipherText
            portal.api.storedClientBackupShare(true, BackupMethods.Gdrive)
        }
    }
}