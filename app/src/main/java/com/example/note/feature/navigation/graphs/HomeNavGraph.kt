package com.example.note.feature.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.note.feature.navigation.Screen
import com.example.note.feature.navigation.bottom_navigation.BottomNavItems
import com.example.note.feature.presentation.addEditNote.AddEditNoteScreen
import com.example.note.feature.presentation.addEditTask.AddEditTaskScreen
import com.example.note.feature.presentation.favorite.FavoriteScreen
import com.example.note.feature.presentation.home.HomeScreen
import com.example.note.feature.presentation.note.NoteScreen
import com.example.note.feature.presentation.search.SearchScreen
import com.example.note.feature.presentation.task.TaskScreen


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun HomeNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavItems.HomeScreen.route
    ) {

        composable(
            route = BottomNavItems.HomeScreen.route,
        ) {
            HomeScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.NotesScreen.route,
        ) {
            NoteScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.AddEditNoteScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            AddEditNoteScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.TaskScreen.route,
        ) {
            TaskScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.AddEditTaskScreen.route +
                    "?taskId={taskId}",
            arguments = listOf(
                navArgument(
                    name = "taskId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "taskColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            AddEditTaskScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.SearchScreen.route
        ) {
            SearchScreen(navController = navController)
        }

        composable(
            route = BottomNavItems.FavoriteScreen.route
        ) {
            FavoriteScreen(
                navController = navController,
            )
        }


    }
}