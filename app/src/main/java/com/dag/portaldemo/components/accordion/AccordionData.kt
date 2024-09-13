package com.dag.portaldemo.components.accordion
data class AccordionModel(
    val header: String,
    val rows: List<Row>
) {
    data class Row(
        val value: String,
        val text: String
    )
}