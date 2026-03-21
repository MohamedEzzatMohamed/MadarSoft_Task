package com.madarsofttask.feature.add_user_screen.domain.event

import com.madarsofttask.common.domain.entitty.UserEntity
import com.tru.core.bases.base_viewmodel.ViewIntent

sealed class AddUserIntent : ViewIntent {

    data class UserNameValidationIntent(val userName: String) :
        com.madarsofttask.feature.add_user_screen.domain.event.AddUserIntent()

    data class UserAgeValidationIntent(val age: String) :
        com.madarsofttask.feature.add_user_screen.domain.event.AddUserIntent()

    data class UserGenderValidationIntent(val gender: String) :
        com.madarsofttask.feature.add_user_screen.domain.event.AddUserIntent()

    data class UserJobTitleValidationIntent(val jobTitle: String) :
        com.madarsofttask.feature.add_user_screen.domain.event.AddUserIntent()

    data class AddUserIntent(val userEntity: UserEntity) :
        com.madarsofttask.feature.add_user_screen.domain.event.AddUserIntent()

}