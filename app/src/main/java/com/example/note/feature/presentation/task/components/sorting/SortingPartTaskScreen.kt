package com.example.note.feature.presentation.task.components.sorting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.R
import com.example.note.feature.presentation.utils.clickableWithoutRipple
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingPartTaskScreen(
    ModifiedDescending: () -> Unit,
    ModifiedAscending: () -> Unit,
    SortIsDones: () -> Unit,
    TitleAZ: () -> Unit,
    TitleZA: () -> Unit,
) {

    var openBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Icon(
        painter = painterResource(id = R.drawable.filter),
        tint = OnPrimary,
        contentDescription = stringResource(id = R.string.sort_icon),
        modifier = Modifier
            .size(25.dp)
            .clickableWithoutRipple {
                openBottomSheet = true
            }
    )


    if (openBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openBottomSheet = false },
            dragHandle = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Surface),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    BottomSheetDefaults.DragHandle(
                        modifier = Modifier
                            .width(30.dp)
                            .height(2.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.sort),
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        ) {
            SortBottomSheetContentTask(
                ModifiedDescending = {
                    ModifiedDescending()
                },
                ModifiedAscending = {
                    ModifiedAscending()
                },
                SortIsDones = {
                    SortIsDones()
                },
                TitleAZ = {
                    TitleAZ()
                },
                TitleZA = {
                    TitleZA()
                }
            )
        }
    }
}