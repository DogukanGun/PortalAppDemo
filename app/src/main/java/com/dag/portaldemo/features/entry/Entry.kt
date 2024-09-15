package com.dag.portaldemo.features.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dag.portaldemo.components.accordion.Accordion
import com.dag.portaldemo.components.accordion.AccordionModel
import com.dag.portaldemo.navigation.NavScreen

@Composable
fun EntryScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        listOf(
            AccordionModel(
                header = "Wallet", rows = NavScreen.wallets.map {
                    AccordionModel.Row(it.route, it.toString())
                }
            ),
            AccordionModel(
                header = "Transaction", rows = NavScreen.transactions.map {
                    AccordionModel.Row(it.route, it.toString())
                }
            ),
            AccordionModel(
                header = "External", rows = NavScreen.external.map {
                    AccordionModel.Row(it.route, it.toString())
                }
            )
        ).forEach {
            Accordion(model = it) { route ->
                navController.navigate(route)
            }
        }
    }
}

@Composable
@Preview
fun EntryScreenPreview() {
    EntryScreen(
        navController = rememberNavController()
    )
}