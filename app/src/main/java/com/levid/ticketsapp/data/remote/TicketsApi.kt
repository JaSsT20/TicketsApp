package com.levid.ticketsapp.data.remote

import com.levid.ticketsapp.data.remote.dto.ClientDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TicketsApi {
    @GET("/Client")
    suspend fun getClients(): List<ClientDto>

    @POST("/Client")
    suspend fun postClient(@Body clientDto: ClientDto): Response<ClientDto>
}