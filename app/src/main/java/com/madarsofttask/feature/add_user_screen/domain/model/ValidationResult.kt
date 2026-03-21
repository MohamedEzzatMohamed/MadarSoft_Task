package com.madarsofttask.feature.add_user_screen.domain.model

import com.core.ui_component.custom_text_failed.ui_text.UiText

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorText: UiText? = null
)