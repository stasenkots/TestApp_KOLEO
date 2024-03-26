package com.example.testapp_koleo.localstorage.di

import android.content.Context
import androidx.room.Room
import com.example.testapp_koleo.localstorage.database.StationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context,
    ): StationDatabase {
        return Room.databaseBuilder(
            applicationContext,
            StationDatabase::class.java, "station"
        ).build()
    }

}