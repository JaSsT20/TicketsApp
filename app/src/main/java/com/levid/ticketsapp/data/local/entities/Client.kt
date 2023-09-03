package com.levid.ticketsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clients")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val clientId: Int? = null,
    val nombre: String = ""
)