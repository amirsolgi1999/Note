package com.example.note.feature.navigation.bottom_navigation

import com.example.note.R
import com.example.note.feature.navigation.Screen

sealed class BottomNavItems(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {

    object HomeScreen : BottomNavItems(
        route = Screen.HomeScreen.route,
        title = "Home",
        selectedIcon = R.drawable.home,
        unselectedIcon = R.drawable.home,
    )

    object SearchScreen : BottomNavItems(
        route = Screen.SearchScreen.route,
        title = "Search",
        selectedIcon = R.drawable.search,
        unselectedIcon = R.drawable.search,
    )

    object FavoriteScreen : BottomNavItems(
        route = Screen.FavoriteScreen.route,
        title = "Favorites",
        selectedIcon = R.drawable.love,
        unselectedIcon = R.drawable.love,
    )


}
