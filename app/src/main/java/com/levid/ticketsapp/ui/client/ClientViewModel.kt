package com.levid.ticketsapp.ui.client

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levid.ticketsapp.data.local.AppDb
import com.levid.ticketsapp.data.local.entities.Client
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val appDb: AppDb
): ViewModel() {

    var name by mutableStateOf("")

    val clients: StateFlow<List<Client>> = appDb.clientDao().getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    fun saveClient(){
        viewModelScope.launch {
            val client = Client(
                name = name
            )
            if(isValid())
            {
                appDb.clientDao().save(client)
                cleanFields()
            }
        }
    }
    private fun cleanFields(){
        name = ""
    }
    private fun isValid() : Boolean{
        return name.isEmpty() || name.isBlank()
    }
}