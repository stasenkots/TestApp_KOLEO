package com.example.testapp_koleo.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StationEntity::class, StationKeywordEntity::class], version = 1)
abstract class StationDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao
}