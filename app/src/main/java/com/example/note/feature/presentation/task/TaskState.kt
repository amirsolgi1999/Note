package com.example.note.feature.presentation.task

import com.example.note.feature.domain.model.Task

data class TaskState (
    val tasks:List<Task> = emptyList(),
    val isLoading:Boolean=false,
    var isDone:Boolean=false,
    var isFavorite:Boolean=false,
    var searchQuery:String=""
)