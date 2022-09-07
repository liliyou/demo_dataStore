package com.demo.dataStore.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.demo.dataStore.ResponeData

class PreferencesGroup(private val mPref: DataStore<Preferences>) {
    val testString = DefDataStore(mPref, stringPreferencesKey("STRING"), "")
    val testBool = DefDataStore(mPref, booleanPreferencesKey("BOOL"), false)
    var testLong = DefDataStore(mPref, longPreferencesKey("NUMBER"), 0L)
    var defaultClass =
        ObjDataStore(mPref, stringPreferencesKey("CLASS"), ResponeData::class.java)
}