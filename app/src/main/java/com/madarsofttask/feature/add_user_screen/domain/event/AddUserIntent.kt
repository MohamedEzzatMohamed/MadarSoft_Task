package com.madarsofttask.feature.add_user_screen.domain.event

import com.madarsofttask.common.domain.entitty.UserEntity
import com.tru.core.bases.base_viewmodel.ViewIntent

sealed class AddUserIntent : ViewIntent {

    data class UserNameValidationIntent(val userName: String) : AddUserIntent()

    data class UserAgeValidationIntent(val age: String) : AddUserIntent()

    data class UserGenderValidationIntent(val gender: String) : AddUserIntent()

    data class UserJobTitleValidationIntent(val jobTitle: String) : AddUserIntent()

    data class AddNewUserIntent(val userEntity: UserEntity) : AddUserIntent()

}