package com.example.note.feature.domain.usecases.taskUsecase

import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TaskRepository
) {

    operator fun invoke():Flow<List<Task>>{
       return repository.getTasks()
    }
}