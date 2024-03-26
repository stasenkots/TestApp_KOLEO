package com.example.testapp_koleo.data

import androidx.paging.PagingData
import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    suspend fun updateStations()

    suspend fun getStations(): List<Station>

    suspend fun getStationKeywords(): List<StationKeyword>
}