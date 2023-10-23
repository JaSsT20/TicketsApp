package com.levid.ticketsapp.data.repositories

import com.levid.ticketsapp.data.local.dao.ClientDao
import com.levid.ticketsapp.data.local.entities.Client
import com.levid.ticketsapp.data.remote.TicketsApi
import com.levid.ticketsapp.data.remote.dto.ClientDto
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val clientDao: ClientDao,
    private val ticketsApi: TicketsApi
) {
    suspend fun saveClient(client: Client) = clientDao.save(client)
    suspend fun deleteClient(client: Client) = clientDao.delete(client)
    fun getAll() = clientDao.getAll()

    suspend fun getRemoteClients() = ticketsApi.getClients()
    suspend fun saveRemoteClient(clientDto: ClientDto) = ticketsApi.postClient(clientDto)
}