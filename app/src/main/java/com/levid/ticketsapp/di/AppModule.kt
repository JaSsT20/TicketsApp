package com.levid.ticketsapp.di

import android.content.Context
import androidx.room.Room
import com.levid.ticketsapp.data.local.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appDbContext: Context): AppDb =
        Room.databaseBuilder(
            appDbContext,
            AppDb::class.java,
            "AppDb.db")
            .fallbackToDestructiveMigration()
            .build()
}