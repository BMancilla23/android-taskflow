package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.model.displayName

@Composable
fun TaskItem(
    task: Task,
    onToggleCompleted: (Long) -> Unit,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
//            .border(
//                width = 3.dp,
//                color = when(task.priority){
//                    TaskPriority.HIGH -> MaterialTheme.colorScheme.error
//                    TaskPriority.MEDIUM -> MaterialTheme.colorScheme.tertiary
//                    TaskPriority.LOW -> MaterialTheme.colorScheme.primary
//                },
//                shape = RoundedCornerShape(16.dp)
//            ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PriorityBadge(
                    priority = task.priority
                )

                Text(
                    text = if (task.isCompleted)
                        "Completada"
                    else "Pendiente",
                    style = MaterialTheme.typography.labelMedium
                )

            }
            Spacer(
                modifier = Modifier.height(12.dp)
            )

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
                modifier = Modifier.height(16.dp)
            )
//            Text(
//                text = "Prioridad: ${task.priority.displayName()}"
//            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isCompleted, onCheckedChange = {
                        onToggleCompleted(task.id)
                    })

                Spacer(
                    modifier = Modifier.weight(1f)
                )
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
}