package com.estholon.todoapp.feature1.ui.model

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean = false

){
}