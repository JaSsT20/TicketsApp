package com.levid.ticketsapp.data.repositories

import com.levid.ticketsapp.data.local.AppDb
import com.levid.ticketsapp.data.local.entities.Client
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val appDb: AppDb
) {
    suspend fun saveClient(client: Client) = appDb.clientDao().save(client)
    suspend fun deleteClient(client: Client) = appDb.clientDao().delete(client)
    fun getAll() = appDb.clientDao().getAll()
}