package com.bryan.taskflow.data.repository

import com.bryan.taskflow.data.local.dao.TaskDao
import com.bryan.taskflow.data.local.entity.TaskEntity
import com.bryan.taskflow.data.local.entity.toDomain
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl (
    private val taskDao: TaskDao
): TaskRepository {
    override fun getTasksByUser(userId: Long): Flow<List<Task>> {

       return taskDao
           .getTaskByUser(userId)
           .map { tasks ->
               tasks.map {
                   it.toDomain()
               }
           }
    }

    override suspend fun createTask(
        title: String,
        description: String,
        priority: TaskPriority,
        userId: Long
    ) {
        taskDao.insert(
            TaskEntity(
                userId = userId,
                title = title,
                description = description,
                priority = priority.name,
                completed = false
            )
        )
    }

    override suspend fun updateTask(
        task: Task,
        userId: Long
    ) {
        taskDao.update(
            TaskEntity(
                id = task.id,
                userId = userId,
                title = task.title,
                description = task.description,
                priority = task.priority.name,
                completed = task.isCompleted
            )
        )
    }

    override suspend fun deleteTask(task: Task, userId: Long) {
        taskDao.delete(
            TaskEntity(
                id = task.id,
                userId = userId,
                title = task.title,
                description = task.description,
                priority = task.priority.name,
                completed = task.isCompleted
            )
        )
    }

    override suspend fun toggleCompleted(taskId: Long) {
        val task = taskDao.getById(taskId)?:return

        taskDao.update(
            task.copy(
                completed = !task.completed
            )
        )
    }
}
