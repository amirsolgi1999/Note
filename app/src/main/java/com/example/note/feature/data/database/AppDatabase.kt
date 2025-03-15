package com.example.note.feature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.model.Task


@Database(
    entities = [
        Task::class,
        Note::class
    ],
    version = 1
)
abstract class AppDatabase :RoomDatabase(){

    abstract val taskDao:TaskDao
    abstract val noteDao:NoteDao

    companion object{
        const val DATABASENAME = "Note"
    }
}