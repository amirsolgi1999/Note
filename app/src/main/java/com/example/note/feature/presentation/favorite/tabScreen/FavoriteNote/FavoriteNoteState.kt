package com.example.note.feature.presentation.favorite.tabScreen.FavoriteNote

import com.example.note.feature.domain.model.Note

data class FavoriteNoteState(
    val notes: List<Note> = emptyList(),
)
