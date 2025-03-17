package com.example.note.feature.presentation.addEditTask

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.presentation.addEditTask.components.AddEditTaskTopBar
import com.example.note.feature.presentation.utils.rememberImeState
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Primary
import com.example.note.ui.theme.Surface
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {

    val taskTitleState = viewModel.taskTitle.value
    val taskDescriptionState = viewModel.description.value

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {

                is AddEditTaskViewModel.UiEvent.PopBackStack -> {
                    navController.popBackStack()
                }

                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.popBackStack()
                }

                is AddEditTaskViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Scaffold(
        topBar = {
            AddEditTaskTopBar(
                navController = navController
            )
        },
        modifier = Modifier
            .background(Surface)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Surface),
        ) {

            item {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 65.dp)
                        .fillMaxSize()
                ) {

                    TextField(
                        value = taskTitleState.text,
                        onValueChange = {
                            viewModel.onEvent(AddEditTaskEvent.EnteredTaskTitle(it))
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Surface,
                            unfocusedContainerColor = Surface,
                            disabledContainerColor = Surface,
                            disabledIndicatorColor = Primary,
                            focusedIndicatorColor = Primary,
                            unfocusedIndicatorColor = Primary,
                        ),
                        singleLine = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Default
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.title),
                                fontWeight = FontWeight.Light,
                                color = OnPrimary,
                                fontStyle = FontStyle.Normal,
                                fontSize = 20.sp,
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                    )

                    Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface)

                    TextField(
                        value = taskDescriptionState.text,
                        onValueChange = {
                            viewModel.onEvent(AddEditTaskEvent.EnteredDescription(it))
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Surface,
                            unfocusedContainerColor = Surface,
                            disabledContainerColor = Surface,
                            disabledIndicatorColor = Primary,
                            focusedIndicatorColor = Primary,
                            unfocusedIndicatorColor = Primary,
                        ),
                        singleLine = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Default
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.description),
                                fontWeight = FontWeight.Light,
                                color = OnPrimary,
                                fontStyle = FontStyle.Normal,
                                fontSize = 15.sp,
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}