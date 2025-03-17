package com.example.note.feature.presentation.note

import com.example.note.feature.domain.model.Note

sealed class NoteEvent {
    data class DeleteNote(val note: Note) : NoteEvent()
    data class OnFavoriteChange(val note: Note, val isFavorite: Boolean) : NoteEvent()
    data class Search(val query: String) : NoteEvent()


    object RestoreNote : NoteEvent()
    object ClearSearch : NoteEvent()


}