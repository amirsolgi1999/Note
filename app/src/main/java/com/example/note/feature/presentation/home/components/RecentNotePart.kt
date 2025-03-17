package com.example.note.feature.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.domain.util.SortOrder
import com.example.note.feature.navigation.Screen
import com.example.note.feature.presentation.home.components.emptyScreen.EmptyRecentScreenUi
import com.example.note.feature.presentation.note.NoteViewModel
import com.example.note.feature.presentation.utils.clickableWithoutRipple
import com.example.note.feature.presentation.utils.empty_ui_states.EmptyUiStateNote

@Composable
fun RecentNotePart(
    navController: NavController
){

    val viewModel: NoteViewModel = hiltViewModel()

    val noteScreenState by viewModel.noteScreenState.observeAsState()

    val state = viewModel.state.value
    val notes = state.notes

    val currentSortOrder by remember {
        mutableStateOf<SortOrder>(SortOrder.ModifiedAscending)
    }

    val sortedNotes = when (currentSortOrder){
        SortOrder.ModifiedDescending -> notes.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> notes.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> notes.sortedByDescending { it.isFavorite }
        SortOrder.AlphabeticalAZ -> notes.sortedBy { it.title.length }
        SortOrder.AlphabeticalZA -> notes.sortedByDescending { it.title.length }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp, start = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = stringResource(R.string.recent_notes),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        when ( noteScreenState){
            is EmptyUiStateNote.Loading -> {
                androidx.compose.material3.CircularProgressIndicator()
            }
            is EmptyUiStateNote.Content -> {
                val notes = (noteScreenState as EmptyUiStateNote.Content).notes

                LazyRow (
                    contentPadding = PaddingValues(end = 10.dp, start = 10.dp)
                ){
                    items(
                        sortedNotes.takeLast(5),
                        key = { note ->
                            note.id!!.toInt()
                        }
                    ){note ->
                        HomeNoteItem(
                            note = note,
                            modifier = Modifier
                                .clickableWithoutRipple(
                                    onClick = {
                                        navController.navigate(
                                            Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}"
                                        )
                                    }
                                )
                        )
                    }
                }
            }

            is EmptyUiStateNote.Empty -> {
                EmptyRecentScreenUi()
            }

            else -> {}
        }
    }
}