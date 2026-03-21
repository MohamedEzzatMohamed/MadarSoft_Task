package com.madarsofttask.feature.add_user_screen.presentation.viewmodel

import com.madarsofttask.common.domain.entitty.UserEntity
import com.madarsofttask.feature.add_user_screen.domain.event.AddUserIntent
import com.madarsofttask.feature.add_user_screen.domain.event.state.AddUserState
import com.madarsofttask.feature.add_user_screen.domain.repository.AddUserRepository
import com.madarsofttask.feature.add_user_screen.domain.usecase.ValidateUserAgeUseCase
import com.madarsofttask.feature.add_user_screen.domain.usecase.ValidateUserGenderUseCase
import com.madarsofttask.feature.add_user_screen.domain.usecase.ValidateUserJobTitleUseCase
import com.madarsofttask.feature.add_user_screen.domain.usecase.ValidateUserNameUseCase
import com.tru.core.bases.base_viewmodel.BaseViewModel
import com.tru.core.error.AppError
import com.tru.core.extensions.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val validateUserAgeUseCase: ValidateUserAgeUseCase,
    private val validateUserGenderUseCase: ValidateUserGenderUseCase,
    private val validateUserJobTitleUseCase: ValidateUserJobTitleUseCase,
    private val addUserRepository: AddUserRepository
) : BaseViewModel<AddUserIntent, AddUserState>(initialState = AddUserState()) {


    fun sendValidateUserNameIntent(name: String) =
        sendIntent(AddUserIntent.UserNameValidationIntent(userName = name))

    fun sendValidateUserAgeIntent(age: String) =
        sendIntent(AddUserIntent.UserAgeValidationIntent(age = age))

    fun sendValidateUserGenderIntent(gender: String) =
        sendIntent(AddUserIntent.UserGenderValidationIntent(gender = gender))

    fun sendValidateUserJobTitleIntent(jobTitle: String) =
        sendIntent(AddUserIntent.UserJobTitleValidationIntent(jobTitle = jobTitle))


    fun sendAddUserIntent() =
        sendIntent(AddUserIntent.AddUserIntent(userEntity = userEntity))

    private val userEntity
        get() = UserEntity(
            name = uiStateFlow.value.name,
            age = uiStateFlow.value.age,
            jobTitle = uiStateFlow.value.jobTitle,
            genderType = uiStateFlow.value.gender
        )


    override fun processIntent(intent: AddUserIntent) {
        when (intent) {
            is AddUserIntent.UserNameValidationIntent -> {
                reduceNameValidationState(name = intent.userName)
                isNameValid()
            }

            is AddUserIntent.UserAgeValidationIntent -> {
                reduceAgeValidationState(age = intent.age)
                isAgeValid()
            }

            is AddUserIntent.UserGenderValidationIntent -> {
                reduceGenderTypeValidationState(gender = intent.gender)
                isGenderTypeValid()
            }

            is AddUserIntent.UserJobTitleValidationIntent -> {
                reduceJobTitleValidationState(jobTitle = intent.jobTitle)
                isJobTitleValid()
            }

            is AddUserIntent.AddUserIntent -> {
                reduceAddUserState(userEntity = intent.userEntity)
            }
        }
    }


    private fun reduceNameValidationState(name: String) = updateStateFlow { copy(name = name) }

    private fun isNameValid(): Boolean {
        val validationState = AddUserState(name = uiStateFlow.value.name)
        val result = validateUserNameUseCase.execute(input = validationState)
        updateStateFlow { copy(errorName = result.errorText) }
        return result.isSuccessful
    }


    private fun reduceAgeValidationState(age: String) = updateStateFlow { copy(age = age) }

    private fun isAgeValid(): Boolean {
        val validationState = AddUserState(age = uiStateFlow.value.age)
        val result = validateUserAgeUseCase.execute(input = validationState)
        updateStateFlow { copy(errorAge = result.errorText) }
        return result.isSuccessful
    }


    private fun reduceGenderTypeValidationState(gender: String) =
        updateStateFlow { copy(gender = gender) }

    private fun isGenderTypeValid(): Boolean {
        val validationState = AddUserState(gender = uiStateFlow.value.gender)
        val result = validateUserGenderUseCase.execute(input = validationState)
        updateStateFlow { copy(errorGender = result.errorText) }
        return result.isSuccessful
    }

    private fun reduceJobTitleValidationState(jobTitle: String) =
        updateStateFlow { copy(jobTitle = jobTitle) }

    private fun isJobTitleValid(): Boolean {
        val validationState = AddUserState(jobTitle = uiStateFlow.value.jobTitle)
        val result = validateUserJobTitleUseCase.execute(input = validationState)
        updateStateFlow { copy(errorJobTitle = result.errorText) }
        return result.isSuccessful
    }


    private fun reduceAddUserState(userEntity: UserEntity) = viewModelScope(
        context = Dispatchers.IO
    ) {
        updateStateFlow { copy(isLoading = true) }
        try {
            addUserRepository.addUser(user = userEntity.toUserDto())
            updateStateFlow { copy(isLoading = false, isAddedSuccess = true) }
        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateStateFlow { copy(isLoading = false, appError = appError) }
            }
        }
    }


    fun resetAddUserState() = updateStateFlow {
        copy(
            name = "",
            age = "",
            jobTitle = "",
            gender = "",
            isLoading = false,
            appError = null,
            isAddedSuccess = false
        )
    }
}