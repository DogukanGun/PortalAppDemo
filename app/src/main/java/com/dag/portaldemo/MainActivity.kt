package com.dag.portaldemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dag.portaldemo.features.wallet.create.CreateWallet
import com.dag.portaldemo.navigation.NavGraph
import com.dag.portaldemo.ui.theme.PortalDemoTheme
import io.portalhq.android.Portal
import io.portalhq.android.provider.data.EthSigningRequest
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject

class MainActivity : AppCompatActivity() {

    private val portal: Portal by inject()

    private fun addCustomSignature() {
        portal.provider.on("portal_signingRequested") { data: Any? ->
            val request = data as EthSigningRequest

            val isMfaApproved = false

            // Approve transaction request if MFA was successful
            if (isMfaApproved) {
                portal.provider.emit("portal_signingApproved", request)
            } else {
                portal.provider.emit("portal_signingRejected", request)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortalDemoTheme {
                addCustomSignature()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}
