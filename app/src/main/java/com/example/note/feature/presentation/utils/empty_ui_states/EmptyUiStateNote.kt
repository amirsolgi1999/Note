package com.example.note.feature.presentation.utils.empty_ui_states

import com.example.note.feature.domain.model.Note


sealed class EmptyUiStateNote {
    object Loading : EmptyUiStateNote()
    data class Content(val notes: List<Note>) : EmptyUiStateNote()
    object Empty : EmptyUiStateNote()
}