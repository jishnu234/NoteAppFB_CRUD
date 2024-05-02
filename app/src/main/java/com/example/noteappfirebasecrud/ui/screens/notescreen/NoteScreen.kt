package com.example.noteappfirebasecrud.ui.screens.notescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NoteScreen(
    navigateToNoteEditScreen:() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navigateToNoteEditScreen()
            },
            text = "NoteScreen",
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}