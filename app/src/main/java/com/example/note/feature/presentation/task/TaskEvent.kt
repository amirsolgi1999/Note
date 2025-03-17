package com.example.note.feature.presentation.task

import com.example.note.feature.domain.model.Task

sealed class TaskEvent {
    data class OnDeleteTaskClick(val task: Task):TaskEvent()
    data class OnDoneChange(val task: Task,val isDone:Boolean):TaskEvent()
    data class OnFavoriteChange(val task: Task,val isFavorite:Boolean):TaskEvent()
    data class Search(val query:String):TaskEvent()

    object RestoreTask:TaskEvent()
    object ClearSearch:TaskEvent()
}