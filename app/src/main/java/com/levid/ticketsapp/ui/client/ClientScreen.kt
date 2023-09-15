package com.levid.ticketsapp.ui.client

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.levid.ticketsapp.data.local.entities.Client
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ClientScreen(
    viewModel: ClientViewModel = hiltViewModel(),
) {
    val clients by viewModel.clients.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Registro de personas/clientes", fontWeight = FontWeight.Bold)
        NameTextField(viewModel)
        TelephoneTextField(viewModel)
        CellphoneTextField(viewModel = viewModel)
        EmailTextField(viewModel = viewModel)
        DirectionTextField(viewModel = viewModel)
        BirthDateTextField(viewModel = viewModel)
        OccupationTextField(viewModel = viewModel)
        SaveButton(viewModel)
        ShowList(viewModel, clients)
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
        onValueChange = { viewModel.name = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelephoneTextField(viewModel: ClientViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Teléfono") },
        singleLine = true,
        value = viewModel.telephone,
        onValueChange = { viewModel.telephone = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CellphoneTextField(viewModel: ClientViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Celular") },
        singleLine = true,
        value = viewModel.cellphone,
        onValueChange = { viewModel.cellphone = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(viewModel: ClientViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email") },
        singleLine = true,
        value = viewModel.email,
        onValueChange = { viewModel.email = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectionTextField(viewModel: ClientViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Dirección") },
        singleLine = true,
        value = viewModel.direction,
        onValueChange = { viewModel.direction = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDateTextField(viewModel: ClientViewModel) {
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = (1 + calendar.get(Calendar.MONTH))
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
            viewModel.birthDate = dateFormat.parse(date.value)!!
        }, year, month, day
    )

    OutlinedTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        value = viewModel.birthDate.toString(),
        label = { Text(text = "Fecha de nacimiento") },
        singleLine = true,
        onValueChange = { date.value = it },
        placeholder = { Text(text = "<--- Push there to pick a date") },
        leadingIcon = {
            IconButton(
                onClick = { datePickerDialog.show() }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date icon",
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OccupationTextField(viewModel: ClientViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }
    val occupationList = listOf("Engineer", "Doctor", "Architect")
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Ocupación") },
        singleLine = true,
        value = viewModel.occupation,
        onValueChange = { viewModel.occupation = it },
        leadingIcon = {
            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Arrow Drop Down Icon",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    )
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        for (item in occupationList) {
            DropdownMenuItem(
                text = { Text(text = item) },
                onClick = {
                    viewModel.occupation = occupationList[selectedOption]
                    expanded = false
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SaveButton(viewModel: ClientViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedButton(
        onClick = {
            keyboardController?.hide()
            viewModel.saveClient()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
        Text(text = "Guardar")
    }
}

@Composable
fun ShowList(viewModel: ClientViewModel, clients: List<Client>) {
    Text(text = "Lista de Clientes", style = MaterialTheme.typography.titleMedium)
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(clients) { client ->
            ItemContainer(viewModel = viewModel, client = client)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeleteButton(viewModel: ClientViewModel, client: Client) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedButton(
        onClick = {
            keyboardController?.hide()
            viewModel.deleteClient(client)
            //Poner el eliminar viewModel.saveClient()
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
fun ItemContainer(viewModel: ClientViewModel, client: Client) {
    Surface(
        modifier = Modifier.padding(16.dp),
        color = Color(0xFFC4C4C4),
        border = BorderStroke(1.dp, color = Color(0xFF673AB7))
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