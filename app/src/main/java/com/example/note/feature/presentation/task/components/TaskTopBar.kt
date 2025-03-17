package com.example.note.feature.presentation.task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.navigation.Screen
import com.example.note.feature.presentation.utils.clickableWithoutRipple
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Surface


@Composable
fun TaskTopBar(
    navController: NavController,
) {

    TopAppBar(
        backgroundColor = Surface,
        elevation = 0.dp,
        modifier = Modifier.height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp, start = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

             Icon(
                    painter = painterResource(id = R.drawable.angle_left),
                    tint = OnPrimary,
                    contentDescription = stringResource(id = R.string.back_arrow),
                    modifier = Modifier
                        .size(20.dp)
                        .clickableWithoutRipple {
                            navController.navigateUp()
                        }
                )

             Icon(
                    painter = painterResource(id = R.drawable.add),
                    tint = OnPrimary,
                    contentDescription = stringResource(id = R.string.add_icon),
                    modifier = Modifier
                        .size(20.dp)
                        .clickableWithoutRipple {
                            navController.navigate(Screen.AddEditTaskScreen.route)
                        }
                )


        }
    }
}