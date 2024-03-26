package com.example.testapp_koleo.localstorage

import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword
import com.example.testapp_koleo.localstorage.database.StationEntity
import com.example.testapp_koleo.localstorage.database.StationKeywordEntity

interface StationsLocalDataSource {

    suspend fun insertStations(stations: List<Station>)

    suspend fun insertStationKeywords(stationKeywords: List<StationKeyword>)

    suspend fun getStations(): List<StationEntity>

    suspend fun getStationKeywords(): List<StationKeywordEntity>
}