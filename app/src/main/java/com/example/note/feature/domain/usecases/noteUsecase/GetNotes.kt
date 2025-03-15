package com.example.note.feature.domain.usecases.noteUsecase

import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.repo.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository: NoteRepository,
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}