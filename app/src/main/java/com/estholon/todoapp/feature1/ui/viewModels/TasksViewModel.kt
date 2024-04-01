package com.estholon.todoapp.feature1.ui.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estholon.todoapp.feature1.domain.AddTaskUseCase
import com.estholon.todoapp.feature1.domain.GetTaskUseCase
import com.estholon.todoapp.feature1.ui.TasksUiState
import com.estholon.todoapp.feature1.ui.TasksUiState.*
import com.estholon.todoapp.feature1.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTaskUseCase: GetTaskUseCase
): ViewModel(){

    val uiState : StateFlow<TasksUiState> =
        getTaskUseCase()
        .map ( ::Success )
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private var _showDialog = MutableLiveData<Boolean>()
    val showDialog : LiveData<Boolean> = _showDialog

//    private val _task = mutableStateListOf<TaskModel>()
//    val task:List<TaskModel> = _task


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task=task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        //Actualizar check
//        val index = _task.indexOf(taskModel)
//        _task[index] = _task[index].let{
//            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        //Borrar item
//        val task = _task.find { it.id == taskModel.id }
//        _task.remove(task)
    }
}