package com.bryan.taskflow.presentation.task

import androidx.room.Query
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val searchQuery: String = "",

    val newTaskTitle: String = "",
    val newTaskDescription: String = "",
    val selectedPriority: TaskPriority = TaskPriority.MEDIUM,
    val isLoading: Boolean = false,
    val error: String? = null,

    val username: String = ""
)
