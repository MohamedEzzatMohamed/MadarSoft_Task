package com.madarsofttask.application

import com.google.firebase.FirebaseApp
import com.tru.core.bases.base_application.BaseApplication
import com.tru.core.crash_reporting.CrashReportingHandler
import com.tru.core.crash_reporting.crash_reporting_tools.FirebaseCrashReportingTool
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MadarSoftApplication : BaseApplication() {


    @Inject
    lateinit var firebaseCrashReportingTool: FirebaseCrashReportingTool


    override fun onCreate() {
        super.onCreate()
        initFirebaseApp()
    }

    private fun initFirebaseApp() {
        FirebaseApp.initializeApp(this)
    }

    override fun addCrashReportingTools(crashReportingHandler: CrashReportingHandler?) {
        if (this::firebaseCrashReportingTool.isInitialized) return
        crashReportingHandler?.registerCrashReportingTool(firebaseCrashReportingTool)
    }

}