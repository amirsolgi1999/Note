package com.example.note.feature.domain.repo

import com.example.note.feature.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks():Flow<List<Task>>

    suspend fun getTaskById(id:Int):Task?

    suspend fun deleteTask(task: Task)

    suspend fun insertTask(task: Task)
}