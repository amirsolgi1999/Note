package com.example.note.feature.presentation.addEditTask

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent {

    data class EnteredTaskTitle(val value:String):AddEditTaskEvent()
    data class ChangeTaskTitleFocus(val focusState: FocusState):AddEditTaskEvent()
    data class EnteredDescription(val value: String):AddEditTaskEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState):AddEditTaskEvent()
    object SaveTask:AddEditTaskEvent()
}