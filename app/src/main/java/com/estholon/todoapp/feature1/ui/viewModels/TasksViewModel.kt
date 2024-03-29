package com.estholon.todoapp.feature1.ui.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estholon.todoapp.feature1.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(): ViewModel(){

    private var _showDialog = MutableLiveData<Boolean>()
    val showDialog : LiveData<Boolean> = _showDialog

    private val _task = mutableStateListOf<TaskModel>()
    val task:List<TaskModel> = _task


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        _task.add(TaskModel(task = task))
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _task.indexOf(taskModel)
        _task[index] = _task[index].let{
            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        val task = _task.find { it.id == taskModel.id }
        _task.remove(task)
    }
}