package com.example.testapp_koleo.data

import com.example.testapp_koleo.localstorage.datastore.AppPreferences
import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {

    val preferences: Flow<AppPreferences>

    suspend fun setFirstStart()
}