package com.example.noteappfirebasecrud.ui.screens.notescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.ui.components.Note
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navigateToNoteEditScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(
            0.dp,
            0.dp,
            0.dp,
            0.dp
        ),
        containerColor = Color(0xFFF1EEDC)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
        ) {

            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                text = "All Notes",
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 32.sp
                ),
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Total 32 Notes",
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Thin,
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Start
            )

            LazyVerticalStaggeredGrid(
                modifier = Modifier.weight(1f),
                columns = StaggeredGridCells.Adaptive(142.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalItemSpacing = 16.dp,
                contentPadding = PaddingValues(
                    top = 32.dp,
                    bottom = paddingValues.calculateBottomPadding() + 32.dp
                )
            ) {
                items(25) {

                    Note(
                        titleText = "Your Title here",
                        noteText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna fkafjafja fh lasjflfsa lfajskjflsda lfsdajlkasfjd ",
                        date = "24 Jan",
                        onClick = navigateToNoteEditScreen
                    )
                }
            }
        }

    }
}