package com.levid.ticketsapp.di

import android.content.Context
import androidx.room.Room
import com.levid.ticketsapp.data.local.AppDb
import com.levid.ticketsapp.data.remote.TicketApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    @Provides
    fun providesClientDao(db: AppDb) = db.clientDao()
    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTicketApi(moshi: Moshi, client: OkHttpClient): TicketApi {
        return Retrofit.Builder()
            .baseUrl("https://ticketsapilevid.azurewebsites.net/api/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketApi::class.java)
    }
}