package com.madarsofttask.feature.add_user_screen.domain.usecase

import com.tru.core.ui_component.custom_text_failed.ui_text.UiText
import com.madarsofttask.R
import com.madarsofttask.feature.add_user_screen.domain.event.state.AddUserState
import com.madarsofttask.feature.add_user_screen.domain.model.ValidationResult
import com.tru.core.bases.base_usecase.BaseUseCase
import javax.inject.Inject

class ValidateUserAgeUseCase @Inject constructor() : BaseUseCase<AddUserState, ValidationResult> {

    override fun execute(input: AddUserState): ValidationResult {
        if(input.age.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorText = UiText.StringResource(resId = R.string.enterAge)
            )
        }
        return ValidationResult(isSuccessful = true, errorText = null)
    }
}