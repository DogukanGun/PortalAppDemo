package com.dag.portaldemo.features.external.webview

import android.app.Application
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import io.portalhq.android.components.webview.PortalWebView
import org.koin.androidx.compose.koinViewModel

@Composable
fun WebView(
    webViewVM: WebViewVM = koinViewModel()
) {

    val state = webViewVM.viewState.collectAsState()

    if (state.value != null) {
        state.value!!.view
    } else {
        TextButton(onClick = {
            webViewVM.createWebView()
        }) {
            Text("Load View")
        }
    }
}

private fun generateViewId(): Int {
    return View.generateViewId()
}