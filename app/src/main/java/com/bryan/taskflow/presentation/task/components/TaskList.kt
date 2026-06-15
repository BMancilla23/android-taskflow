package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.ui.components.EmptyState

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onToggleTask: (Long) -> Unit,
    onEditTask: (Long) -> Unit,
    onDeleteTask: (Task) -> Unit
){

    if (tasks.isEmpty()){
        EmptyState(
            title = "No tienes tareas",
            description = "Crea tu primera tarea para comenzar",
            modifier = modifier.fillMaxSize()
        )
        return
    }

        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks){
                    task ->
                TaskItem(
                    task = task,
                    onToggleCompleted = onToggleTask,
                    onEdit = {
                        onEditTask(task.id)
                    },
                    onDelete = onDeleteTask
                )
            }
    }
}
