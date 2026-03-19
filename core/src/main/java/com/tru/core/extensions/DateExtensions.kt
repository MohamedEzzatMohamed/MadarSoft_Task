package com.tru.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun Long.getDate(
    outputPattern: String = "yyyy-MM-dd HH:mm:ss",
    timeZone: String = "Africa/Cairo"
): String {
    val date = Date(this * 1000)
    val formatter = SimpleDateFormat(outputPattern, Locale.US)
    formatter.timeZone = TimeZone.getTimeZone(timeZone)
    return formatter.format(date)
}