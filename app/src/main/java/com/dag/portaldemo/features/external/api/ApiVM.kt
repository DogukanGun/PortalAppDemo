package com.dag.portaldemo.features.external.api

import androidx.lifecycle.viewModelScope
import com.dag.portaldemo.BaseVM
import io.portalhq.android.Portal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiVM(private val portal: Portal): BaseVM<Map<String,List<Any>>>() {
    fun getApiInformation() {
        viewModelScope.launch(Dispatchers.IO)  {
            val results = mutableMapOf<String, List<Any>>()
            results["nfts"] = portal.api.getNFTs()
            results["balances"] = portal.api.getBalances()
            results["networks"] = portal.api.getNetworks()
            results["dapps"] = portal.api.getEnabledDapps()
            results["transactions"] = portal.api.getTransactions(limit = 10)
            viewModelScope.launch(Dispatchers.Main) {
                _viewState.value = results.toMap()
            }
        }

    }
}