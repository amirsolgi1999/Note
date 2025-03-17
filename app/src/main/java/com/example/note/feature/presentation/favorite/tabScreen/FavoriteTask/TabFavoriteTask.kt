package com.example.note.feature.presentation.favorite.tabScreen.FavoriteTask

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import com.example.note.feature.domain.util.convertLongToTime
import com.example.note.feature.navigation.Screen
import com.example.note.ui.theme.Surface

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabFavoriteTask(
    navController: NavController,
    viewModel: FavoriteTaskViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    val tasks = state.tasks
    var currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.ModifiedDescending) }

    val sortedTasks = when (currentSortOrder) {
        SortOrder.ModifiedDescending -> tasks.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> tasks.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> tasks.sortedByDescending { it.isFavorite }
        SortOrder.AlphabeticalAZ -> tasks.sortedBy { it.title[0] }
        SortOrder.AlphabeticalZA -> tasks.sortedByDescending { it.title[0] }
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
                    FavoriteTaskHeader(
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
                    sortedTasks,
                    key = { task ->
                        task.id!!.toInt()
                    }
                ) { task ->
                    FavoriteTaskItem(
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

                            viewModel.onEvent(FavoriteTaskEvent.OnDeleteTaskClick(task))
                        },
                        modifier = Modifier
                            .combinedClickable(
                                onClick = {
                                    navController.navigate(
                                        Screen.AddEditTaskScreen.route +
                                                "?taskId=${task.id}"
                                    )
                                },
                                onLongClick = {

                                    val isDoneForSharing = if (task.isDone) {
                                        "Yeah"
                                    } else {
                                        "No"
                                    }

                                    val sharingText = """
                                        Favorite task: ${task.title}
                                        Description: ${task.description}
                                        Completed: $isDoneForSharing                                                                  
                                        """.trimIndent()
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, sharingText)
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)

                                    context.startActivity(shareIntent)
                                }
                            )
                            .animateItemPlacement(
                                tween(durationMillis = 250)
                            ),
                        task = task
                    )
                }
            }

    }
}