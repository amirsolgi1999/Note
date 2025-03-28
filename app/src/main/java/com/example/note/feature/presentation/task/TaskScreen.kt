package com.example.note.feature.presentation.task

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.domain.util.SortOrder
import com.example.note.feature.domain.util.convertLongToTime
import com.example.note.feature.navigation.Screen
import com.example.note.feature.presentation.task.components.TaskItem
import com.example.note.feature.presentation.task.components.TaskSearchBar
import com.example.note.feature.presentation.task.components.TaskTopBar
import com.example.note.feature.presentation.utils.CustomSnackBar
import com.example.note.ui.theme.Surface
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state = viewModel.state.value
    val searchedText = state.searchQuery

    val tasks = state.tasks
    var currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.ModifiedDescending) }

    val sortedTasks = when (currentSortOrder) {
        SortOrder.ModifiedDescending -> tasks.sortedByDescending { it.timestamp }
        SortOrder.ModifiedAscending -> tasks.sortedBy { it.timestamp }
        SortOrder.SortIsDones -> tasks.sortedByDescending { it.isDone }
        SortOrder.AlphabeticalAZ -> tasks.sortedBy { it.title[0] }
        SortOrder.AlphabeticalZA -> tasks.sortedByDescending { it.title[0] }
    }

    val vibrator = context.getSystemService(Vibrator::class.java)

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getTasks()
            delay(300)
        }
    }

    Scaffold(
        topBar = {
            TaskTopBar(
                navController = navController
            )
        },

        snackbarHost = {
            SnackbarHost(
                snackBarHostState
            ) {
                CustomSnackBar(
                    snackBarHostState = snackBarHostState,
                    message = stringResource(R.string.task_deleted),
                    actionLabel = stringResource(R.string.undo),
                    actionIcon = Icons.Default.Undo,
                    durationInSeconds = 5,
                    actionOnClick = {
                        viewModel.onEvent(TaskEvent.RestoreTask)
                    }
                )
            }
        }
    ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Surface),
                contentPadding = PaddingValues(
                    top = 25.dp,
                    bottom = 70.dp
                ),
            ) {

                item {
                    Text(
                        text = stringResource(R.string.tasks),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, top = 30.dp, bottom = 15.dp)
                    )
                }

                item {
                    TaskSearchBar(
                        ModifiedDescending = {
                            currentSortOrder = SortOrder.ModifiedDescending
                        },
                        ModifiedAscending = {
                            currentSortOrder = SortOrder.ModifiedAscending
                        },
                        SortIsDones = {
                            currentSortOrder = SortOrder.SortIsDones
                        },
                        TitleAZ = {
                            currentSortOrder = SortOrder.AlphabeticalAZ
                        },
                        TitleZA = {
                            currentSortOrder = SortOrder.AlphabeticalZA
                        },
                    )
                }

                val searchSortTasks = state.tasks.filter { task ->
                    task.title.contains(searchedText, ignoreCase = true)
                }

                if (searchedText.isEmpty()) {
                    items(
                        sortedTasks,
                        key = { task ->
                            task.id!!.toInt()
                        }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onDeleteClick = {
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

                                viewModel.onEvent(TaskEvent.OnDeleteTaskClick(task))
                                scope.launch {

                                    val result = snackBarHostState.showSnackbar(
                                        message = "Task was deleted!",
                                        actionLabel = "UNDO"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(TaskEvent.RestoreTask)
                                    }
                                }
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
                                            "Nope"
                                        }
                                        val parsedTime =
                                            convertLongToTime(task.timestamp)

                                        val sharingText = """
                                        Task: ${task.title}
                                        Description: ${task.description}
                                        Completed: $isDoneForSharing
                                        Date: ${parsedTime}
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
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                } else {
                    items(
                        searchSortTasks,
                        key = { task ->
                            task.id!!.toInt()
                        }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onDeleteClick = {
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

                                viewModel.onEvent(TaskEvent.OnDeleteTaskClick(task))
                                scope.launch {

                                    val result = snackBarHostState.showSnackbar(
                                        message = "Task was deleted!",
                                        actionLabel = "UNDO",
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(TaskEvent.RestoreTask)
                                    }
                                }
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
                                        Task: ${task.title}
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
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }

    }
}