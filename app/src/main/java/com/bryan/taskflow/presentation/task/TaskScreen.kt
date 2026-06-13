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
                text = "Mis tareas", style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = state.newTaskTitle, onValueChange = viewModel::onTaskTitleChange, label = {
                    Text("Título")
                }, modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.height(8.dp)
            )
            Text(
                text = "Prioridad", style = MaterialTheme.typography.titleSmall
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            // TaskPriority.entries devuelve todos los valores del enum.
            // Se usa para generar los chips dinámicamente sin escribirlos manualmente.
            Row {
                TaskPriority.entries.forEach { priority ->
                    FilterChip(selected = state.selectedPriority == priority, onClick = {
                        viewModel.onPriorityChange(priority)
                    }, label = {
                        Text(priority.displayName())
                    })
                }
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Button(
                onClick = {
//                    viewModel.addTask()
                    viewModel.saveTask()
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
//                    "Crear tarea"
                    if (state.editingTask == null) "Crear tarea"
                    else "Actualizar tarea"
                )
            }

            if(state.editingTask != null){
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {
                        viewModel.cancelEdit()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar edición")
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Lista eficiente para grandes cantidades de elementos.
            // Solo renderiza los elementos visibles en pantalla
            // weight(1f) ocupa todo el espacio restante dentro de la Column.
            // Sin weight, la LazyColumn puede quedarse sin espacio o provocar
            // problemas de scroll al coexistir con otros componentes.
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(state.tasks) { task ->

                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            Checkbox(
                                checked = task.isCompleted, onCheckedChange = {
                                    viewModel.toggleTask(task.id)
                                })
                            Column(
                                // Modifier permite configurar aparicencia,
                                // tamaño, posición y comportamiento del componente
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 12.dp)
                            ) {

                                Text(
                                    text = task.title, style = MaterialTheme.typography.titleMedium

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
                                    text = "Prioridad: ${task.priority.displayName()}"
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.startEdit(task)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar"
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.deleteTask(task)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}