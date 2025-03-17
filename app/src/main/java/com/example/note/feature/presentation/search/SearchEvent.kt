package com.example.note.feature.presentation.search


sealed class SearchEvent {
    data class Search(val query: String):SearchEvent()
    object ClearSearch:SearchEvent()
}