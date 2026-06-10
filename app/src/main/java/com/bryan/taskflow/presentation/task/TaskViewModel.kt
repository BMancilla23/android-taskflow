package com.bryan.taskflow.presentation.task

import androidx.lifecycle.ViewModel
import com.bryan.taskflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel: ViewModel(){
    // Estado interno mutabla
    // Solo el ViewModel puede modificarlo
    private val _uiState = MutableStateFlow(
        TaskUiState(
            tasks = listOf(
                Task(1, "Aprender Compose"),
                Task(2, "Aprender ViewModel"),
                Task(3, "Preparar Room")
            )
        )
    )
    // Exposición segura del estado.
    // La UI puede observar cambios pero no modificarlos.
    val uiState = _uiState.asStateFlow()
}