package com.tru.core.error

interface AppErrorHandler {
    fun handleError(error: AppError, callback: AppError.() -> Unit = {})
}