package com.levid.ticketsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.levid.ticketsapp.data.local.dao.ClientDao
import com.levid.ticketsapp.data.local.entities.Client

@Database(
    entities = [Client::class],
    version = 1
)
abstract class AppDb: RoomDatabase() {
    abstract fun clientDao(): ClientDao
}