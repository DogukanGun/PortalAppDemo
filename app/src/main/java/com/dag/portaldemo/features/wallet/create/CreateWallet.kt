package com.dag.portaldemo.features.wallet.create

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateWallet(
    createWalletVM: CreateWalletVM = koinViewModel()
){
    val state = createWalletVM.viewState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddressBox(label = "Ethereum", value = state.value.ethereumAddress)
        AddressBox(label = "Solana", value = state.value.solanaAddresss)
        TextButton(onClick = {
            createWalletVM.createWallet()
        }) {
            Text("Create Wallet")
        }
    }
}

@Composable
fun AddressBox(
    label:String,
    value:String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = value)
    }
}

@Composable
@Preview
fun CreateWalletPreview(){
    CreateWallet()
}