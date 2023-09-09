package com.levid.ticketsapp.ui.client

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levid.ticketsapp.data.local.entities.Client
import com.levid.ticketsapp.data.repositories.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val clientRepository: ClientRepository
): ViewModel() {

    var name by mutableStateOf("")
    var telephone by mutableStateOf("")
    var cellphone by mutableStateOf("")
    var email by mutableStateOf("")
    var direction by mutableStateOf("")
    var birthDate by mutableStateOf(Date())
    var occupation by mutableStateOf("")

    val clients: StateFlow<List<Client>> = clientRepository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    fun saveClient(){
        viewModelScope.launch {
            val client = Client(
                name = name,
                telephone = telephone,
                cellphone = cellphone,
                email = email,
                direction = direction,
                birthDate = birthDate,
                occupation = occupation
            )
            if(isValid())
            {
                clientRepository.saveClient(client)
                cleanFields()
            }
        }
    }
    private fun cleanFields(){
        name = ""
        telephone = ""
        cellphone = ""
        email = ""
        direction = ""
        birthDate = Date()
        occupation = ""
    }
    private fun isValid() : Boolean{
        return (name.isNotBlank())
    }
}