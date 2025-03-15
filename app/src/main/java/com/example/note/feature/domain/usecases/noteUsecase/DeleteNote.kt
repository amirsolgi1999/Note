package com.example.note.feature.domain.usecases.noteUsecase

import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.repo.NoteRepository


class DeleteNote(
    private val repository: NoteRepository,
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}