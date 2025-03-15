package com.example.note.feature.domain.usecases.taskUsecase

import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.repo.TaskRepository

class GetTaskById(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(id:Int):Task?{
        return repository.getTaskById(id)
    }
}