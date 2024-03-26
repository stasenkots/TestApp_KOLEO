package com.example.testapp_koleo.ui.search

sealed class MainSearchUiState {

    data object Initial : MainSearchUiState()

    data class Calculated(val distance: Double) : MainSearchUiState()

    data class StationNotFound(val stationName: String) : MainSearchUiState()
}