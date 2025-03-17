package com.example.note.feature.presentation.addEditTask

data class AddEditTaskState(
    val text : String ="",
    val isHintVisible:Boolean = true,
    val isDone:Boolean=false,
    val isFavorite:Boolean=false
)
