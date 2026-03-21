package com.madarsofttask.common.domain.entitty

import com.madarsofttask.common.data.model.UserDto

data class UserEntity(
    val userId : Long? = null,
    val name: String? = null,
    val age: String? = null,
    val jobTitle: String? = null,
    val genderType: String? = null
) {
    fun toUserDto() = UserDto(
        userName = name,
        age = age,
        jobTitle = jobTitle,
        genderType = genderType
    )
}

