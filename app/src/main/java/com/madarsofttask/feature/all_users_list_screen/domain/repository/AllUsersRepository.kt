package com.madarsofttask.feature.all_users_list_screen.domain.repository

import com.madarsofttask.common.data.model.UserDto
import kotlinx.coroutines.flow.Flow

interface AllUsersRepository {
    suspend fun getAllUsers(): Flow<List<UserDto>>
}