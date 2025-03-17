package com.example.note.feature.presentation.favorite.tabScreen.FavoriteTask

import com.example.note.feature.domain.model.Task

sealed class FavoriteTaskEvent {

    object RestoreTask:FavoriteTaskEvent()

    data class OnDeleteTaskClick(val task: Task):FavoriteTaskEvent()
    data class OnDoneChangeTask(val task: Task,val isDone:Boolean):FavoriteTaskEvent()
    data class OnFavoriteChangeTask(val task: Task,val isFavorite:Boolean):FavoriteTaskEvent()
}