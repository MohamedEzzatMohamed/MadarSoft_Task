package com.tru.core.error

data class AppException(val appError: AppError) : Exception()