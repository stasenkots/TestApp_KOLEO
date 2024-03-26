package com.example.testapp_koleo.localstorage.database.mapper

import com.example.testapp_koleo.domain.models.StationKeyword
import com.example.testapp_koleo.localstorage.database.StationKeywordEntity
import javax.inject.Inject

class StationKeywordEntityMapper @Inject constructor() {

    fun map(stationKeyword: StationKeyword): StationKeywordEntity {
        return StationKeywordEntity(
            id = stationKeyword.id,
            keyword = stationKeyword.keyword,
            stationId = stationKeyword.stationId,
        )
    }
}