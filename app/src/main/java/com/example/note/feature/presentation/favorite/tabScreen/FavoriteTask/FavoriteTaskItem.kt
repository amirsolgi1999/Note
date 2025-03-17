package com.example.note.feature.presentation.favorite.tabScreen.FavoriteTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.R
import com.example.note.feature.domain.model.Task
import com.example.note.feature.presentation.utils.DeleteConfirmationDialog
import com.example.note.feature.presentation.utils.pulsateClick2
import com.example.note.ui.theme.Primary

@Composable
fun FavoriteTaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    viewModel: FavoriteTaskViewModel = hiltViewModel(),
) {

    if (task.isFavorite) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }


        var checked by remember {
            mutableStateOf(task.isDone)
        }

        val textDecoration =
            if (task.isDone) TextDecoration.LineThrough else TextDecoration.None



        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Row(verticalAlignment = Alignment.CenterVertically) {

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(Primary)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.Right,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(8f)) {
                            Text(
                                text = task.title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textDecoration = textDecoration,
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    top = 12.dp
                                )
                            )
                            if (task.description != null) {
                                Text(
                                    text = task.description,
                                    textDecoration = textDecoration,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp),
                                )
                            }
                        }


                        Column(modifier = Modifier.weight(1f)) {
                            Column(
                            ) {
                                IconToggleButton(
                                    checked = task.isFavorite,
                                    onCheckedChange = { isChecked ->
                                        viewModel.onEvent(
                                            FavoriteTaskEvent.OnFavoriteChangeTask(
                                                task,
                                                isChecked
                                            )
                                        )
                                    },
                                    modifier = Modifier.pulsateClick2()
                                ) {
                                    Icon(
                                        imageVector = if (task.isFavorite) {
                                            Icons.Filled.Favorite
                                        } else {
                                            Icons.Filled.FavoriteBorder
                                        },
                                        tint = if (task.isFavorite) {
                                            MaterialTheme.colorScheme.inverseOnSurface
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        },
                                        contentDescription = stringResource(id = R.string.favorite_icon),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        deleteConfirmationRequired = true
                                    }
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.bin),
                                        contentDescription = stringResource(id = R.string.delete_note_dialog),
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            if (deleteConfirmationRequired) {
                                DeleteConfirmationDialog(
                                    onDeleteClick = {
                                        deleteConfirmationRequired = false
                                        onDeleteClick()
                                    },
                                    onDeleteCancel = {
                                        deleteConfirmationRequired = false
                                    },
                                    titleString = stringResource(id = R.string.delete_task),
                                    textString = stringResource(id = R.string.are_you_sure_to_delete_your_task)
                                )
                            }

                        }

                    }


                }
            }
        }
    }
}
