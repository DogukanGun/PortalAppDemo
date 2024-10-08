package com.dag.portaldemo.features.transaction.sign

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dag.portaldemo.BaseVM
import com.google.gson.Gson
import com.solana.core.PublicKey
import com.solana.core.Transaction
import com.solana.programs.SystemProgram
import io.portalhq.android.Portal
import io.portalhq.android.provider.data.PortalProviderRpcResponse
import io.portalhq.android.provider.data.PortalRequestMethod
import io.portalhq.android.provider.data.SolGetLatestBlockhashResult
import io.portalhq.android.storage.mobile.PortalNamespace
import io.portalhq.android.utils.PortalSolanaHeader
import io.portalhq.android.utils.PortalSolanaInstruction
import io.portalhq.android.utils.PortalSolanaMessage
import io.portalhq.android.utils.PortalSolanaRequest
import kotlinx.coroutines.launch
import java.math.BigInteger

class SignTransactionVM(private val portal: Portal) : BaseVM<String>() {

    private fun prepareSolanaRequest(
        fromAddress: String,
        toAddress: String,
        lamports: Long,
        recentBlockhash: String
    ): PortalSolanaRequest {
        val fromPublicKey = PublicKey(fromAddress)
        val toPublicKey = PublicKey(toAddress)

        val transferInstruction = SystemProgram.transfer(
            fromPublicKey,
            toPublicKey,
            lamports
        )

        Log.i("PortalSolana", "Transfer instruction: $transferInstruction")

        val transaction = Transaction()
        transaction.addInstruction(transferInstruction)
        transaction.recentBlockhash = recentBlockhash
        transaction.feePayer = fromPublicKey

        val message = transaction.compileMessage()

        Log.i("PortalSolana", "Compiled message: $message")

        val header = PortalSolanaHeader(
            numRequiredSignatures = message.header.numRequiredSignatures,
            numReadonlySignedAccounts = message.header.numReadonlySignedAccounts,
            numReadonlyUnsignedAccounts = message.header.numReadonlyUnsignedAccounts
        )

        val instructions = message.instructions.map { instruction ->
            PortalSolanaInstruction(
                instruction.programIdIndex,
                instruction.accounts,
                instruction.data
            )
        }

        val accountKeys = message.accountKeys.map { key -> key.toString() }

        return PortalSolanaRequest(
            message = PortalSolanaMessage(
                accountKeys = accountKeys,
                header = header,
                recentBlockhash = message.recentBlockhash,
                instructions = instructions
            )
        )
    }

    fun signTransactionSolana(
        fromAddress: String,
        toAddress: String,
        lamports: Long
    ) {
        viewModelScope.launch {
            val recentBlockhashRpcResponse = portal.request(
                PortalNamespace.SOLANA.value,
                PortalRequestMethod.sol_getLatestBlockhash,
                emptyList()
            ).result as PortalProviderRpcResponse
            val recentBlockhashResult = recentBlockhashRpcResponse.result
            val recentBlockhash = Gson().let {
                it.fromJson(
                    it.toJson(recentBlockhashResult),
                    SolGetLatestBlockhashResult::class.java
                )
            }

            val solanaRequest = prepareSolanaRequest(
                fromAddress,
                toAddress,
                lamports,
                recentBlockhash.value.blockhash
            )

            val transactionHash = portal.request(
                PortalNamespace.SOLANA.value,
                PortalRequestMethod.sol_signAndSendTransaction,
                listOf(solanaRequest)
            ).result as String

            _viewState.value = transactionHash
        }

    }

    fun signTransactionEthereum() {
        viewModelScope.launch {
            try {
                //Ethereum is not working
                val params = listOf(
                    TransactionParams(
                        "0x6097f22127E2EF98C2cF31335Bede16D742f6890",
                        "0xF952181fE3466D1C1b58160295772eCEe40a36c0",
                        "0x${BigInteger("1").toString(16)}",
                        "0x6000",
                        portal.getAddress(PortalNamespace.EIP155)
                    )
                )

                // Attempt to send the transaction.
                val transactionHash = portal.provider.request(
                    "eth_sendTransaction",
                    params
                )

                _viewState.value = transactionHash.toString()
            } catch (error: Error) {
                println(error)
            }
        }
    }
}