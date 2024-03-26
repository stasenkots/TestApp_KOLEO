package com.example.testapp_koleo.network

import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword

interface StationsRemoteDataSource {

    suspend fun getStations(): List<Station>

    suspend fun getStationKeywords(): List<StationKeyword>
}