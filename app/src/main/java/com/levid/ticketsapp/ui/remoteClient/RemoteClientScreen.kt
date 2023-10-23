package com.levid.ticketsapp.ui.remoteClient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.levid.ticketsapp.data.remote.dto.ClientDto


@Composable
fun RemoteClientScreen(
    viewModel: RemoteClientViewModel = hiltViewModel()
) {
    val clientsList = viewModel.clientsList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ClientsList(clientList = clientsList)
    }
}

@Composable
fun ClientsList(clientList: List<ClientDto>){
    LazyColumn{
        items(clientList){ client ->
            Text(text = "Id: ${client.clientId}")
            Text(text = "Nombre: ${client.name}")
        }
    }

}