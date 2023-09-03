package com.levid.ticketsapp.ui.ticket

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

@Composable
fun ticketScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        nameTextField()
        saveButton()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun nameTextField(){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nombres") },
        singleLine = true,
        value = "",
        onValueChange = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun saveButton(){
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
        Text(text = "Guardar")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTicketScreen(){
    ticketScreen()
}