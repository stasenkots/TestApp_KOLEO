package com.example.testapp_koleo

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.testapp_koleo.data.AppPreferencesRepository
import com.example.testapp_koleo.utils.ApplicationScope
import com.example.testapp_koleo.workmanager.StationsWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class TestApplication : Application(), Configuration.Provider {

    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var appPreferencesRepository: AppPreferencesRepository

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            appPreferencesRepository.preferences
                .filter { it.isFirstStart }
                .collectLatest {
                    val updateStationsRequest =
                        PeriodicWorkRequestBuilder<StationsWorker>(1, TimeUnit.DAYS)
                            .build()

                    WorkManager
                        .getInstance(applicationContext)
                        .enqueue(updateStationsRequest)
                }
        }


    }
}