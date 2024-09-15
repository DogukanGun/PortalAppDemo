package com.dag.portaldemo.features.external.webview

import androidx.lifecycle.viewModelScope
import com.dag.portaldemo.BaseVM
import io.portalhq.android.Portal
import io.portalhq.android.components.webview.PortalWebViewFragment
import kotlinx.coroutines.launch

class WebViewVM(val portal:Portal): BaseVM<PortalWebViewFragment>() {

    fun createWebView() {
        viewModelScope.launch {
            val url = "https://app.uniswap.org"
            val wallets = portal.createWallets{}
            if (wallets.isSuccess){
                _viewState.value = portal.createWebView(url)
            }
        }


    }
}