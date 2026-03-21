package com.madarsofttask.feature.all_users_list_screen.di

import com.madarsofttask.feature.all_users_list_screen.data.repository.AllUsersRepositoryImpl
import com.madarsofttask.feature.all_users_list_screen.domain.repository.AllUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AllUsersModule {
    @Binds
    abstract fun bindAllUsersRepository(allUsersRepositoryImpl: AllUsersRepositoryImpl): AllUsersRepository
}