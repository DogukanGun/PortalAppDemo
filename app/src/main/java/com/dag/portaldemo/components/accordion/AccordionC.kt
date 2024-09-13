package com.dag.portaldemo.components.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun AccordionHeader(
    title: String = "Header",
    isExpanded: Boolean = false,
    onTapped: () -> Unit = {}
) {
    val degrees = if (isExpanded) 180f else 0f

    Surface(
        color = White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Gray),
        tonalElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .clickable { onTapped() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, Modifier.weight(1f), color = Black)
            Surface(shape = CircleShape, color = Gray.copy(alpha = 0.6f)) {
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    contentDescription = "arrow-down",
                    modifier = Modifier.rotate(degrees),
                    tint = Black
                )
            }
        }
    }
}

@Composable
private fun AccordionRow(
    model: AccordionModel.Row,
    onClick: (String)->Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick(model.value)
            }
    ) {
        Text(model.text, Modifier.weight(1f), color = Black)
    }
}

@Composable
fun Accordion(
    modifier: Modifier = Modifier,
    model: AccordionModel,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AccordionHeader(title = model.header, isExpanded = expanded) {
            expanded = !expanded
        }
        AnimatedVisibility(visible = expanded) {
            Surface(
                color = White,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Gray),
                tonalElevation = 1.dp,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                LazyColumn {
                    items(model.rows) { row ->
                        AccordionRow(row,onClick = onClick)
                        HorizontalDivider(thickness = 1.dp, color = Gray)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun AccordionHeaderPreview() {
    AccordionHeader()
}

@Composable
@Preview
fun AccordionGroupPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        listOf(
            AccordionModel(
                header = "Wallet", rows = listOf(
                    AccordionModel.Row("create", "Create Wallet"),
                    AccordionModel.Row("backup", "Backup Wallet")
                )
            ),
            AccordionModel(
                header = "Transaction", rows = listOf(
                    AccordionModel.Row("stransaction", "Send Transaction"),
                )
            )
        ).forEach {
            Accordion(model = it){}
        }
    }
}