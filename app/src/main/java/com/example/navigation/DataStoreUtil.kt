package com.example.navigation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore:DataStore<Preferences> by preferencesDataStore("pxd")
val isFirstPreference = booleanPreferencesKey("is_first_time_use")