package com.example.note.feature.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun CategoriesPart(
    modifier: Modifier = Modifier,
    navController: NavController
){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, end = 10.dp, start = 15.dp, bottom = 10.dp)
            .background(Surface),
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.category),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(15.dp))

        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Box (
                modifier = Modifier
                    .size(width = 176.dp, height = 136.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate(Screen.NotesScreen.route)
                    }
                    .background(Primary)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.note_category),
                        modifier = Modifier.size(60.dp),
                        tint = OnPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.notes),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = OnPrimary
                    )

                }

            }
            
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(width = 176.dp, height = 136.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate(Screen.TaskScreen.route)
                    }
                    .background(Primary)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.category_task),
                        contentDescription = stringResource(R.string.task_category),
                        modifier = Modifier.size(60.dp),
                        tint = OnPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.tasks),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = OnPrimary
                    )
                }
            }
        }
    }
}