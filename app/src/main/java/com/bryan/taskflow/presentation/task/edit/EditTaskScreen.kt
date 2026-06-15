package com.bryan.taskflow.presentation.task.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bryan.taskflow.presentation.task.TaskViewModel
import com.bryan.taskflow.presentation.task.components.TaskForm
import com.bryan.taskflow.ui.components.ScreenLayout

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

    ScreenLayout(
        title = "Editar tarea",
        onNavigateBack = onNavigateBack
    ) {
        padding ->
        TaskForm(
            modifier = Modifier.padding(padding),
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
}