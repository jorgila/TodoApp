package com.estholon.todoapp.feature1.ui

import com.estholon.todoapp.feature1.ui.model.TaskModel

sealed interface TasksUiState {
    object Loading: TasksUiState
    data class Error(val throwable: Throwable): TasksUiState
    data class Success(val tasks:List<TaskModel>) : TasksUiState
}