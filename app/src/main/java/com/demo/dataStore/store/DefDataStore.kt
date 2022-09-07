package com.demo.dataStore.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DefDataStore<T>(
    private val mPref: DataStore<Preferences>,
    private val key: Preferences.Key<T>,
    private val default: T
) {
    suspend fun get(): T {
        return mPref.data.map { preferences -> preferences[key] ?: default }
            .catch { } // log something
            .firstOrNull() ?: default
    }

    suspend fun set(value: T) {
        mPref.edit { preference ->
            preference[key] = value
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