package com.bryan.taskflow.presentation.task.create

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import com.bryan.taskflow.presentation.task.TaskViewModel
import com.bryan.taskflow.presentation.task.components.TaskForm

@Composable
fun CreateTaskScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
){
    val state by viewModel.uiState.collectAsState()
    TaskForm(
     title = state.newTaskTitle,
        description = state.newTaskDescription,
        priority = state.selectedPriority,
        buttonText = "Crear tarea",
        onTitleChange = viewModel::onTaskTitleChange,
        onDescriptionChange = viewModel::onTaskDescriptionChange,
        onPriorityChange = viewModel::onPriorityChange,
        onSave = {
            viewModel.createTask()
            onNavigateBack()
        }
    )
}