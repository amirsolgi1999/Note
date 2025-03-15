package com.example.note.feature.domain.usecases.taskUsecase

import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.repo.TaskRepository

class DeleteTask(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(task: Task){
        repository.deleteTask(task)
    }
}