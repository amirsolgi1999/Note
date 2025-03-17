package com.example.note.feature.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.feature.navigation.Screen
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Primary
import com.example.note.ui.theme.Surface

@Composable
fun GoToRecent(
    navController: NavController
){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 10.dp)
            .background(Surface)
    ){
        Text(
            text = "Go TO ",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 15.dp, end = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Primary)
                    .clickable {
                        navController.navigate(Screen.NotesScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(text = "All notes", color = Color.White)
                    Icon(
                        painter = painterResource(id = R.drawable.k),
                        contentDescription = "",
                        tint = OnPrimary,modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 15.dp, end = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Primary)
                    .clickable {
                        navController.navigate(Screen.TaskScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ){
                    Text(text = "All tasks", color = Color.White,)
                    Icon(
                        painter = painterResource(id = R.drawable.k) ,
                        contentDescription = "", tint = OnPrimary,
                        modifier = Modifier.size(20.dp))

                }

            }

        }

    }
}