package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.model.displayName

@Composable
fun PrioritySelector(
    selectedPriority: TaskPriority,
    onPrioritySelected: (TaskPriority) -> Unit
){
    Row{
        TaskPriority.entries.forEach {
            priority ->
            FilterChip(
                selected = selectedPriority == priority,
                onClick = {
                    onPrioritySelected
                },
                label = {
                    Text(priority.displayName())
                }
            )
        }
    }
}