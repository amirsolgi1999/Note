package com.example.mnote.feature.presentation.favorite.tabScreens.FavoriteNote

import android.annotation.SuppressLint
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.feature.domain.util.SortOrder
import com.example.note.feature.navigation.Screen
import com.example.note.feature.presentation.favorite.tabScreen.FavoriteNote.FavoriteNoteEvent
import com.example.note.feature.presentation.favorite.tabScreen.FavoriteNote.FavoriteNoteHeader
import com.example.note.feature.presentation.favorite.tabScreen.FavoriteNote.FavoriteNoteItem
import com.example.note.feature.presentation.favorite.tabScreen.FavoriteNote.FavoriteNoteViewModel
import com.example.note.feature.presentation.utils.clickableWithoutRipple
import com.example.note.ui.theme.Surface

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabFavoriteNote(
    navController: NavController,
    viewModel: FavoriteNoteViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    val notes = state.notes
    var currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.ModifiedDescending) }

    val sortedNotes = when (currentSortOrder) {
        SortOrder.ModifiedDescending -> notes.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> notes.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> notes.sortedByDescending { it.isFavorite }
        SortOrder.AlphabeticalAZ -> notes.sortedBy { it.title[0] }
        SortOrder.AlphabeticalZA -> notes.sortedByDescending { it.title[0] }
    }

    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)

    Scaffold(
        modifier = Modifier
            .background(Surface)
    ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Surface),
                contentPadding = PaddingValues(
                    top = 15.dp,
                    bottom = 80.dp
                ),
            ) {
                item {
                    FavoriteNoteHeader(
                        ModifiedDescending = {
                            currentSortOrder = SortOrder.ModifiedDescending
                        },
                        ModifiedAscending = {
                            currentSortOrder = SortOrder.ModifiedAscending
                        },
                        TitleAZ = {
                            currentSortOrder = SortOrder.AlphabeticalAZ
                        },
                        TitleZA = {
                            currentSortOrder = SortOrder.AlphabeticalZA
                        },
                    )
                }

                items(
                    sortedNotes,
                    key = { note ->
                        note.id!!.toInt()
                    }
                ) { note ->
                    FavoriteNoteItem(
                        onDeleteClick = {         // vibration
                            val vibrationEffect1: VibrationEffect =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    VibrationEffect.createOneShot(
                                        50,
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                } else {
                                    TODO("VERSION.SDK_INT < O")
                                }

                            vibrator.cancel()
                            vibrator.vibrate(vibrationEffect1)

                            viewModel.onEvent(FavoriteNoteEvent.DeleteFavoriteNote(note))
                        },
                        modifier = Modifier
                            .clickableWithoutRipple {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}"
                                )
                            }
                            .animateItemPlacement(
                                tween(durationMillis = 250)
                            ),
                        note = note
                    )
                }
            }
        }
}