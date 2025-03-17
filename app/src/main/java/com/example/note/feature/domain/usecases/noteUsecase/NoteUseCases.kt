package com.example.note.feature.domain.usecases.noteUsecase

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val insertNote: InsertNote,
    val getNote: GetNoteById,
)
