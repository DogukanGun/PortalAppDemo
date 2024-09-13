package com.dag.portaldemo.navigation

sealed class NavScreen(val route: String) {
    data object CreateWallet : NavScreen("create_wallet")
    data object BackupWallet : NavScreen("backup_wallet")
    data object Entry : NavScreen("entry")

    companion object {
        val wallets = listOf(CreateWallet, BackupWallet)
        val transactions = emptyList<NavScreen>()
    }
}
