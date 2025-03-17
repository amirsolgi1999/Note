package com.example.note.feature.presentation.favorite.tabScreen.FavoriteTask

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.usecases.taskUsecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _state = mutableStateOf(FavoriteTaskState())
    val state: State<FavoriteTaskState> = _state

    private var recentlyDeletedTask: Task? = null

    private var getTasksJob: Job? = null

    init {
        getFavoriteTasks()
    }

    fun onEvent(event: FavoriteTaskEvent) {
        when (event) {

            is FavoriteTaskEvent.OnFavoriteChangeTask -> {
                viewModelScope.launch {
                    taskUseCases.insertTask(
                        event.task.copy(
                            isFavorite = event.isFavorite,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }

            is FavoriteTaskEvent.OnDeleteTaskClick -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                    recentlyDeletedTask = event.task
                }
            }

            is FavoriteTaskEvent.OnDoneChangeTask -> {
                viewModelScope.launch {
                    taskUseCases.insertTask(
                        event.task.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            is FavoriteTaskEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.insertTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }
        }
    }

    fun getFavoriteTasks() {
        viewModelScope.launch {

            getTasksJob?.cancel()
            getTasksJob = taskUseCases.getTasks()
                .onEach { tasks ->
                    _state.value = state.value.copy(
                        tasks = tasks
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}