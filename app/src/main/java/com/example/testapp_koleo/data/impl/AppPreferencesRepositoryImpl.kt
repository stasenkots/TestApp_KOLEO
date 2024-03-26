package com.example.testapp_koleo.data.impl

import com.example.testapp_koleo.data.AppPreferencesRepository
import com.example.testapp_koleo.localstorage.datastore.AppPreferences
import com.example.testapp_koleo.localstorage.datastore.LocalDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore
): AppPreferencesRepository {

    override val preferences: Flow<AppPreferences>
        get() = localDataStore.preferences

    override suspend fun setFirstStart() {
        localDataStore.updateIsFirstStart(false)
    }
}