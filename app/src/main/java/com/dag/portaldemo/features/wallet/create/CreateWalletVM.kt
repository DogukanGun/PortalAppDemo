package com.dag.portaldemo.features.wallet.create

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.portalhq.android.Portal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateWalletVM(private val portal: Portal) : ViewModel() {

    private var _viewState: MutableStateFlow<CreateWalletVS> =
        MutableStateFlow(CreateWalletVS())
    val viewState: StateFlow<CreateWalletVS> = _viewState.asStateFlow()

    fun createWallet() {
        viewModelScope.launch {
            val walletAddress = portal.createWallets { status ->
                // Do something with the status, such as update a progress bar
                // or log the progress
                Log.println(
                    Log.INFO,
                    "[PORTAL]",
                    "Generate status: ${status.status} is done: ${status.done}"
                )
            }
            if (walletAddress.isSuccess) {
                val response = walletAddress.getOrThrow()
                _viewState.value = _viewState.value.copy(
                    ethereumAddress = response.ethereumAddress,
                    solanaAddresss = response.solanaAddress
                )
            }
        }

    }
}