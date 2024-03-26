package com.example.testapp_koleo.localstorage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class LocalDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val preferences: Flow<AppPreferences>
        get() = dataStore.data.map { prefs ->
            AppPreferences(
                isFirstStart = prefs[IS_FIRST_START] ?: true
            )
        }

    suspend fun updateIsFirstStart(isFirstStart: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_FIRST_START] = isFirstStart
        }
    }

    private companion object {
        val IS_FIRST_START = booleanPreferencesKey("is_first_start")
    }
}