package com.bryan.taskflow.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.presentation.task.components.TaskList
import com.bryan.taskflow.ui.components.ScreenLayout

@Composable
fun TaskScreen(
    viewModel: TaskViewModel,
    onNavigateToCreate: () -> Unit,
    onNavigateToEdit: (Long) -> Unit,
    onLogout: () -> Unit
) {
    // Convierte el StateFLow en un State de Compose.
    // Cuando uiState cambia, la pantalla se recompone automáticamente.
    // by evita usar .value
    val state by viewModel.uiState.collectAsState() // Conectar Stateflow con Compose

    ScreenLayout(
        title = "Mis tareas",
        actions = {
            IconButton(
                onClick = onLogout
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Cerrar sesión"
                )
            }
        },
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
                text = "Hola, ${state.username}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            TaskList(
                modifier = Modifier.weight(1f),
                tasks = state.tasks,
                onToggleTask = viewModel::toggleTask,
                onEditTask = onNavigateToEdit,
                onDeleteTask = viewModel::deleteTask
            )
        }

    }

}