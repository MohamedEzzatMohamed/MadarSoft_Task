package com.madarsofttask.common.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    @ColumnInfo("user_name")
    val userName: String? = null,
    @ColumnInfo("age")
    val age: String? = null,
    @ColumnInfo("job_title")
    val jobTitle: String? = null,
    @ColumnInfo("gender_type")
    val genderType: String? = null
)