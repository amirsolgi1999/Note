package com.example.note.feature.domain.util

sealed class SortOrder() {

    object ModifiedDescending : SortOrder()
    object ModifiedAscending : SortOrder()
    object SortIsDones : SortOrder()
    object AlphabeticalAZ : SortOrder()
    object AlphabeticalZA : SortOrder()
}