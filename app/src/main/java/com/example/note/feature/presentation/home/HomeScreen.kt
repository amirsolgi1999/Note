package com.example.note.feature.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.feature.presentation.home.components.CategoriesPart
import com.example.note.feature.presentation.home.components.GoToRecent
import com.example.note.feature.presentation.home.components.OngoingTasksPart
import com.example.note.feature.presentation.home.components.RecentNotePart

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
) {



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(com.example.note.ui.theme.Surface)
    ) {

        Scaffold(
            modifier = Modifier
                .padding(bottom = 60.dp),
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(com.example.note.ui.theme.Surface),
                contentPadding = PaddingValues(
                    top = 15.dp,
                    bottom = 80.dp
                ),
            ) {


                item {
                    CategoriesPart(
                        navController = navController
                    )
                }
                item {
                    GoToRecent(navController = navController)
                }

                item {
                    RecentNotePart(
                        navController = navController
                    )
                }

                item {
                    OngoingTasksPart(
                        navController = navController
                    )
                }
            }
        }
    }
}