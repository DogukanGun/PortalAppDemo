package com.dag.portaldemo.features.wallet.recover

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.portalhq.android.Portal
import io.portalhq.android.mpc.data.BackupConfigs
import io.portalhq.android.mpc.data.BackupMethods
import io.portalhq.android.mpc.data.MpcStatus
import io.portalhq.android.mpc.data.PasswordStorageConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecoverVM(private val portal: Portal) : ViewModel() {

    private var _viewState: MutableStateFlow<String> =
        MutableStateFlow("")
    val viewState: StateFlow<String> = _viewState.asStateFlow()

    fun recover(password: String?, type: BackupMethods) {
        viewModelScope.launch {
            when (type) {
                BackupMethods.Password -> {
                    password?.let {
                        recoverWithPassword(it)
                    }
                }

                BackupMethods.Gdrive -> {
                    recoverWithGDrive()
                }

                BackupMethods.Passkey -> {
                    recoverWithPasskey()
                }

                else -> {
                    throw Exception("Unknown Type for Recover")
                }
            }
        }
    }

    // With PasskeyBackups
    private suspend fun recoverWithPasskey() {
        portal.recoverWallet(backupMethod = BackupMethods.Passkey) { status ->
            // (Optional) Get status updates on the recovery operation
            printRecoverStatus(status)
        }
    }

    // With GDrive backup
    private suspend fun recoverWithGDrive() {
        portal.recoverWallet(backupMethod = BackupMethods.Gdrive) { status ->
            // (Optional) Get status updates on the recovery operation
            printRecoverStatus(status)
        }
    }

    // With Password backup
    private suspend fun recoverWithPassword(password: String) {
        val backupConfigs = BackupConfigs(PasswordStorageConfig(password = password))
        portal.recoverWallet(
            backupMethod = BackupMethods.Password,
            backupConfigs = backupConfigs
        ) { status ->
            // (Optional) Get status updates on the recovery operation
            printRecoverStatus(status)
        }
    }

    private fun printRecoverStatus(status: MpcStatus) {
        Log.d("[PortalEx]", "Recover status: $status")
        _viewState.value = status.toString()
    }

}