package com.example.testapp_koleo.di

import com.example.testapp_koleo.utils.AppDispatchers
import com.example.testapp_koleo.utils.ApplicationScope
import com.example.testapp_koleo.utils.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @ApplicationScope
    fun provideCoroutineScope(
        @Dispatcher(AppDispatchers.Default) dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @Provides
    @Dispatcher(AppDispatchers.Default)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(AppDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(AppDispatchers.Main)
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}