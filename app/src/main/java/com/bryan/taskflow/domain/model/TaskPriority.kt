package com.bryan.taskflow.domain.model

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}

fun TaskPriority.displayName(): String {
    return when(this){
        TaskPriority.LOW -> "Baja"
        TaskPriority.MEDIUM -> "Media"
        TaskPriority.HIGH -> "Alta"
    }
}