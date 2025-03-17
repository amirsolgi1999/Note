package com.example.note.feature.presentation.addEditTask.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.presentation.addEditNote.AddEditNoteViewModel
import com.example.note.feature.presentation.addEditTask.AddEditTaskEvent
import com.example.note.feature.presentation.addEditTask.AddEditTaskViewModel
import com.example.note.feature.presentation.utils.AnimatableEmptyDialog
import com.example.note.feature.presentation.utils.clickableWithoutRipple
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Surface
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskTopBar(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
){

    val taskTitleState = viewModel.taskTitle.value
    val taskDescriptionState = viewModel.description.value

    var emptyStateDialogOpened by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect (emptyStateDialogOpened){
        if (taskTitleState.text.isEmpty()){
            delay(2500)
            emptyStateDialogOpened=false
        }
    }

    androidx.compose.material.TopAppBar(
        backgroundColor = Surface,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.cross),
                contentDescription = stringResource(id = R.string.close),
                tint = OnPrimary,
                modifier = Modifier
                    .size(16.dp)
                    .clickableWithoutRipple {
                        navController.navigateUp()
                    }
            )


            Text(
                text = stringResource(id = R.string.task),
                fontSize = 27.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Icon(
                painter = painterResource(id = R.drawable.done),
                contentDescription = stringResource(id = R.string.done_icon),
                tint = OnPrimary,
                modifier = Modifier
                    .size(20.dp)
                    .clickableWithoutRipple {
                        if (taskTitleState.text.isEmpty() &&
                            (taskDescriptionState.text.isEmpty() || taskDescriptionState.text.isNotEmpty())
                        ) {
                            emptyStateDialogOpened = true
                        } else {
                            viewModel.onEvent(AddEditTaskEvent.SaveTask)
                        }
                    }
            )

            if (emptyStateDialogOpened){

                val animateTrigger = remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(key1 = Unit) {
                    launch {
                        delay(100)
                        animateTrigger.value=true
                    }

                }

                Dialog(
                    onDismissRequest = {
                    emptyStateDialogOpened=false
                    }
                ) {
                    (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)

                    AnimatableEmptyDialog(visible = animateTrigger.value) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .border(
                                    3.dp,
                                    MaterialTheme.colorScheme.onSurface.copy(0.6f),
                                    RoundedCornerShape(55.dp)
                                )
                                .clip(RoundedCornerShape(55.dp))
                                .background(MaterialTheme.colorScheme.error.copy(0.6f))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cancel),
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(50.dp)
                                )
                                androidx.compose.material3.Text(
                                    text = stringResource(id = R.string.title_can_t_be_empty),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 26.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}