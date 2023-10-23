package com.levid.ticketsapp.data.remote.dto

data class ClientDto(
    val clientId: Int,
    var name: String = "",
    var telephone: String = "",
    var cellphone: String = "",
    var email: String = "",
    var direction: String = "",
    var birthDate: String = "",
    var occupation: String = ""
)
