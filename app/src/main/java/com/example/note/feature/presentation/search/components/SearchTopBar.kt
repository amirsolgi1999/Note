package com.example.note.feature.presentation.search.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.R
import com.example.note.feature.presentation.search.SearchEvent
import com.example.note.feature.presentation.search.SearchViewModel
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Primary
import com.example.note.ui.theme.Surface


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Surface)
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = state.searchQuery,
                onValueChange = { newQuery ->
                    viewModel.onEvent(SearchEvent.Search(newQuery))
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Primary,
                    focusedIndicatorColor = Primary,
                    unfocusedIndicatorColor = Primary,
                    disabledIndicatorColor = Primary
                ),
                trailingIcon = {
                    if (state.searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(SearchEvent.ClearSearch)
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cross_circle),
                                contentDescription = stringResource(id = R.string.clear_icon),
                                tint = OnPrimary,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.search),
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = OnPrimary,
                        modifier = Modifier
                            .size(15.dp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                textStyle = TextStyle(color = Color.White),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Primary),
            )
        }

}