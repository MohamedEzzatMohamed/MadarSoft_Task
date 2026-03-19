package com.tru.core.data_store_manager.preference_datastore

import androidx.datastore.preferences.core.Preferences

interface PreferenceDataStoreHandler {

    suspend fun setBoolean(preferencesKey: Preferences.Key<Boolean>, value: Boolean)
    suspend fun getBoolean(preferencesKey: Preferences.Key<Boolean>): Boolean?

    suspend fun setInteger(preferencesKey: Preferences.Key<Int>, value: Int)
    suspend fun getInteger(preferencesKey: Preferences.Key<Int>): Int?

    suspend fun setFloat(preferencesKey: Preferences.Key<Float>, value: Float)
    suspend fun getFloat(preferencesKey: Preferences.Key<Float>): Float?

    suspend fun setString(preferencesKey: Preferences.Key<String>, value: String)
    suspend fun getString(preferencesKey: Preferences.Key<String>): String?

    suspend fun <T>  setObject(preferencesKey: Preferences.Key<String>, objects: T)
    suspend fun <T> getObject(preferencesKey: Preferences.Key<String>, type: Class<T>): T?

    suspend fun <T>removeByKey(preferencesKey: Preferences.Key<T>)
}