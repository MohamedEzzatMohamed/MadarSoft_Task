package com.core.ui_component.validation_method

import android.util.Patterns


fun isNumber(value: String): Boolean {
    return value.isEmpty() || Regex("^\\d+\$").matches(value)
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    val passwordRegex = Regex(
        pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$"
    )
    return passwordRegex.matches(password)
}