package com.example.note.feature.presentation.favorite.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.R
import com.example.note.ui.theme.Surface

@Preview
@Composable
fun FavoriteTopBar(){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, start = 20.dp, top = 15.dp, end = 20.dp)
            .background(Surface),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = stringResource(id = R.string.favorites),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )

    }
}