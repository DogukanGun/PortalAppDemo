package com.dag.portaldemo.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dag.portaldemo.components.appbar.CustomAppbar
import com.dag.portaldemo.features.entry.EntryScreen
import com.dag.portaldemo.features.external.api.ApiView
import com.dag.portaldemo.features.external.webview.WebView
import com.dag.portaldemo.features.transaction.sign.SignTransaction
import com.dag.portaldemo.features.transaction.simulate.SimulateTransaction
import com.dag.portaldemo.features.wallet.backup.Backup
import com.dag.portaldemo.features.wallet.create.CreateWallet
import com.dag.portaldemo.features.wallet.recover.Recover

@Composable
fun NavGraph(
    startDestination: String = NavScreen.Entry.route,
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { CustomAppbar() },
        bottomBar = { },
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(NavScreen.CreateWallet.route) {
                    CreateWallet()
                }
                composable(NavScreen.Entry.route) {
                    EntryScreen(
                        navController = navController
                    )
                }
                composable(NavScreen.BackupWallet.route) {
                    Backup()
                }
                composable(NavScreen.SignTransaction.route) {
                    SignTransaction()
                }
                composable(NavScreen.SimulateTransaction.route) {
                    SimulateTransaction()
                }
                composable(NavScreen.RecoverScreen.route) {
                    Recover()
                }
                composable(NavScreen.ApiScreen.route) {
                    ApiView()
                }
                composable(NavScreen.WebView.route) {
                    WebView()
                }
            }
        }
    }
}
