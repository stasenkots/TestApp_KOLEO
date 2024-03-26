package com.example.testapp_koleo.data.di

import com.example.testapp_koleo.data.AppPreferencesRepository
import com.example.testapp_koleo.data.StationRepository
import com.example.testapp_koleo.data.impl.AppPreferencesRepositoryImpl
import com.example.testapp_koleo.data.impl.StationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAppPreferencesRepository(appPreferencesRepositoryImpl: AppPreferencesRepositoryImpl): AppPreferencesRepository

    @Binds
    fun bindStationRepository(stationRepositoryImpl: StationRepositoryImpl): StationRepository
}