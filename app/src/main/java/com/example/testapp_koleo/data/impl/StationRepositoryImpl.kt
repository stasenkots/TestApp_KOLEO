package com.example.testapp_koleo.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.example.testapp_koleo.data.StationRepository
import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword
import com.example.testapp_koleo.localstorage.StationsLocalDataSource
import com.example.testapp_koleo.localstorage.database.StationEntity
import com.example.testapp_koleo.network.StationsRemoteDataSource
import com.example.testapp_koleo.utils.AppDispatchers
import com.example.testapp_koleo.utils.Dispatcher
import com.example.testapp_koleo.utils.toRadians
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val stationsRemoteDataSource: StationsRemoteDataSource,
    private val stationLocalDataSource: StationsLocalDataSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : StationRepository {

    override suspend fun updateStations() = withContext(ioDispatcher) {
        val stations = stationsRemoteDataSource.getStations()
        val stationKeywords = stationsRemoteDataSource.getStationKeywords()

        stationLocalDataSource.insertStations(stations)
        stationLocalDataSource.insertStationKeywords(stationKeywords)
    }

    override suspend fun getStations(): List<Station> =
        withContext(ioDispatcher) {
            stationLocalDataSource.getStations().map {
                Station(
                    id = it.id,
                    name = it.name,
                    latitude = it.latitude.toRadians(),
                    longitude = it.longitude.toRadians(),
                    city = it.city,
                    country = it.country,
                    region = it.region,
                    ibnr = it.ibnr,
                    hits = it.hits,
                    isGroup = it.isGroup,
                    isNearbyStationEnabled = it.isNearbyStationEnabled,
                    hasAnnouncements = it.hasAnnouncements,
                    nameSlug = it.nameSlug
                )
            }
        }

    override suspend fun getStationKeywords(): List<StationKeyword> = withContext(ioDispatcher) {
        stationLocalDataSource.getStationKeywords().map {
            StationKeyword(
                id = it.id,
                stationId = it.stationId,
                keyword = it.keyword,
            )
        }
    }
}