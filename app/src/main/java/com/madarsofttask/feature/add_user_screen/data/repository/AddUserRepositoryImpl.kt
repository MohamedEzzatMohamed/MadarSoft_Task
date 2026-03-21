package com.madarsofttask.feature.add_user_screen.data.repository

import com.madarsofttask.common.data.dao.UserDao
import com.madarsofttask.common.data.model.UserDto
import com.madarsofttask.feature.add_user_screen.domain.repository.AddUserRepository
import javax.inject.Inject

class AddUserRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    AddUserRepository {

    override suspend fun addUser(user: UserDto) = userDao.insertUser(user)

}