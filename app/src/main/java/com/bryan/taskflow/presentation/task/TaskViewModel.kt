package com.bryan.taskflow.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryan.taskflow.data.session.SessionManager
import com.bryan.taskflow.domain.model.Task
import com.bryan.taskflow.domain.model.TaskPriority
import com.bryan.taskflow.domain.repository.TaskRepository
import com.bryan.taskflow.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
): ViewModel(){
    // Estado interno mutabla
    // Solo el ViewModel puede modificarlo
    private val _uiState = MutableStateFlow(
        TaskUiState(
//            tasks = listOf(
//                Task(
//                    id = 1,
//                    title = "Aprender Compose",
//                    description = "Aprender estados y recomposición",
//                    priority = TaskPriority.HIGH,
//                    isCompleted = false,
//                    createdAt = System.currentTimeMillis()
//                ),
//                Task(
//                    id = 2,
//                    title = "Aprender ViewModel",
//                    description = "Gestionar estado de la UI",
//                    priority = TaskPriority.MEDIUM,
//                    isCompleted = false,
//                    createdAt = System.currentTimeMillis()
//                ),
//                Task(
//                    id = 3,
//                    title = "Preparar Room",
//                    description = "Persistencia local de datos",
//                    priority = TaskPriority.LOW,
//                    isCompleted = false,
//                    createdAt = System.currentTimeMillis()
//                )
//            )
        )
    )
    // Exposición segura del estado.
    // La UI puede observar cambios pero no modificarlos.
    val uiState = _uiState.asStateFlow()

    init {
        loadUser()
        loadTasks()
    }

    private fun loadUser(){
        viewModelScope.launch {
            val userId = sessionManager
                .getUserId()
                .firstOrNull()
                ?: return@launch

            val user = userRepository.getUserById(userId)
                ?: return@launch

            _uiState.value = _uiState.value.copy(
                username = user.fullName
            )
        }
    }

    private fun loadTasks(){
       viewModelScope.launch {
           val userId = sessionManager.getUserId()
               .firstOrNull()
               ?: return@launch

           taskRepository
               .getTasksByUser(userId)
               .collect { tasks ->
                   _uiState.value =
                       _uiState.value.copy(
                           tasks = tasks
                       )
               }
       }
    }

    private var currentTask: Task? = null

    fun loadTask(taskId: Long){
        viewModelScope.launch {
            val task = taskRepository
                .getTaskById(taskId)
                ?: return@launch

            currentTask = task

            _uiState.value = _uiState.value.copy(
                newTaskTitle = task.title,
                newTaskDescription = task.description,
                selectedPriority = task.priority
            )
        }
    }

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

//    fun startEdit(task: Task){
//        _uiState.value =
//            _uiState.value.copy(
//                editingTask = task,
//                newTaskTitle = task.title,
//                newTaskDescription = task.description,
//                selectedPriority = task.priority
//            )
//    }

//    fun cancelEdit(){
//        _uiState.value =
//            _uiState.value.copy(
//                editingTask = null,
//                newTaskTitle = "",
//                newTaskDescription = "",
//                selectedPriority = TaskPriority.MEDIUM
//            )
//    }

    fun createTask() {
        val state = _uiState.value

        if(state.newTaskTitle.isBlank()){
            return
        }

        viewModelScope.launch {
            val userId = sessionManager.getUserId()
                .firstOrNull()
                ?: return@launch

            taskRepository.createTask(
                title = state.newTaskTitle,
                description = state.newTaskDescription,
                priority = state.selectedPriority,
                userId = userId
            )
        }
    }

    fun updateTask(){
        val state = _uiState.value

        if(state.newTaskTitle.isBlank()){
            return
        }

        val task = currentTask ?: return

        viewModelScope.launch {
            val userId = sessionManager
                .getUserId()
                .firstOrNull()
                ?: return@launch

            taskRepository.updateTask(
                task.copy(
                    title = state.newTaskTitle,
                    description = state.newTaskDescription,
                    priority = state.selectedPriority
                ),
                userId
            )
            clearForm()
        }
    }

//    fun addTask(){
//        val state = _uiState.value
////        val title = _uiState.value.newTaskTitle
////        val description = _uiState.value.newTaskDescription
////        val priority = _uiState.value.selectedPriority
//
//
////        if (title.isBlank()){
////            return
////        }
//
//        if (state.newTaskTitle.isBlank()){
//            return
//        }
//
//        viewModelScope.launch {
//            val userId = sessionManager
//                .getUserId()
//                .firstOrNull()
//                ?: return@launch
//
//            taskRepository.createTask(
//                title = state.newTaskTitle,
//                description = state.newTaskDescription,
//                priority = state.selectedPriority,
//                userId = userId
//            )
//
//            _uiState.value =
//                state.copy(
//                    newTaskTitle = "",
//                    newTaskDescription = "",
//                    selectedPriority = TaskPriority.MEDIUM
//                )
//        }
//
////        val newTask = Task(
////            id = (_uiState.value.tasks.maxOfOrNull { it.id } ?: 0) + 1,
////            title = title,
////            description = description,
////            priority = priority,
////            isCompleted = false,
////        )
////
////        _uiState.value = _uiState.value.copy(
////            tasks = _uiState.value.tasks + newTask,
////            newTaskTitle = "",
////            newTaskDescription = "",
////            selectedPriority = TaskPriority.MEDIUM
////        )
//    }

//    fun saveTask(){
//        val state = _uiState.value
//
//        if(state.newTaskTitle.isBlank()){
//            return
//        }
//
//        viewModelScope.launch {
//            val userId = sessionManager
//                .getUserId()
//                .firstOrNull()
//                ?: return@launch
//
//            val editingTask = state.editingTask
//
//            if (editingTask == null){
//                taskRepository.createTask(
//                    title = state.newTaskTitle,
//                    description = state.newTaskDescription,
//                    priority = state.selectedPriority,
//                    userId = userId
//                )
//            } else {
//                taskRepository.updateTask(
//                    editingTask.copy(
//                        title = state.newTaskTitle,
//                        description = state.newTaskDescription,
//                        priority = state.selectedPriority,
//                    ),
//                    userId
//                )
//            }
//
//            cancelEdit()
//        }
//    }

    fun toggleTask(taskId: Long){
//        _uiState.value = _uiState.value.copy(
//            tasks = _uiState.value.tasks.map { task ->
//                if(task.id == taskId){
//                    task.copy(
//                        isCompleted = !task.isCompleted
//                    )
//                }else {
//                    task
//                }
//            }
//        )

        viewModelScope.launch {
            taskRepository.toggleCompleted(taskId)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            val userId = sessionManager
                .getUserId()
                .firstOrNull()
                ?:return@launch

            taskRepository.deleteTask(
                task,
                userId
            )
        }
    }

    private fun clearForm(){
        _uiState.value =
            _uiState.value.copy(
                newTaskTitle = "",
                newTaskDescription = "",
                selectedPriority = TaskPriority.MEDIUM
            )
    }

    fun logout(){
        viewModelScope.launch {
            sessionManager.clearSession()
        }
    }

    fun onSearchQueryChange(query: String){
        _uiState.value = _uiState.value.copy(
            searchQuery = query
        )
    }
}