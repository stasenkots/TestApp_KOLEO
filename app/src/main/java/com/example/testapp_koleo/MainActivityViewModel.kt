package com.example.testapp_koleo

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.testapp_koleo.data.AppPreferencesRepository
import com.example.testapp_koleo.data.StationRepository
import com.example.testapp_koleo.workmanager.StationsWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val stationRepository: StationRepository,
    private val appPreferencesRepository: AppPreferencesRepository
) : ViewModel() {

    fun updateStations() {
        viewModelScope.launch {
            appPreferencesRepository.preferences
                .filter { it.isFirstStart }
                .collectLatest {
                    stationRepository.updateStations()
                    appPreferencesRepository.setFirstStart()
                }
        }
    }
}