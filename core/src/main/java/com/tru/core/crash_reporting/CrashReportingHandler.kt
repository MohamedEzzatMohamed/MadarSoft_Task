package com.tru.core.crash_reporting

import com.tru.core.crash_reporting.crash_reporting_tools.CrashReportingTool

interface CrashReportingHandler {

    val crashReportingTools: Map<CrashReportingToolIdentifier, CrashReportingTool>
    val crashReportingKeys: Map<String, Any?>

    fun log(priority: Int, tag: String?, message: String, t: Throwable?)

    fun registerCrashReportingTool(crashReportingTool: CrashReportingTool)

    fun unRegisterCrashReportingTool(identifier: CrashReportingToolIdentifier)

    fun getCrashReportingTool(identifier: CrashReportingToolIdentifier): CrashReportingTool?

    fun initCrashReportingKeys()

    fun setCrashReportingKey(key: String, value: Any?, stored: Boolean = false)

    fun setCrashReportingKey(key: CrashReportingKey, value: Any?, stored: Boolean = false)

}

