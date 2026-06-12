package com.bryan.taskflow.presentation.task

import androidx.lifecycle.ViewModel
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(): ViewModel(){
    // Estado interno mutabla
    // Solo el ViewModel puede modificarlo
    private val _uiState = MutableStateFlow(
        TaskUiState(
            tasks = listOf(
                Task(
                    id = 1,
                    title = "Aprender Compose",
                    description = "Aprender estados y recomposición",
                    priority = TaskPriority.HIGH,
                    isCompleted = false,
                    createdAt = System.currentTimeMillis()
                ),
                Task(
                    id = 2,
                    title = "Aprender ViewModel",
                    description = "Gestionar estado de la UI",
                    priority = TaskPriority.MEDIUM,
                    isCompleted = false,
                    createdAt = System.currentTimeMillis()
                ),
                Task(
                    id = 3,
                    title = "Preparar Room",
                    description = "Persistencia local de datos",
                    priority = TaskPriority.LOW,
                    isCompleted = false,
                    createdAt = System.currentTimeMillis()
                )
            )
        )
    )
    // Exposición segura del estado.
    // La UI puede observar cambios pero no modificarlos.
    val uiState = _uiState.asStateFlow()

    fun onTaskTitleChange(title: String){
        _uiState.value = _uiState.value.copy(
            newTaskTitle = title
        )
    }

    fun onTaskDescriptionChange(description: String){
        _uiState.value = _uiState.value.copy(
            newTaskDescription = description
        )
    }

    fun onPriorityChange(priority: TaskPriority){
        _uiState.value = _uiState.value.copy(
            selectedPriority = priority
        )
    }

    fun addTask(){
        val title = _uiState.value.newTaskTitle
        val description = _uiState.value.newTaskDescription
        val priority = _uiState.value.selectedPriority


        if (title.isBlank()){
            return
        }

        val newTask = Task(
            id = (_uiState.value.tasks.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            description = description,
            priority = priority,
            isCompleted = false,
        )

        _uiState.value = _uiState.value.copy(
            tasks = _uiState.value.tasks + newTask,
            newTaskTitle = "",
            newTaskDescription = "",
            selectedPriority = TaskPriority.MEDIUM
        )
    }

    fun toggleTask(taskId: Long){
        _uiState.value = _uiState.value.copy(
            tasks = _uiState.value.tasks.map { task ->
                if(task.id == taskId){
                    task.copy(
                        isCompleted = !task.isCompleted
                    )
                }else {
                    task
                }
            }
        )
    }
}