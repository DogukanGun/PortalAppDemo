package com.dag.portaldemo.di

import com.dag.portaldemo.BaseVM
import com.dag.portaldemo.BuildConfig
import com.dag.portaldemo.features.external.api.ApiVM
import com.dag.portaldemo.features.external.webview.WebView
import com.dag.portaldemo.features.external.webview.WebViewVM
import com.dag.portaldemo.features.transaction.sign.SignTransaction
import com.dag.portaldemo.features.transaction.sign.SignTransactionVM
import com.dag.portaldemo.features.transaction.simulate.SimulateTransaction
import com.dag.portaldemo.features.transaction.simulate.SimulateTransactionVM
import com.dag.portaldemo.features.wallet.backup.BackupVM
import com.dag.portaldemo.features.wallet.create.CreateWalletVM
import com.dag.portaldemo.features.wallet.recover.RecoverVM
import io.portalhq.android.Portal
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Portal> {
        val portal = Portal(
            // A Portal client API key. You can obtain one from Portal's REST API.
            apiKey = BuildConfig.API_KEY,
            // A map of chainIDs to Gateway URLs (e.g. Infura, Alchemy, etc.)
            rpcConfig = mapOf("eip155:11155111" to BuildConfig.SEPOLIA_ENDPOINT),
            // A boolean to auto-approve transactions.
            autoApprove = true,
            // Provide Eth chain id here if you are upgrading from V3 to V4. This is needed for some legacy code
            legacyEthChainId = 11155111
        )

        portal.configureGoogleStorage(
            clientId = BuildConfig.GOOGLE_DRIVE_KEY, // Your Google client id with GDrive access
            signOutAfterUse = true // Signout Google after backup/recover operation
        )
        portal
    }
}

val viewModelModule = module {
    viewModel { CreateWalletVM(get()) }
    viewModel { BackupVM(get()) }
    viewModel { SignTransactionVM(get()) }
    viewModel { SimulateTransactionVM(get()) }
    viewModel { RecoverVM(get()) }
    viewModel { ApiVM(get()) }
    viewModel { WebViewVM(get()) }

}
