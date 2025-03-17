package com.example.note.di

import android.app.Application
import androidx.room.Room
import com.example.note.feature.data.database.AppDatabase
import com.example.note.feature.data.repository.NoteRepositoryImpl
import com.example.note.feature.data.repository.TaskRepositoryImpl
import com.example.note.feature.domain.repo.NoteRepository
import com.example.note.feature.domain.repo.TaskRepository
import com.example.note.feature.domain.usecases.noteUsecase.InsertNote
import com.example.note.feature.domain.usecases.noteUsecase.DeleteNote
import com.example.note.feature.domain.usecases.noteUsecase.GetNoteById
import com.example.note.feature.domain.usecases.noteUsecase.GetNotes
import com.example.note.feature.domain.usecases.noteUsecase.NoteUseCases
import com.example.note.feature.domain.usecases.taskUsecase.InsertTask
import com.example.note.feature.domain.usecases.taskUsecase.DeleteTask
import com.example.note.feature.domain.usecases.taskUsecase.GetTaskById
import com.example.note.feature.domain.usecases.taskUsecase.GetTasks
import com.example.note.feature.domain.usecases.taskUsecase.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASENAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepo(db:AppDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideTaskRepo(db: AppDatabase):TaskRepository{
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repo: TaskRepository): TaskUseCase {
        return TaskUseCase(
            getTasks = GetTasks(repo),
            deleteTask = DeleteTask(repo),
            getTask = GetTaskById(repo),
            insertTask = InsertTask(repo)
        )
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            insertNote = InsertNote(repository),
            getNote = GetNoteById(repository)
        )
    }

}