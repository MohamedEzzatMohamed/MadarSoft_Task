package com.tru.core.bases.base_application

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.tru.core.BuildConfig
import com.tru.core.crash_reporting.CrashReportingKey
import com.tru.core.crash_reporting.CrashReportingHandler
import timber.log.Timber
import javax.inject.Inject

abstract class BaseApplication : Application() {

    @Inject
    lateinit var crashReportingHandler: CrashReportingHandler

    override fun onCreate() {
        super.onCreate()
        initCrashReportingManager()
        plantTimberTrees()
        initPluto()
    }

    private fun initCrashReportingManager() {
        if (this::crashReportingHandler.isInitialized)
            addCrashReportingTools(crashReportingHandler)
    }

    open fun addCrashReportingTools(crashReportingHandler: CrashReportingHandler?) = Unit

    private fun plantTimberTrees() {
        Timber.plant(CrashReportingTree())
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private inner class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            setPreLogKeys()
            crashReportingHandler.log(priority, tag, message, t)
        }
    }

    private fun setPreLogKeys() {
        getPreLogKeys().forEach { (key, value) ->
            crashReportingHandler.setCrashReportingKey(
                key,
                value
            )
        }
    }

    open fun getPreLogKeys(): List<Pair<CrashReportingKey, String?>> = listOf()



    private fun initPluto() = Pluto.Installer(this).apply {
        getPlutoPlugins().forEach { addPlugin(it) }
    }.install()


    open fun getPlutoPlugins() = listOf(PlutoNetworkPlugin())


}