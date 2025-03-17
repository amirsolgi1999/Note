package com.example.note.feature.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.note.feature.navigation.bottom_navigation.BottomBar
import com.example.note.feature.navigation.graphs.HomeNavGraph

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        HomeNavGraph(navController = navController)
    }
}