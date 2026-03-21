package com.madarsofttask.feature.add_user_screen.di

import com.madarsofttask.feature.add_user_screen.data.repository.AddUserRepositoryImpl
import com.madarsofttask.feature.add_user_screen.domain.repository.AddUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddUserModule {

    @Binds
    abstract fun bindsAddUserRepository(addUserRepositoryImpl: AddUserRepositoryImpl): AddUserRepository
}