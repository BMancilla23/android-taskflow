package com.bryan.taskflow.domain.repository

import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasksByUser(
        userId: Long
    ): Flow<List<Task>>

    suspend fun createTask(
        title: String,
        description: String,
        priority: TaskPriority,
        userId: Long
    )

    suspend fun updateTask(
        task: Task,
        userId: Long
    )

    suspend fun deleteTask(
        task: Task,
        userId: Long
    )

    suspend fun toggleCompleted(
        taskId: Long
    )

}