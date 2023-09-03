package com.levid.ticketsapp.data.local

import androidx.room.Database
import com.levid.ticketsapp.data.local.dao.ClientDao
import com.levid.ticketsapp.data.local.entities.Client

@Database(
    entities = [Client::class],
    version = 1
)
abstract class AppDb {
    abstract fun clientDao(): ClientDao
}