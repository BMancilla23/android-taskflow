package com.bryan.taskflow.domain.model

data class Task(
    val id: Int,
    val title: String,
    val completed: Boolean = false
)