package com.example.note.feature.data.repository

import com.example.note.feature.data.database.TaskDao
import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao

):TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

}