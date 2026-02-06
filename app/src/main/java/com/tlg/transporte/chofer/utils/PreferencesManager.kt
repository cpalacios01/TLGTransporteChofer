/**
 * Gestor de preferencias
 * Path: app/src/main/java/com/tlg/transporte/chofer/utils/PreferencesManager.kt
 * Última modificación: 2026-02-05 14:50
 * Dev: cpalacios01@gmail.com | +595994648273
 */

package com.tlg.transporte.chofer.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {
    
    companion object {
        private val USER_ID_KEY = intPreferencesKey("user_id")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }
    
    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN_KEY] ?: false
    }
    
    suspend fun saveLoginData(userId: Int, token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_TOKEN_KEY] = token
            preferences[IS_LOGGED_IN_KEY] = true
        }
    }
    
    suspend fun clearLoginData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}