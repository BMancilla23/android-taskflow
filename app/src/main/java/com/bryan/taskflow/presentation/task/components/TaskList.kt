package com.bryan.taskflow.presentation.task.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bryan.taskflow.domain.model.Task

@Composable
fun TaskList(
    tasks: List<Task>,
    onToggleTask: (Long) -> Unit,
    onEditTask: (Long) -> Unit,
    onDeleteTask: (Task) -> Unit
){

        LazyColumn(
            modifier = Modifier.fillMaxSize(),

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
