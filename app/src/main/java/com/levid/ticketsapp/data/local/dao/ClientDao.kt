package com.levid.ticketsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.levid.ticketsapp.data.local.entities.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(client: Client)

    @Query("SELECT * FROM Clients")
    fun getAll(): Flow<List<Client>>
}