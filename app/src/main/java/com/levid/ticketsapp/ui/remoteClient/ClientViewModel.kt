package com.levid.ticketsapp.ui.remoteClient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levid.ticketsapp.data.remote.dto.ClientDto
import com.levid.ticketsapp.data.repositories.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    var birthDate by mutableStateOf("")
    var occupation by mutableStateOf("")

    init {
        viewModelScope.launch {
            val clients : List<ClientDto> = clientRepository.getRemoteClients()
        }
    }

    fun save(){
        viewModelScope.launch {
            if(isValid()){
                clientRepository.saveRemoteClient(
                    ClientDto(
                        clientId = 0,
                        name = name,
                        telephone = telephone,
                        cellphone = cellphone,
                        email = email,
                        direction = direction,
                        birthDate = birthDate,
                        occupation = occupation
                    )
                )
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
        birthDate = ""
        occupation = ""
    }
    private fun isValid() : Boolean{
        return (name.isNotBlank())
    }

}