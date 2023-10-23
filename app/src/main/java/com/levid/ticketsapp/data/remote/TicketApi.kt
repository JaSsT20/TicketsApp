package com.levid.ticketsapp.data.remote

import com.levid.ticketsapp.data.remote.dto.ClientDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketApi{
    @GET("/Client")
    suspend fun getClients():List<ClientDto>

//    @GET("/Client/{clientId}")
//    suspend fun getClientById(@Path("TicketId") ticketId: String): Response<ClientDto>

    @POST("/Client")
    suspend fun postClient(@Body ticket: ClientDto): Response<ClientDto>
}