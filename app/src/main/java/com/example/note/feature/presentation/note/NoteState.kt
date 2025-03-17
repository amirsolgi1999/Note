package com.example.note.feature.presentation.note

import com.example.note.feature.domain.model.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val isOrderSectionVisible: Boolean = false,
    val isLoading: Boolean = false,
    var searchQuery: String = "",
)