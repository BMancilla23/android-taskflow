package com.bryan.taskflow.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.model.displayName
import com.bryan.taskflow.presentation.task.components.TaskList

@Composable
fun TaskScreen(
    viewModel: TaskViewModel,
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Long) -> Unit
) {
    // Convierte el StateFLow en un State de Compose.
    // Cuando uiState cambia, la pantalla se recompone automáticamente.
    // by evita usar .value
    val state by viewModel.uiState.collectAsState() // Conectar Stateflow con Compose

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreate
            ) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Mis tareas", style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            TaskList(
                tasks = state.tasks,
                onToggleTask = viewModel::toggleTask,
                onEditTask = onNavigateToEdit,
                onDeleteTask = viewModel::deleteTask
            )

        }
    }

}