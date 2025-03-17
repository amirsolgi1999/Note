package com.example.note.feature.presentation.utils.empty_ui_states

import com.example.note.feature.domain.model.Task


sealed class EmptyUiStateTask {
    object Loading : EmptyUiStateTask()
    data class Content(val tasks: List<Task>) : EmptyUiStateTask()
    object Empty : EmptyUiStateTask()
}