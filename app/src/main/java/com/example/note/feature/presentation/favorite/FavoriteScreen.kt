package com.example.note.feature.presentation.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.feature.domain.util.findActivity
import com.example.note.feature.presentation.favorite.components.FavoriteTopBar
import com.example.note.feature.presentation.favorite.components.TabScreen
import com.example.note.ui.theme.Surface

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {


    Scaffold(
        modifier = Modifier
            .padding(
                bottom = 60.dp
            ),

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            FavoriteTopBar()

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Surface)
                    .draggable(
                        state = viewModel.dragState.value!!,
                        orientation = Orientation.Horizontal,
                        onDragStarted = {

                        },
                        onDragStopped = {
                            viewModel.updateTabIndexBasedOnSwipe()
                        }
                    )
            ) {
                TabScreen(
                    navController = navController
                )
            }
        }
    }
}