package com.example.note.feature.domain.usecases.noteUsecase

import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.repo.NoteRepository

class GetNoteById(
    private val repository: NoteRepository,
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}