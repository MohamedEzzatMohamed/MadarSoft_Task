package com.tru.core.crash_reporting

import com.tru.core.crash_reporting.crash_reporting_tools.CrashReportingTool
import com.tru.core.storage_manager.StorageManager
import com.tru.core.storage_manager.di.StorageManagerModule
import javax.inject.Inject
import javax.inject.Named


class CoreCrashReportingHandler @Inject constructor(
    @Named(StorageManagerModule.SHARED_PREFERENCE)
    private val storageManager: StorageManager
) : CrashReportingHandler {

    override val crashReportingTools =
        mutableMapOf<CrashReportingToolIdentifier, CrashReportingTool>()
    override val crashReportingKeys = mutableMapOf<String, Any?>()

    init {
        initCrashReportingKeys()
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        crashReportingTools.values.forEach {
            it.log(priority, tag, message, t)
        }
    }


    override fun registerCrashReportingTool(crashReportingTool: CrashReportingTool) {
        crashReportingTools[crashReportingTool.identifier] = crashReportingTool
    }

    override fun unRegisterCrashReportingTool(identifier: CrashReportingToolIdentifier) {
        crashReportingTools.remove(identifier)
    }

    override fun getCrashReportingTool(identifier: CrashReportingToolIdentifier) =
        crashReportingTools[identifier]

    override fun initCrashReportingKeys() {
        storageManager.getAll()
            ?.filter { it.key.startsWith(CRASH_REPORT_KEY) }
            ?.map { (k, v) -> k.removePrefix(CRASH_REPORT_KEY) to v.toString() }
            ?.forEach { setCrashReportingKey(it.first, it.second) }
    }


    override fun setCrashReportingKey(key: String, value: Any?, stored: Boolean) {
        crashReportingKeys[key] = value
        crashReportingTools.values.forEach { it.setCrashReportingKey(key, value) }
        if (stored) storageManager.setString("$CRASH_REPORT_KEY$key", value.toString())
    }

    override fun setCrashReportingKey(key: CrashReportingKey, value: Any?, stored: Boolean) {
        setCrashReportingKey(key.key, value, stored)
    }

    companion object Companion {
        private const val CRASH_REPORT_KEY = "CRK_"
    }
}