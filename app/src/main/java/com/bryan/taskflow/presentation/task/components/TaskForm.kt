package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.model.displayName

@Composable
fun TaskForm(
    title: String,
    description: String,
    priority: TaskPriority,
    buttonText: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPriorityChange: (TaskPriority) -> Unit,
    onSave: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = title, onValueChange = onTitleChange, label = {
                Text("Título")
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
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
        PrioritySelector(
            selectedPriority = priority,
            onPrioritySelected = onPriorityChange
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                buttonText
            )
        }
    }
}