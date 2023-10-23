package com.levid.ticketsapp.data.remote

import com.levid.ticketsapp.data.remote.dto.ClientDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TicketsApi {
    @GET("/api/Client")
    suspend fun getClients(): List<ClientDto>

    @POST("/api/Client")
    suspend fun postClient(@Body clientDto: ClientDto): Response<ClientDto>
}