package com.bryan.taskflow.data.local.entity

import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        priority = TaskPriority.valueOf(priority),
        isCompleted =  completed
    )
}

fun Task.toEntity(
    userId: Long
): TaskEntity {
    return TaskEntity(
        id = id,
        userId = userId,
        title = title,
        description = description,
        priority = priority.name,
        completed = isCompleted
    )
}