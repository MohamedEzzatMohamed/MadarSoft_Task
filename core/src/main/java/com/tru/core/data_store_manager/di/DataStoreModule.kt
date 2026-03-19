package com.tru.core.data_store_manager.di

import android.content.Context
import com.tru.core.data_store_manager.preference_datastore.PreferenceDataStoreHandler
import com.tru.core.data_store_manager.preference_datastore.PreferenceDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providePreferenceDataStoreManager(@ApplicationContext context: Context): PreferenceDataStoreHandler =
        PreferenceDataStoreManager(context)
}