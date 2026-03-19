package com.tru.core.connectivity.di

import android.content.Context
import com.tru.core.connectivity.connectivity_manager.ConnectivityManager
import com.tru.core.connectivity.internet_access_observer.InternetAccessObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class NetworkManagerModule {

    @Provides
    fun provideNetworkManager(@ApplicationContext  context: Context, internetAccessObserver: InternetAccessObserver) =
        ConnectivityManager(context, internetAccessObserver)

    @Provides
    fun provideInternetAccessObserver() = InternetAccessObserver()
}