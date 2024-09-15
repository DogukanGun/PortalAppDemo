package com.dag.portaldemo.navigation

import com.dag.portaldemo.features.transaction.sign.SignTransaction

sealed class NavScreen(val route: String) {
    data object CreateWallet : NavScreen("create_wallet")
    data object BackupWallet : NavScreen("backup_wallet")
    data object Entry : NavScreen("entry")
    data object SignTransaction : NavScreen("send_transaction")
    data object SimulateTransaction : NavScreen("simulate_transaction")
    data object RecoverScreen : NavScreen("recover_screen")
    data object ApiScreen: NavScreen("api_screen")
    data object WebView: NavScreen("webview_screen")

    companion object {
        val wallets = listOf(CreateWallet, BackupWallet, RecoverScreen)
        val transactions = listOf(SignTransaction, SimulateTransaction)
        val external = listOf(ApiScreen,WebView)
    }
}
