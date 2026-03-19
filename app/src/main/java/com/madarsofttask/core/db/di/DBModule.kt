package com.madarsofttask.core.db.di

import android.content.Context
import androidx.room.Room
import com.madarsofttask.common.data.dao.UserDao
import com.madarsofttask.core.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "madarsoft-db"

@Module
@InstallIn(SingletonComponent::class)
object DBModule {


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun provideUserDao(db: AppDataBase): UserDao = db.userDao()

}