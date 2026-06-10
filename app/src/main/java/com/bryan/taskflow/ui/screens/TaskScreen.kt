package com.bryan.taskflow.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Card
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
    // Cuando uiState cambia, la pantalla se recomienda automáticamente.
    // by evita usar .value
    val state by viewModel.uiState.collectAsState() // Conectar Stateflow con Compose

    Scaffold { innerPadding ->
        // Lista eficiente para grandes cantidades de elementos.
        // Solo renderiza los elementos visibles en pantalla
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(state.tasks) { task ->
                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = task.title,
                        // Modifier permite configurar aparicencia,
                        // tamaño, posición y comportamiento del componente
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }

}