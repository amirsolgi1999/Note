package com.example.note.feature.domain.usecases.taskUsecase

import com.example.note.feature.domain.model.InvalidTaskException
import com.example.note.feature.domain.model.Task
import com.example.note.feature.domain.repo.TaskRepository
import kotlin.jvm.Throws

class InsertTask (
    private val repository: TaskRepository
){

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task){
        if (task.title.isBlank())
            throw InvalidTaskException("The title can't be empty!")

        repository.insertTask(task)
    }
}