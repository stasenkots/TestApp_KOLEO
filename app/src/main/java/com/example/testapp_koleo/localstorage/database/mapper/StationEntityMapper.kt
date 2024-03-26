package com.example.testapp_koleo.localstorage.database.mapper

import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.localstorage.database.StationEntity
import javax.inject.Inject

class StationEntityMapper @Inject constructor() {

    fun map(station: Station): StationEntity {
        return  StationEntity(
            id = station.id,
            name = station.name,
            latitude = station.latitude,
            longitude = station.longitude,
            city = station.city,
            country = station.country,
            hasAnnouncements = station.hasAnnouncements,
            hits = station.hits,
            ibnr = station.ibnr,
            isGroup = station.isGroup,
            isNearbyStationEnabled = station.isNearbyStationEnabled,
            nameSlug = station.nameSlug,
            region = station.region
        )
    }
}