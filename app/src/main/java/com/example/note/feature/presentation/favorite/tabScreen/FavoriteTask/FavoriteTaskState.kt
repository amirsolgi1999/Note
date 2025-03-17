package com.example.note.feature.presentation.favorite.tabScreen.FavoriteTask

import com.example.note.feature.domain.model.Task

data class FavoriteTaskState(
    val tasks: List<Task> = emptyList(),
)
