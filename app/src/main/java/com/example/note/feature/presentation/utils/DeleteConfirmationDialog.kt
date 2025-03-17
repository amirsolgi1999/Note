package com.example.note.feature.presentation.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.note.R
import com.example.note.ui.theme.OnPrimary
import com.example.note.ui.theme.Surface

@Composable
fun DeleteConfirmationDialog(
    onDeleteClick: () -> Unit,
    onDeleteCancel: () -> Unit,
    titleString: String,
    textString: String,
) {

    AlertDialog(
        containerColor = Surface,
        onDismissRequest = {
        },
        title = {
            Text(titleString,
                color = Color.White)
        },
        text = {
            Text(textString,
                color = Color.White)
        },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(
                    text = stringResource(R.string.no),
                    color = OnPrimary
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteClick) {
                Text(
                    text = stringResource(R.string.yes),
                    color = OnPrimary

                )
            }
        },
    )
}