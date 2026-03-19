package com.madarsofttask.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madarsofttask.common.data.dao.UserDao
import com.madarsofttask.common.data.model.UserDto

@Database(
    entities = [UserDto::class],
    version = 2,
    exportSchema = true,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}