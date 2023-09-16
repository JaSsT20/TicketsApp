package com.levid.ticketsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.levid.ticketsapp.data.local.dao.ClientDao
import com.levid.ticketsapp.data.local.entities.Client
import com.levid.ticketsapp.util.typeConverter

@TypeConverters(value = [typeConverter::class])
@Database(
    entities = [Client::class],
    version = 3,
    exportSchema = false
)
abstract class AppDb: RoomDatabase() {
    abstract fun clientDao(): ClientDao
}