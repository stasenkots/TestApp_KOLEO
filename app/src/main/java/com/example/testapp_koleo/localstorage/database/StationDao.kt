package com.example.testapp_koleo.localstorage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword
import com.example.testapp_koleo.localstorage.StationsLocalDataSource
import com.example.testapp_koleo.localstorage.database.mapper.StationEntityMapper
import com.example.testapp_koleo.localstorage.database.mapper.StationKeywordEntityMapper
import javax.inject.Inject

@Dao
interface StationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStations(stations: List<StationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStationKeywords(stationKeywords: List<StationKeywordEntity>)

    @Query("SELECT * FROM stations")
    fun getStations(): List<StationEntity>

    @Query("SELECT station_keywords.id, keyword, stationId FROM station_keywords " +
            "INNER JOIN stations ON station_keywords.stationId = stations.id " +
            "ORDER BY hits DESC")
    fun getStationKeywords(): List<StationKeywordEntity>
}


class StationsLocalDataSourceImpl @Inject constructor(
    stationDatabase: StationDatabase,
    private val stationEntityMapper: StationEntityMapper,
    private val stationKeywordEntityMapper: StationKeywordEntityMapper
) : StationsLocalDataSource {

    private val stationsDao = stationDatabase.stationDao()

    override suspend fun insertStations(stations: List<Station>) {
        val stationsEntity = stations.map { stationEntityMapper.map(it) }
        stationsDao.insertStations(stationsEntity)
    }

    override suspend fun insertStationKeywords(stationKeywords: List<StationKeyword>) {
        val stationKeywordsEntity = stationKeywords.map { stationKeywordEntityMapper.map(it) }
        stationsDao.insertStationKeywords(stationKeywordsEntity)
    }

    override suspend fun getStations(): List<StationEntity> {
        return stationsDao.getStations()
    }

    override suspend fun getStationKeywords(): List<StationKeywordEntity> {
        return stationsDao.getStationKeywords()
    }
}