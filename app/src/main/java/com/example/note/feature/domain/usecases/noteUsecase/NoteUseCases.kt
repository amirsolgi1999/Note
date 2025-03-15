package com.example.note.feature.domain.usecases.noteUsecase

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNoteById,
)
