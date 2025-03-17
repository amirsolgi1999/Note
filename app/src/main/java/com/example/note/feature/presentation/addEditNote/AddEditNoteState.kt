package com.example.note.feature.presentation.addEditNote

data class AddEditNoteState(
    val text: String = "",
    val isHintVisible: Boolean = true,
    var isFavorite: Boolean = false,
)