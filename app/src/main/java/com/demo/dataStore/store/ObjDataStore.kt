package com.demo.dataStore.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
class ObjDataStore<T>(
    private val mPref: DataStore<Preferences>,
    private val key: Preferences.Key<String>,
    private val typeClass: Class<T>
) {
    suspend fun get(): T? {
        return try {
            mPref.data.map { preferences ->
                Gson().fromJson(preferences[key], typeClass)
            }.firstOrNull()

        } catch (e: Exception) {
            null
        }
    }

    suspend fun set(value: T?) {
        mPref.edit { preference ->
            val json = Gson().toJson(value ?: "")
            preference[key] = json
        }
    }

    suspend fun remove() {
        mPref.edit { preference ->
            preference.remove(key)
        }
    }

    suspend fun reset() {
        mPref.edit { preference ->
            preference.clear()
        }
    }
}