package com.example.testapp_koleo.localstorage.di

import com.example.testapp_koleo.localstorage.StationsLocalDataSource
import com.example.testapp_koleo.localstorage.database.StationsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalStorageModule {

    @Binds
    fun bindStationsLocalDataSource(
        stationsLocalDataSourceImpl: StationsLocalDataSourceImpl
    ): StationsLocalDataSource
}