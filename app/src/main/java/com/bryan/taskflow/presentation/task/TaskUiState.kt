package com.bryan.taskflow.presentation.task

import com.bryan.taskflow.domain.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList()
)
