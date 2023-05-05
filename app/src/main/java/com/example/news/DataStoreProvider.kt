package com.example.news

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.news.data.network.Filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "filters_preferences")

class DataStoreProvider(context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val FILTER_KEY = stringPreferencesKey("filter")
    }


    suspend fun saveSelectedFilter(filter: Filter) {
        dataStore.edit { preferences ->
            preferences[FILTER_KEY] = filter.filterName
            Log.d("DataStore", "Saved filter: ${filter.filterName}") // Add this log

        }
    }

    fun getSelectedFilter(): Flow<Filter> {
        return dataStore.data.map { preferences ->
            val filterName = preferences[FILTER_KEY] ?: ""
            Log.d("DataStore", "Loaded filter: $filterName") // Add this log
            Filter(filterName)
        }
    }
}