package com.example.noteappfirebasecrud.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.R
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily

sealed class NoteErrorScreenType {
    data class GenericError(val message: String = "Oops.. Something went wrong...") :
        NoteErrorScreenType()

    data class Empty(val message: String = "Oops.. Nothing found..") : NoteErrorScreenType()
}


@Composable
fun NoteErrorScreen(
    modifier: Modifier = Modifier,
    type: NoteErrorScreenType = NoteErrorScreenType.GenericError(),
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = when (type) {
                    is NoteErrorScreenType.GenericError -> R.drawable.crying_girl
                    is NoteErrorScreenType.Empty -> R.drawable.sad_girl
                }
            ),
            contentDescription = "error_logo",
            modifier = Modifier.size(230.dp, 309.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = when (type) {
                is NoteErrorScreenType.Empty -> type.message
                is NoteErrorScreenType.GenericError -> type.message
            },
            color = Color.Red,
            style = TextStyle(
                fontFamily = noteAppFirebaseCrudFamily,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp, start = 48.dp, end = 48.dp)
                .height(48.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = Color.Black
                ),
            onClick = onClick
        ) {
            Text(
                text = when (type) {
                    is NoteErrorScreenType.Empty -> "Add Notes"
                    is NoteErrorScreenType.GenericError -> "Retry"
                },
                color = Color.White,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Start
            )
        }
    }
}