package com.example.note.feature.domain.usecases.taskUsecase

data class TaskUseCase(
    val insertTask:InsertTask,
    val deleteTask: DeleteTask,
    val getTask: GetTaskById,
    val getTasks: GetTasks,
)