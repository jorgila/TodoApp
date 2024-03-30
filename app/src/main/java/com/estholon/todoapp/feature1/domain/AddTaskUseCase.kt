package com.estholon.todoapp.feature1.domain

import com.estholon.todoapp.feature1.data.TaskRepository
import com.estholon.todoapp.feature1.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.add(taskModel)
    }
}