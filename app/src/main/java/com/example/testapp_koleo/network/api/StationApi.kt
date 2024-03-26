package com.example.testapp_koleo.network.api

import com.example.testapp_koleo.network.StationsRemoteDataSource
import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

private interface StationApi {

    @GET("stations")
    suspend fun getStations(): List<Station>

    @GET("station_keywords")
    suspend fun getStationKeywords(): List<StationKeyword>
}

class StationsRemoteDataSourceImpl @Inject constructor(retrofit: Retrofit): StationsRemoteDataSource {

    private val stationApi = retrofit.create(StationApi::class.java)

    override suspend fun getStations() = stationApi.getStations()

    override suspend fun getStationKeywords() = stationApi.getStationKeywords()
}