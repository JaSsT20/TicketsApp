package com.levid.ticketsapp.ui.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ClientScreen(
    viewModel: ClientViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NameTextField(viewModel)
        SaveButton(viewModel)
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

@Preview(showSystemUi = true)
@Composable
fun PreviewClientScreen(){
    ClientScreen()
}