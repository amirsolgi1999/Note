package com.example.note.feature.domain.usecases.noteUsecase


import com.example.note.feature.domain.model.InvalidNoteException
import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.repo.NoteRepository

class InsertNote(
    private val repository: NoteRepository,
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("The title can't be empty!")
        if (note.description.isBlank())
            throw InvalidNoteException("The description can't be empty!")

        repository.insertNote(note)
    }
}