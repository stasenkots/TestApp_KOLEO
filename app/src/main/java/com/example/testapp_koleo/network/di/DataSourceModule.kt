package com.example.testapp_koleo.network.di

import com.example.testapp_koleo.network.StationsRemoteDataSource
import com.example.testapp_koleo.network.api.StationsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindStationsRemoteDataSource(stationsRemoteDataSourceImpl: StationsRemoteDataSourceImpl): StationsRemoteDataSource
}