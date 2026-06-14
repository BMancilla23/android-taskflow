package com.bryan.taskflow.presentation.task.edit

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import com.bryan.taskflow.presentation.task.TaskViewModel
import com.bryan.taskflow.presentation.task.components.TaskForm

@Composable
fun EditTaskScreen(
    viewModel: TaskViewModel,
    taskId: Long,
    onNavigateBack: () -> Unit
){

    LaunchedEffect(taskId){
        viewModel.loadTask(taskId)
    }

   val state by viewModel.uiState.collectAsState()
    TaskForm(
     title = state.newTaskTitle,
        description = state.newTaskDescription,
        priority = state.selectedPriority,
        buttonText = "Actualizar tarea",
        onTitleChange = viewModel::onTaskTitleChange,
        onDescriptionChange = viewModel::onTaskDescriptionChange,
        onPriorityChange = viewModel::onPriorityChange,
        onSave = {
            viewModel.updateTask()
            onNavigateBack()
        }
    )
}