package com.bryan.taskflow.presentation.task.create

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bryan.taskflow.presentation.task.TaskViewModel
import com.bryan.taskflow.presentation.task.components.TaskForm
import com.bryan.taskflow.ui.components.ScreenLayout

@Composable
fun CreateTaskScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    ScreenLayout(
        title = "Crear tarea",
        onNavigateBack = onNavigateBack
    ) {
        padding ->
        TaskForm(
            modifier = Modifier.padding(padding),
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
}