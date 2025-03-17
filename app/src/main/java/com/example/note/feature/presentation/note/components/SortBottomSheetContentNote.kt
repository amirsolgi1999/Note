package com.example.note.feature.presentation.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.R
import com.example.note.feature.presentation.utils.clickableWithoutRipple
import com.example.note.feature.presentation.utils.pulsateClick
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Surface

@Composable
fun SortBottomSheetContentNote(
    ModifiedDescending: () -> Unit,
    ModifiedAscending: () -> Unit,
    SortFavorites: () -> Unit,
    TitleAZ: () -> Unit,
    TitleZA: () -> Unit,
) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Surface)
                    .padding(
                        bottom = 55.dp,
                        top = 25.dp,
                        start = 45.dp,
                        end = 45.dp
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pulsateClick()
                        .clickableWithoutRipple {
                            ModifiedDescending()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_down),
                        tint = OnPrimary,
                        contentDescription = stringResource(R.string.down_icon_for_sorting),
                        modifier = Modifier
                            .size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = stringResource(R.string.modified_descending),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pulsateClick()
                        .clickableWithoutRipple {
                            ModifiedAscending()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_up),
                        tint = OnPrimary,
                        contentDescription = stringResource(R.string.up_icon_for_sorting),
                        modifier = Modifier
                            .size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = stringResource(R.string.modified_ascending),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pulsateClick()
                        .clickableWithoutRipple {
                            SortFavorites()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_heart),
                        tint = OnPrimary,
                        contentDescription = stringResource(R.string.heart_icon_for_sorting),
                        modifier = Modifier
                            .size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = stringResource(R.string.favorites_first),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pulsateClick()
                        .clickableWithoutRipple {
                            TitleAZ()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_az),
                        tint = OnPrimary,
                        contentDescription = stringResource(R.string.a_to_z_icon_for_sorting),
                        modifier = Modifier
                            .size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = stringResource(R.string.title_a_z),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pulsateClick()
                        .clickableWithoutRipple {
                            TitleZA()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_za),
                        tint = OnPrimary,
                        contentDescription = stringResource(R.string.z_to_a_icon_for_sorting),
                        modifier = Modifier
                            .size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = stringResource(R.string.title_z_a),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        color = Color.White
                    )
                }
            }
}