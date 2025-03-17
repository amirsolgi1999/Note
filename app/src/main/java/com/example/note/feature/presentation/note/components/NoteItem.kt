package com.example.note.feature.presentation.note.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.R
import com.example.note.feature.domain.model.Note
import com.example.note.feature.domain.util.convertLongToTime
import com.example.note.feature.presentation.note.NoteEvent
import com.example.note.feature.presentation.note.NoteViewModel
import com.example.note.feature.presentation.utils.DeleteConfirmationDialog
import com.example.note.feature.presentation.utils.pulsateClick2
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Primary

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
) {

    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current


    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            Primary
        ),
        modifier = modifier
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp)
        ) {

            Text(
                text = note.title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(15.dp))



            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = note.description,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.White,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconToggleButton(
                    checked = note.isFavorite,
                    onCheckedChange = { isChecked ->
                        viewModel.onEvent(NoteEvent.OnFavoriteChange(note, isChecked))
                    },
                    modifier = Modifier.pulsateClick2()
                ) {
                    Icon(
                        imageVector = if (note.isFavorite) {
                            Icons.Filled.Favorite

                        } else {
                            Icons.Filled.FavoriteBorder
                        },
                        tint = if (note.isFavorite) {
                            OnPrimary
                        } else {
                            Color.White
                        },
                        contentDescription = stringResource(id = R.string.favorite_icon),
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(
                    onClick = {
                         val sharingText = """
                            Note: ${note.title}
                            Description: ${note.description}
                            """.trimIndent()
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, sharingText)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)

                        context.startActivity(shareIntent)
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.share),
                        contentDescription = stringResource(id = R.string.share_note),
                        tint = Color.White,
                        modifier = Modifier.size(23.dp)
                    )
                }

                IconButton(
                    onClick = {
                        deleteConfirmationRequired = true
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.bin),
                        contentDescription = stringResource(id = R.string.delete_note),
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteClick = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {
                            deleteConfirmationRequired = false
                        },
                        titleString = stringResource(id = R.string.delete_note_dialog),
                        textString = stringResource(id = R.string.are_you_sure_to_delete_your_note)
                    )
                }
            }
           
        }
    }
}