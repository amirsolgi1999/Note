package com.example.note.feature.presentation.addEditNote

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredNoteTitle(val value: String) : AddEditNoteEvent()
    data class ChangeNoteTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredDescription(val value: String) : AddEditNoteEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddEditNoteEvent()

    object SaveNote : AddEditNoteEvent()
}