package com.madarsofttask.feature.all_users_list_screen.presentation.viewmodel

import com.madarsofttask.feature.all_users_list_screen.domain.event.AllUsersEvent
import com.madarsofttask.feature.all_users_list_screen.domain.event.AllUsersIntent
import com.madarsofttask.feature.all_users_list_screen.domain.model.mapper.toUserEntity
import com.madarsofttask.feature.all_users_list_screen.domain.repository.AllUsersRepository
import com.madarsofttask.feature.all_users_list_screen.domain.state.AllUsersListState
import com.tru.core.bases.base_viewmodel.BaseViewModel
import com.tru.core.error.AppError
import com.tru.core.extensions.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AllUsersListViewModel @Inject constructor(
    private val allUsersRepository: AllUsersRepository,
) : BaseViewModel<AllUsersIntent, AllUsersListState, AllUsersEvent>(initialState = AllUsersListState()) {

    init {
        sendRetrieveUsersIntent()
    }

    fun refresh() = sendIntent(AllUsersIntent.GetAllUsersIntent)

    private fun sendRetrieveUsersIntent() = sendIntent(AllUsersIntent.GetAllUsersIntent)

    private fun observeUsersList() = viewModelScope(context = Dispatchers.IO) {
        updateState { copy(isLoading = true) }
        try {
            allUsersRepository.getAllUsers().collect { users ->
                val usersUiModel = users.map { it.toUserEntity() }
                updateState {
                    copy(
                        isLoading = false,
                        userUiModelList = usersUiModel,
                        appError = null
                    )
                }
            }
        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateState { copy(isLoading = false, appError = appError) }
            }
            _uiEvent.emit(AllUsersEvent.ShowError(appError.message ?: ""))
        }
    }

    override suspend fun handleIntent(intent: AllUsersIntent) {
        when (intent) {
            is AllUsersIntent.GetAllUsersIntent -> {
                observeUsersList()
            }
        }
    }

}