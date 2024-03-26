package com.example.testapp_koleo.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Station(
    @SerialName("city")
    var city: String = "",
    @SerialName("country")
    var country: String = "",
    @SerialName("has_announcements")
    var hasAnnouncements: Boolean = false,
    @SerialName("hits")
    var hits: Int = 0,
    @SerialName("ibnr")
    var ibnr: Int = 0,
    @SerialName("id")
    var id: Int = 0,
    @SerialName("is_group")
    var isGroup: Boolean = false,
    @SerialName("is_nearby_station_enabled")
    var isNearbyStationEnabled: Boolean = false,
    @SerialName("latitude")
    var latitude: Double = 0.0,
    @SerialName("localised_name")
    var localisedName: String? = null,
    @SerialName("longitude")
    var longitude: Double = 0.0,
    @SerialName("name")
    var name: String = "",
    @SerialName("name_slug")
    var nameSlug: String = "",
    @SerialName("region")
    var region: String = ""
)