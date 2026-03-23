package com.madarsofttask.feature.add_user_screen.presentation.viewmodel

import com.madarsofttask.common.domain.entitty.UserEntity
import com.madarsofttask.feature.add_user_screen.domain.event.AddUserEvent
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
) : BaseViewModel<AddUserIntent, AddUserState, AddUserEvent>(initialState = AddUserState()) {

    fun onNameChange(name: String) = sendIntent(AddUserIntent.UserNameValidationIntent(name))
    fun onAgeChange(age: String) = sendIntent(AddUserIntent.UserAgeValidationIntent(age))
    fun onGenderChange(gender: String) =
        sendIntent(AddUserIntent.UserGenderValidationIntent(gender))

    fun onJobTitleChange(jobTitle: String) =
        sendIntent(AddUserIntent.UserJobTitleValidationIntent(jobTitle))

    fun onAddNewUserClick() = sendIntent(AddUserIntent.AddNewUserIntent(userEntity))

    private val userEntity: UserEntity
        get() = UserEntity(
            name = uiState.value.name,
            age = uiState.value.age,
            jobTitle = uiState.value.jobTitle,
            genderType = uiState.value.gender
        )

    override suspend fun handleIntent(intent: AddUserIntent) {
        when (intent) {
            is AddUserIntent.UserNameValidationIntent -> {
                updateState { copy(name = intent.userName) }
                validateName()
            }

            is AddUserIntent.UserAgeValidationIntent -> {
                updateState { copy(age = intent.age) }
                validateAge()
            }

            is AddUserIntent.UserGenderValidationIntent -> {
                updateState { copy(gender = intent.gender) }
                validateGender()
            }

            is AddUserIntent.UserJobTitleValidationIntent -> {
                updateState { copy(jobTitle = intent.jobTitle) }
                validateJobTitle()
            }

            is AddUserIntent.AddNewUserIntent -> {
                executeAddUser(intent.userEntity)
            }
        }
    }

    // --- Validation Logic ---
    private fun validateName() {
        val result = validateUserNameUseCase.execute(uiState.value)
        updateState { copy(errorName = result.errorText) }
    }

    private fun validateAge() {
        val result = validateUserAgeUseCase.execute(uiState.value)
        updateState { copy(errorAge = result.errorText) }
    }

    private fun validateGender() {
        val result = validateUserGenderUseCase.execute(uiState.value)
        updateState { copy(errorGender = result.errorText) }
    }

    private fun validateJobTitle() {
        val result = validateUserJobTitleUseCase.execute(uiState.value)
        updateState { copy(errorJobTitle = result.errorText) }
    }

    // --- Repository Interaction ---
    private suspend fun executeAddUser(userEntity: UserEntity) {
        // نستخدم الـ Dispatcher من خلال الـ scope الخارجي أو نحدده هنا إذا لزم الأمر
        // الـ handleIntent تُنفذ أصلاً في viewModelScope
        updateState { copy(isLoading = true) }

        try {
            addUserRepository.addUser(user = userEntity.toUserDto())
            updateState { copy(isLoading = false, isAddedSuccess = true) }

            // إرسال Event للرجوع للخلف أو إظهار رسالة
            _uiEvent.emit(AddUserEvent.NavigateToList)

        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateState { copy(isLoading = false, appError = appError) }
            }
        }
    }

    fun resetState() {
        updateState { AddUserState() }
    }
}