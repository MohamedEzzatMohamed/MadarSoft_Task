package com.madarsofttask.feature.all_users_list_screen.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.madarsofttask.common.domain.entitty.UserEntity
import com.madarsofttask.feature.all_users_list_screen.domain.event.AllUsersIntent
import com.madarsofttask.feature.all_users_list_screen.domain.model.mapper.toUserEntity
import com.madarsofttask.feature.all_users_list_screen.domain.model.state.AllUsersListState
import com.madarsofttask.feature.all_users_list_screen.domain.repository.AllUsersRepository
import com.tru.core.bases.base_viewmodel.BaseViewModel
import com.tru.core.error.AppError
import com.tru.core.extensions.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AllUsersListViewModel @Inject constructor(
    private val allUsersRepository: AllUsersRepository,
) : BaseViewModel<AllUsersIntent, AllUsersListState>(initialState = AllUsersListState()) {

    init {
        sendRetrieveUsersIntent()
    }

    fun refresh() = sendRetrieveUsersIntent()

    private fun sendRetrieveUsersIntent() = sendIntent(AllUsersIntent.GetAllUsersIntent)


    override fun processIntent(intent: AllUsersIntent) {
        when (intent) {
            AllUsersIntent.GetAllUsersIntent -> reduceUsersListState()
        }
    }


    private fun reduceUsersListState() = viewModelScope(context = Dispatchers.IO) {
        updateStateFlow { copy(isLoading = true) }
        try {
            allUsersRepository.getAllUsers().collect { users ->
                val usersUiModel = users.map { it.toUserEntity() }
                updateStateFlow { copy(isLoading = false, userUiModelList = usersUiModel) }
            }
        } catch (e: Exception) {
            val appError = AppError.E(exception = e, message = e.message ?: "Unknown error")
            handleError(error = appError) {
                updateStateFlow { copy(isLoading = false, appError = appError) }
            }
        }
    }

}