package com.example.note.feature.presentation.favorite.tabScreen.FavoriteNote

import com.example.note.feature.domain.model.Note

sealed class FavoriteNoteEvent {
    data class DeleteFavoriteNote(val note: Note) : FavoriteNoteEvent()
    data class OnFavoriteChangeNote(val note: Note, val isFavorite: Boolean) : FavoriteNoteEvent()

    object RestoreNote : FavoriteNoteEvent()

}