package com.estholon.todoapp.feature1.domain

import com.estholon.todoapp.feature1.data.TaskEntity
import com.estholon.todoapp.feature1.data.TaskRepository
import com.estholon.todoapp.feature1.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(val taskRepository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks

}