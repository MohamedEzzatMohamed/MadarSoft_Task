package com.tru.core.crash_reporting.di

import com.tru.core.crash_reporting.CrashReportingHandler
import com.tru.core.crash_reporting.CoreCrashReportingHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CrashReportingModule {

    @Binds
    @Singleton
    abstract fun provideCrashReportingManager(reportingManager: CoreCrashReportingHandler): CrashReportingHandler
}
