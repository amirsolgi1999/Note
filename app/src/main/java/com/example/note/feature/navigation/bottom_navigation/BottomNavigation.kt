package com.example.note.feature.navigation.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.note.R
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Primary
import com.example.note.ui.theme.Surface


@Composable
fun BottomBar(
    navController: NavController,
) {

    val screens = listOf(
        BottomNavItems.HomeScreen,
        BottomNavItems.SearchScreen,
        BottomNavItems.FavoriteScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    Column (
        modifier = Modifier
            .background(Surface)
    ){
        if (bottomBarDestination) {
            BottomNavigation(
                backgroundColor = Primary,
                modifier = Modifier
                    .height(60.dp),
                elevation = 50.dp
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItems,
    currentDestination: NavDestination?,
    navController: NavController,
) {

    BottomNavigationItem(
        icon = {
            val iconPainter =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    painterResource(id = screen.selectedIcon)
                } else {
                    painterResource(id = screen.unselectedIcon)
                }
            Icon(
                painter = iconPainter,
                contentDescription = stringResource(R.string.bottom_icon),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(20.dp),
                tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    OnPrimary
                } else {
                    Color.White
                }
            )
        },
        label = {
            Text(
                text = screen.title,
                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    OnPrimary
                } else {
                    Color.White
                },
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        },
        alwaysShowLabel = true,
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = 0.5f),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}