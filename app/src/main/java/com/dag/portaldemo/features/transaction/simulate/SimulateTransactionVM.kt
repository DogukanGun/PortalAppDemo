package com.dag.portaldemo.features.transaction.simulate

import androidx.lifecycle.viewModelScope
import com.dag.portaldemo.BaseVM
import io.portalhq.android.Portal
import io.portalhq.android.api.data.EvaluateTransactionOperationType
import io.portalhq.android.api.data.EvaluateTransactionParam
import kotlinx.coroutines.launch

class SimulateTransactionVM(private val portal: Portal) : BaseVM<Result<String>>() {

    fun simulate(
        to: String,
        value: String
    ) {
        viewModelScope.launch {
            val evaluateTransactionParam = EvaluateTransactionParam(
                to = to, // The recipient address.
                value = value, // The value to be sent in Wei.
                data = null, // Data for the transaction (for contract interactions).
                maxFeePerGas = null, // Maximum fee per gas.
                maxPriorityFeePerGas = null, // Maximum priority fee per gas.
                gas = null, // The gas limit.
                gasPrice = null, // Gas price in Wei.
            )

            val result = portal.api.evaluateTransaction(
                chainId = "eip155:11155111",
                operationType = EvaluateTransactionOperationType.SIMULATION,
                transaction = evaluateTransactionParam
            )


            println(result)
            _viewState.value = if (result.isFailure)
                Result.failure(Exception("error: ${result.exceptionOrNull()?.message}"))
            else
                Result.success("")
        }

    }
}