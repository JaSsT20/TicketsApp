package com.levid.ticketsapp.ui.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.levid.ticketsapp.data.local.entities.Client

@Composable
fun ClientScreen(
    viewModel: ClientViewModel = hiltViewModel()
){
    val clients by viewModel.clients.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NameTextField(viewModel)
        SaveButton(viewModel)
        ShowList(clients)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(viewModel: ClientViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nombres") },
        singleLine = true,
        value = viewModel.name,
        onValueChange = { viewModel.name = it  }
    )
}

@Composable
fun SaveButton(viewModel: ClientViewModel) {
    OutlinedButton(
        onClick = { viewModel.saveClient() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
        Text(text = "Guardar")
    }
}
@Composable
fun ShowList(clients: List<Client>) {
    Text(text = "Lista de Clientes", style = MaterialTheme.typography.titleMedium)
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(clients){ client->
            Text(text = client.name)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewClientScreen(){
    ClientScreen()
}