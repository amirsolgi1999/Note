package com.example.note.feature.presentation.favorite.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mnote.feature.presentation.favorite.tabScreens.FavoriteNote.TabFavoriteNote
import com.example.note.feature.presentation.favorite.FavoriteViewModel
import com.example.note.feature.presentation.favorite.tabScreen.FavoriteTask.TabFavoriteTask
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Primary
import com.example.note.ui.theme.Surface


@SuppressLint("UnrememberedMutableState")
@Composable
fun TabScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {

    val tabIndex = viewModel.tabIndex.observeAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex.value!!,
        ) {
            viewModel.tabs.forEachIndexed { index, title ->
                val isSelected = tabIndex.value!! == index

                Column(modifier = Modifier.background(Surface)) {
                    Tab(
                        modifier = Modifier
                            .background(Primary)
                            .height(50.dp),
                        text = {
                            Text(
                                text = title,
                                color =
                                if (isSelected) OnPrimary else Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        },

                        selected = isSelected,
                        onClick = {
                            viewModel.updateTabIndex(index)
                        }
                    )
                }

            }
        }

        when (tabIndex.value) {
            0 -> TabFavoriteNote(navController = navController)
            1 -> TabFavoriteTask(navController = navController)
        }
    }
}