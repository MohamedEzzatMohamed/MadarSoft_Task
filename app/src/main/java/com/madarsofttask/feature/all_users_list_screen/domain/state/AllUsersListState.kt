package com.madarsofttask.feature.all_users_list_screen.domain.state

import com.madarsofttask.common.domain.entitty.UserEntity
import com.tru.core.bases.base_viewmodel.ViewState
import com.tru.core.error.AppError

data class AllUsersListState(
    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val userUiModelList: List<UserEntity> = emptyList(),
    val isUserDeleted: Boolean = false
) : ViewState
