package com.estholon.todoapp.feature1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.estholon.todoapp.feature1.ui.viewModels.TasksViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog:Boolean by tasksViewModel.showDialog.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()){
        AddTasksDialog(
            showDialog,
            onDismiss = { tasksViewModel.onDialogClose() },
            onTaskAdded = { tasksViewModel.onTaskCreated(it) })
        FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
    }
}

@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel){
    FloatingActionButton(onClick = {
        tasksViewModel.onShowDialogClick()
    }, modifier = modifier) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddTasksDialog(show:Boolean, onDismiss:()->Unit, onTaskAdded: (String)->Unit){
    if(show){
        var myTask by rememberSaveable {
            mutableStateOf("")
        }
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded(myTask)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}