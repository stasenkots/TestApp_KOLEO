package com.example.testapp_koleo.localstorage.database


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class StationEntity(
    @PrimaryKey val id: Int,
    val city: String,
    val country: String,
    val hasAnnouncements: Boolean,
    val hits: Int,
    val ibnr: Int,
    val isGroup: Boolean,
    val isNearbyStationEnabled: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val nameSlug: String,
    val region: String
)