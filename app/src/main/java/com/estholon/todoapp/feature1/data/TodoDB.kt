package com.estholon.todoapp.feature1.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDB : RoomDatabase() {

    // DAO
    abstract fun taskDao() : TaskDao

}