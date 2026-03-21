package com.madarsofttask.feature.all_users_list_screen.domain.model.mapper

import com.madarsofttask.common.data.model.UserDto
import com.madarsofttask.common.domain.entitty.UserEntity

fun UserDto.toUserEntity() = UserEntity(
    userId = userId,
    name = userName,
    age = age,
    genderType = genderType,
    jobTitle = jobTitle,
)