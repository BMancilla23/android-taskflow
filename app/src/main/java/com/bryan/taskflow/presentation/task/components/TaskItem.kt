package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.displayName

@Composable
fun TaskItem(
    task: Task,
    onToggleCompleted: (Long) -> Unit,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {

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
                    onToggleCompleted(task.id)
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
                    onEdit(task)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar"
                )
            }
            IconButton(
                onClick = {
                    onDelete(task)
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