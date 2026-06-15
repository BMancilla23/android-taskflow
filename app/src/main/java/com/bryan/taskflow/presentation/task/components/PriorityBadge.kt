package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.model.displayName

@Composable
fun PriorityBadge(
    priority: TaskPriority
){
    val color = when (priority){
        TaskPriority.HIGH
                -> MaterialTheme.colorScheme.errorContainer

        TaskPriority.MEDIUM
                -> MaterialTheme.colorScheme.secondaryContainer

        TaskPriority.LOW
                -> MaterialTheme.colorScheme.tertiaryContainer
    }
    Surface(
        color = color,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = priority.displayName(),
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 4.dp
            ),
            style = MaterialTheme.typography.labelMedium
        )
    }
}