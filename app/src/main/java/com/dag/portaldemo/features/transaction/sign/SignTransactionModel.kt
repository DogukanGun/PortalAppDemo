package com.dag.portaldemo.features.transaction.sign

data class TransactionParams(
    val to:String,
    val from:String,
    val gas:String,
    val value:String,
    val address:String?
)