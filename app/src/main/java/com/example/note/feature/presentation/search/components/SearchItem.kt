package com.example.note.feature.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.note.feature.domain.model.Note
import com.example.note.ui.theme.OnPrimary

@Composable
fun SearchItem(
    note: Note,
    modifier: Modifier = Modifier
){

    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Column (
            modifier= Modifier
                .width(7.dp)
                .height(21.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(OnPrimary)
        ){
            Text(text = "")

        }
        Spacer(modifier = Modifier.width(10.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Light,
                color = Color.White.copy(0.8f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }
    }
}