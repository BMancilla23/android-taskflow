package com.bryan.taskflow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.presentation.task.TaskViewModel

@Composable
fun TaskScreen(
    viewModel: TaskViewModel
) {
    // Convierte el StateFLow en un State de Compose.
    // Cuando uiState cambia, la pantalla se recompone automáticamente.
    // by evita usar .value
    val state by viewModel.uiState.collectAsState() // Conectar Stateflow con Compose

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(
                text = "Mis tareas",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = state.newTaskTitle,
                onValueChange = viewModel::onTaskTitleChange,
                label = {
                    Text("Título")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            OutlinedTextField(
                value = state.newTaskDescription,
                onValueChange = viewModel::onTaskDescriptionChange,
                label = {
                    Text("Descripción")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Button(
                onClick = {
                    viewModel.addTask()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear tarea")
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Lista eficiente para grandes cantidades de elementos.
            // Solo renderiza los elementos visibles en pantalla
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding()
            ) {
                items(state.tasks) { task ->
                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = {
                                viewModel.toggleTask(task.id)
                            }
                        )
                        Text(
                            text = task.title,
                            // Modifier permite configurar aparicencia,
                            // tamaño, posición y comportamiento del componente
                            modifier = Modifier.padding(16.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )
                        Text(
                            text = "Prioridad: ${task.priority}"
                        )
                    }
                }
            }
        }
    }

}