package com.example.testapp_koleo.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.testapp_koleo.data.AppPreferencesRepository
import com.example.testapp_koleo.data.StationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class StationsWorker @AssistedInject constructor(
    private val stationRepository: StationRepository,
    private val ioDispatcher: CoroutineDispatcher,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        try {
            stationRepository.updateStations()
            Result.success()
        } catch (e: Exception) {
            return@withContext Result.retry()
        }
    }

}