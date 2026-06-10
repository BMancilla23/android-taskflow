package com.bryan.taskflow.domain.model

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val priority: TaskPriority,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)