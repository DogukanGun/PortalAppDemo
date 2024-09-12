package com.dag.portaldemo.di

import com.dag.portaldemo.BuildConfig
import com.dag.portaldemo.features.wallet.create.CreateWalletVM
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
}
