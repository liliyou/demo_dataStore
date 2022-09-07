package com.demo.dataStore

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.demo.dataStore.store.PreferencesGroup


class mApp : Application() {

    private val Context.prefsDataStore by preferencesDataStore(
        name = "preferencesDataStore"
    )

    override fun onCreate() {
        super.onCreate()

        preferencesGroup = PreferencesGroup(prefsDataStore)
    }

    companion object {
        @JvmStatic
        lateinit var preferencesGroup: PreferencesGroup
    }
}