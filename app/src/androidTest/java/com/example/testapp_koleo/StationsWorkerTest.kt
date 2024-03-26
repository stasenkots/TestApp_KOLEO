package com.example.testapp_koleo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.testapp_koleo.data.StationRepository
import com.example.testapp_koleo.utils.AppDispatchers
import com.example.testapp_koleo.utils.Dispatcher
import com.example.testapp_koleo.workmanager.StationsWorker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class StationsWorkerTest() {

    private lateinit var context: Context

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: StationRepository

    @Inject
    @Dispatcher(AppDispatchers.Default)
    lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testSleepWorker() {
        val worker = TestListenableWorkerBuilder<StationsWorker>(
            context = context
        ).setWorkerFactory(TestWorkerFactory(repository, dispatcher))
            .build()

        runBlocking {
            val result = worker.doWork()
            assertThat(result, CoreMatchers.`is`(ListenableWorker.Result.success()))
        }
    }
}

class TestWorkerFactory(
    private val repository: StationRepository,
    private val dispatcher: CoroutineDispatcher
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return StationsWorker(
            repository,
            dispatcher,
            appContext,
            workerParameters,
        )
    }
}
