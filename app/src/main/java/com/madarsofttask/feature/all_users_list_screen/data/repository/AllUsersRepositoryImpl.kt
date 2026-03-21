package com.madarsofttask.feature.all_users_list_screen.data.repository

import com.madarsofttask.common.data.dao.UserDao
import com.madarsofttask.feature.all_users_list_screen.domain.repository.AllUsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    AllUsersRepository {

    override suspend fun getAllUsers() = userDao.getAllUsers().flowOn(context = Dispatchers.IO)
}