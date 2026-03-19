package com.madarsofttask.common.domain.entitty

data class UserEntity(
    val userId : Long? = null,
    val name: String? = null,
    val age: Int? = null,
    val jobTitle: String? = null,
    val genderType: String? = null
) {
    fun toUserEntity() = UserEntity(
        name = name,
        age = age,
        jobTitle = jobTitle,
        genderType = genderType
    )
}

