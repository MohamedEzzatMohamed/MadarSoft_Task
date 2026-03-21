package com.madarsofttask.feature.add_user_screen.domain.repository

import com.madarsofttask.common.data.model.UserDto

interface AddUserRepository {

    suspend fun addUser(user: UserDto)
}