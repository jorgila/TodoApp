package com.estholon.todoapp.feature1.data.di

import android.content.Context
import androidx.room.Room
import com.estholon.todoapp.feature1.data.TaskDao
import com.estholon.todoapp.feature1.data.TodoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTaskDao(todoDB: TodoDB):TaskDao{
        return todoDB.taskDao()
    }

    @Provides
    @Singleton
    fun provideTodoDB(@ApplicationContext appContext: Context) : TodoDB{
        return Room.databaseBuilder(appContext, TodoDB::class.java, "TaskDB").build()
    }

}