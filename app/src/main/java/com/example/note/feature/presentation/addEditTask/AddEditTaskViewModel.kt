package com.example.note.feature.presentation.addEditTask

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.feature.domain.model.InvalidTaskException
import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.usecases.taskUsecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    var task by mutableStateOf<Task?>(null)
        private set

    private val _taskTitle = mutableStateOf(AddEditTaskState())
    val taskTitle:State<AddEditTaskState> =_taskTitle

    private val _description = mutableStateOf(AddEditTaskState())
    val description : State<AddEditTaskState> = _description

    private val _isDone  = mutableStateOf(AddEditTaskState())
    val isDone : State<AddEditTaskState> = _isDone

    private val _isFavorite = mutableStateOf(AddEditTaskState())
    val isFavorite : State<AddEditTaskState> = _isFavorite

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId:Int? =null


    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1){
                viewModelScope.launch {
                    taskUseCase.getTask(taskId)?.also { task ->
                        currentTaskId = task.id

                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )

                        _description.value=description.value.copy(
                            text = task.description ?: "",
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent){
        when(event){
            is AddEditTaskEvent.EnteredTaskTitle -> {
                _taskTitle.value=taskTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeTaskTitleFocus -> {
                _taskTitle.value=taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                    taskTitle.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.EnteredDescription -> {
                _description.value=description.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeDescriptionFocus -> {
                _description.value=description.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                    description.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCase.insertTask(
                            Task(
                                title = taskTitle.value.text,
                                description = description.value.text,
                                timestamp = System.currentTimeMillis(),
                                isDone = task?.isDone ?: false,
                                isFavorite = task?.isFavorite ?: false,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    }
                    catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save the task"
                            )
                        )
                    }
                }
            }
        }
    }
    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object PopBackStack : UiEvent()
        object SaveTask : UiEvent()
    }
}