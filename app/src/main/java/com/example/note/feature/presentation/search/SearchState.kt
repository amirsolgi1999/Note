package com.example.note.feature.presentation.search

import com.example.note.feature.domain.model.Note

data class SearchState (
    var notes : List<Note> = emptyList(),
    var searchQuery : String = "",
    var isLoading : Boolean = false
)