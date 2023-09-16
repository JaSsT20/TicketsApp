package com.levid.ticketsapp.ui.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.levid.ticketsapp.data.local.entities.Client
import com.levid.ticketsapp.navigation.Destination

@Composable
fun ClientsListScreen(
    viewModel: ClientViewModel = hiltViewModel(),
    navController: NavController
){
    val clients by viewModel.clients.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ShowList(viewModel = viewModel, clients = clients )
        RegisterButton(navController)
    }

}
@Composable
fun ShowList(viewModel: ClientViewModel, clients: List<Client>) {
    Text(text = "Lista de Clientes", style = MaterialTheme.typography.titleMedium)
    if(clients.isEmpty()){
        Text(text = "No hay clientes registrados.")
    }else{
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(clients) { client ->
                ItemContainer(viewModel = viewModel, client = client)
            }
        }
    }
}
@Composable
fun ItemContainer(viewModel: ClientViewModel, client: Client) {
    Surface(
        modifier = Modifier.padding(16.dp),
        color = Color(0xFFF8F8F8),
        border = BorderStroke(1.dp, color = Color(0xFF9C27B0))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Id: ${client.clientId}")
            Text("Nombre: ${client.name} [${client.occupation}]")
            Text("Fecha de nacimiento: ${client.birthDate}")
            Text(text = "Email: ${client.email}")
            DeleteButton(viewModel, client)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeleteButton(viewModel: ClientViewModel, client: Client) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val show = remember { mutableStateOf(false) }
    val displayDialog = DeleteConfirmDialog(viewModel = viewModel, client = client, show = show)
    OutlinedButton(
        onClick = {
            show.value = true
            keyboardController?.hide()
            displayDialog
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Eliminar",
            tint = Color(0xFFFF8585)
        )
        Text(text = "Eliminar")
    }
}

@Composable
fun RegisterButton(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(route = Destination.ClientScreen.route) }
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Register button"
            )
        }
    }
}
@Composable
fun DeleteConfirmDialog(viewModel: ClientViewModel, client: Client, show: MutableState<Boolean>){
    if(show.value){
        AlertDialog(
            onDismissRequest = { show.value = false },
            title = {Text(text= "Está apunto de eliminar un cliente")},
            text = { Text(text = "¿Está seguro que desea eliminar este cliente?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteClient(client)
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