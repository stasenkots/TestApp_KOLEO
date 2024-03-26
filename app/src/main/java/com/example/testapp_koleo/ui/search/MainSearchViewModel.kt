package com.example.testapp_koleo.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp_koleo.data.StationRepository
import com.example.testapp_koleo.domain.models.Station
import com.example.testapp_koleo.domain.models.StationKeyword
import com.example.testapp_koleo.utils.calculateDistance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainSearchViewModel @Inject constructor(
    private val stationRepository: StationRepository,
) : ViewModel() {

    val uiState: MutableState<MainSearchUiState> = mutableStateOf(MainSearchUiState.Initial)

    val startSearchQuery = MutableStateFlow("")
    val endSearchQuery = MutableStateFlow("")
    private val stations = mutableMapOf<Int, Station>()
    private val stationKeywords = mutableMapOf<String, StationKeyword>()
    private val sortedStationKeywords = mutableListOf<StationKeyword>()
    val startStationKeywords = getStationKeyWords(startSearchQuery)
    val endStationKeywords = getStationKeyWords(endSearchQuery)

    init {
        viewModelScope.launch {
            stations.putAll(stationRepository.getStations().associateBy { it.id })
            val keywords = stationRepository.getStationKeywords()
            stationKeywords.putAll(keywords.associateBy { it.keyword })
            sortedStationKeywords.addAll(keywords)

        }
    }

    fun calculateDistanceBetweenStations() {
        val startStationId = stationKeywords[startSearchQuery.value]?.stationId ?: run {
            uiState.value = MainSearchUiState.StationNotFound(startSearchQuery.value)
            return
        }
        val endStationId = stationKeywords[endSearchQuery.value]?.stationId ?: run {
            uiState.value = MainSearchUiState.StationNotFound(endSearchQuery.value)
            return
        }

        val startStation = stations[startStationId] ?: throw IllegalStateException("Start station not found")
        val endStation = stations[endStationId] ?: throw IllegalStateException("End station not found")

        val distance = calculateDistance(
            firstLatitude = startStation.latitude,
            firstLongitude = startStation.longitude,
            secondLatitude = endStation.latitude,
            secondLongitude = endStation.longitude
        )

        uiState.value = MainSearchUiState.Calculated(distance)
    }

    private fun getStationKeyWords(searchQuery: MutableStateFlow<String>): StateFlow<ImmutableList<StationKeyword>> {
        return searchQuery.map { query ->
            if (query.isBlank()) {
                persistentListOf()
            } else {
                sortedStationKeywords
                    .filter {
                        it.keyword.startsWith(query, ignoreCase = true)
                    }
                    .toImmutableList()
            }
        }.stateIn(
            viewModelScope,
            initialValue = persistentListOf(),
            started = SharingStarted.WhileSubscribed(),
        )
    }
}