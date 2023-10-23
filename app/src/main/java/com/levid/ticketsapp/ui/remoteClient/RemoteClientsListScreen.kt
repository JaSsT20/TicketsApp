@file:OptIn(ExperimentalComposeUiApi::class)

package com.levid.ticketsapp.ui.remoteClient

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.levid.ticketsapp.data.local.entities.Client
import com.levid.ticketsapp.data.remote.dto.ClientDto
import com.levid.ticketsapp.navigation.Destination
import com.levid.ticketsapp.ui.client.ClientViewModel
import com.levid.ticketsapp.ui.client.DeleteConfirmDialog

@Composable
fun RemoteClientsListScreen(
    viewModel: RemoteClientViewModel = hiltViewModel()
) {
    val clients = viewModel.clientsList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RemoteShowList(viewModel = viewModel, clients = clients )
    }
}
@Composable
fun RemoteShowList(viewModel: RemoteClientViewModel, clients: List<ClientDto>) {
    Text(text = "Lista de Clientes", style = MaterialTheme.typography.titleMedium)
    if(clients.isEmpty()){
        Text(text = "No hay clientes registrados.")
    }else{
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(clients) { client ->
                RemoteItemContainer(viewModel = viewModel, clientDto = client)
            }
        }
    }
}

@Composable
fun RemoteItemContainer(viewModel: RemoteClientViewModel, clientDto: ClientDto) {
    Surface(
        modifier = Modifier.padding(16.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Id: ${clientDto.clientId}")
            Text("Nombre: ${clientDto.name} [${clientDto.occupation}]")
            Text("Fecha de nacimiento: ${clientDto.birthDate}")
            Text(text = "Email: ${clientDto.email}")
            RemoteDeleteButton(viewModel, clientDto)
        }
    }
}

@Composable
fun RemoteDeleteButton(viewModel: RemoteClientViewModel, clientDto: ClientDto) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val show = remember { mutableStateOf(false) }
    val displayDialog = RemoteDeleteConfirmDialog(viewModel = viewModel, clientDto = clientDto, show = show)
    OutlinedButton(
        onClick = {
            show.value = true
            keyboardController?.hide()
            displayDialog
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Eliminar",
            tint = MaterialTheme.colorScheme.error
        )
        Text(text = "Eliminar", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun RemoteDeleteConfirmDialog(viewModel: RemoteClientViewModel, clientDto: ClientDto, show: MutableState<Boolean>){
    if(show.value){
        AlertDialog(
            onDismissRequest = { show.value = false },
            title = {Text(text= "Está apunto de eliminar un cliente")},
            text = { Text(text = "¿Está seguro que desea eliminar este cliente?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        //viewModel.deleteClient(client)
                        show.value = false
                    },

                    ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "DeleteConfirm" )
                    Text(text = "Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        show.value = false
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "DeleteConfirm" )
                    Text(text = "Cancelar")
                }
            }
        )
    }
}