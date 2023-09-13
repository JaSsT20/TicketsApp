package com.levid.ticketsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Clients")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val clientId: Int? = null,
    val name: String = "",
    val telephone: String = "",
    val cellphone: String = "",
    val email: String = "",
    val direction: String = "",
    val birthDate: Date?,
    val occupation: String = ""
)