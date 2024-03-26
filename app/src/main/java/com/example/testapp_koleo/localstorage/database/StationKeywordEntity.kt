package com.example.testapp_koleo.localstorage.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station_keywords")
class StationKeywordEntity(
    @PrimaryKey val id: Int,
    val keyword: String,
    val stationId: Int,

)