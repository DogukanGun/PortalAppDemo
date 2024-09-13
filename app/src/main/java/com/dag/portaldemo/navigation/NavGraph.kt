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
import com.dag.portaldemo.features.wallet.backup.Backup
import com.dag.portaldemo.features.wallet.create.CreateWallet

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
            }
        }
    }
}
