package com.example.testapp_koleo.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationKeyword(
    @SerialName("id")
    var id: Int = 0,
    @SerialName("keyword")
    var keyword: String = "",
    @SerialName("station_id")
    var stationId: Int = 0,
)