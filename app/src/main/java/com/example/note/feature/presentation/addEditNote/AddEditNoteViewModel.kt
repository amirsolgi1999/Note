package com.example.note.feature.presentation.addEditNote

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.feature.domain.model.InvalidNoteException
import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.usecases.noteUsecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var note by mutableStateOf<Note?>(null)
        private set

    private val _titleState = mutableStateOf(AddEditNoteState())
    val titleState: State<AddEditNoteState> = _titleState

    private val _descriptionState = mutableStateOf(AddEditNoteState())
    val descriptionState: State<AddEditNoteState> = _descriptionState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _titleState.value = titleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _descriptionState.value = descriptionState.value.copy(
                            text = note.description,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredNoteTitle -> {
                _titleState.value = titleState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeNoteTitleFocus -> {
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            titleState.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredDescription -> {
                _descriptionState.value = descriptionState.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeDescriptionFocus -> {
                _descriptionState.value = descriptionState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            descriptionState.value.text.isBlank()
                )
            }


            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.insertNote(
                            Note(
                                title = titleState.value.text,
                                description = descriptionState.value.text,
                                timestamp = System.currentTimeMillis(),
                                isFavorite = note?.isFavorite ?: false,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save the note"
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
        object SaveNote : UiEvent()
    }
}