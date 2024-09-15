package com.dag.portaldemo.features.external.api

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.dag.portaldemo.components.accordion.Accordion
import com.dag.portaldemo.components.accordion.AccordionModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApiView(
    apiVM: ApiVM = koinViewModel()
) {
    val state = apiVM.viewState.collectAsState()
    Column {
        state.value?.keys?.map { key ->
            state.value!![key]?.map { value ->
                AccordionModel.Row(value.toString(), value.toString())
            }?.let {
                AccordionModel(
                    header = key, rows = it
                )
            }

        }?.forEach {
            if (it != null) {
                Accordion(model = it) {}
            }
        }
        TextButton(onClick = {
            apiVM.getApiInformation()
        }) {
            Text("Get API Data")
        }
    }
}