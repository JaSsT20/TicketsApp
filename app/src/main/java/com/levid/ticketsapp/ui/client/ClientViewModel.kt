package com.levid.ticketsapp.ui.client

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levid.ticketsapp.data.local.AppDb
import com.levid.ticketsapp.data.local.entities.Client
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val appDb: AppDb
): ViewModel() {

    var name by mutableStateOf("")

    fun saveClient(){
        viewModelScope.launch {
            val client = Client(
                name = name
            )
            appDb.clientDao().save(client)
        }
    }

}