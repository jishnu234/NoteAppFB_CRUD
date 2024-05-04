package com.example.noteappfirebasecrud.ui.screens.notescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.model.Notes
import com.example.noteappfirebasecrud.ui.components.Note
import com.example.noteappfirebasecrud.ui.components.NoteErrorScreen
import com.example.noteappfirebasecrud.ui.components.NoteErrorScreenType
import com.example.noteappfirebasecrud.ui.screens.notescreen.viewmodel.NoteScreenViewModel
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NoteScreenViewModel,
    navigateToNoteEditScreen: (Notes?) -> Unit
) {

    val uiState = viewModel.uiStates.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getNotes()
    })


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier,
                hostState = viewModel.snackBarHostState,
                snackbar = { snackBarData ->
                    Snackbar(
                        modifier = Modifier.navigationBarsPadding(),
                        snackbarData = snackBarData,
                        containerColor = Color(0xFFB3C8CF),
                        contentColor = Color.Black
                    )
                }
            )
        },
        floatingActionButton = {
            if (viewModel.noteCollection.isNotEmpty())
                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(32.dp),
                    onClick = { navigateToNoteEditScreen(null) },
                    shape = CircleShape,
                    containerColor = Color.Black
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
        },
        floatingActionButtonPosition = FabPosition.End,
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
                text =
                    if (viewModel.noteCollection.size > 0) "Total ${viewModel.noteCollection.size} Notes" else "Looks like no notes added yet...",
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

            if (uiState.value.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center),
                        color = Color(0xFFB3C8CF),
                        strokeWidth = 5.dp
                    )
                }
            } else
                when (val type = uiState.value.errorType) {
                    is NoteErrorScreenType.Empty -> {
                        NoteErrorScreen(
                            type = type,
                            modifier = Modifier.weight(1f)
                        ) {
                            navigateToNoteEditScreen(null)
                        }
                    }

                    is NoteErrorScreenType.GenericError -> {
                        NoteErrorScreen(
                            type = type,
                            modifier = Modifier.weight(1f)
                        ) {
                            viewModel.getNotes()
                        }
                    }

                    null -> {
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
                            items(viewModel.noteCollection) {
                                Note(
                                    titleText = it.title,
                                    noteText = it.note,
                                    date = it.date.orEmpty(),
                                    onClick = { navigateToNoteEditScreen(it) },
                                    deleteNote = {
                                        if (it.documentId.isNullOrEmpty())
                                            coroutineScope.launch {
                                                viewModel
                                                    .snackBarHostState
                                                    .showSnackbar(
                                                        "Sorry, you can't delete this note. 🥹"
                                                    )
                                            }
                                        else
                                            viewModel.deleteNote(it.documentId)
                                    }
                                )
                            }
                        }
                    }
                }
        }

    }
}