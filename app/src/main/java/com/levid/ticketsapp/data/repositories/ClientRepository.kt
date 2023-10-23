package com.levid.ticketsapp.data.repositories

import com.levid.ticketsapp.data.local.AppDb
import com.levid.ticketsapp.data.local.dao.ClientDao
import com.levid.ticketsapp.data.local.entities.Client
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val clientDao: ClientDao
) {
    suspend fun saveClient(client: Client) = clientDao.save(client)
    suspend fun deleteClient(client: Client) = clientDao.delete(client)
    fun getAll() = clientDao.getAll()
}