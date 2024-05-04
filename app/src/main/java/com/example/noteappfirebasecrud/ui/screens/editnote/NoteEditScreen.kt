package com.example.noteappfirebasecrud.ui.screens.editnote

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.model.Notes
import com.example.noteappfirebasecrud.ui.components.NotesTopBar
import com.example.noteappfirebasecrud.ui.screens.editnote.viewmodel.NoteEditScreenViewModel
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily
import kotlinx.coroutines.launch

private const val TAG = "NoteEditScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    args: String?,
    viewModel: NoteEditScreenViewModel,
    navigateBack: () -> Unit
) {
    val uiStates = viewModel.uiStates.collectAsState().value
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()


    LaunchedEffect(key1 = Unit, block = {
        Log.d(TAG, "NoteEditScreen: incoming args: ${args.isNullOrEmpty()}")
        if (!args.isNullOrEmpty()) {
            Log.d(TAG, "NoteEditScreen: args ---  $args")
            viewModel.setArgs(args)
        } else {
            Log.d(TAG, "NoteEditScreen: args --- args empty")
        }
    })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(
            0.dp,
            0.dp,
            0.dp,
            0.dp
        ),
        containerColor = Color(0xFFF1EEDC),
        topBar = {
            TopAppBar(
                title = {
                    NotesTopBar(
                        value = uiStates.titleText.orEmpty(),
                        onValueChange = viewModel::updateTitleText,
                        onBackPressed = navigateBack
                    )
                },
                colors = TopAppBarDefaults
                    .largeTopAppBarColors(
                        containerColor = Color(0xFFF1EEDC)
                    )
            )
        },
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding(),
                hostState = viewModel.snackBarHostState,
                snackbar = { snackBarData ->
                    Snackbar(
                        snackbarData = snackBarData,
                        containerColor = Color(0xFFB3C8CF),
                        contentColor = Color.Black
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .imePadding()
                    .padding(paddingValues)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null
                    ) { focusRequester.requestFocus() }
                    .padding(24.dp)
                    .verticalScroll(scrollState),
            ) {
                BasicTextField(
                    modifier = Modifier
                        .imePadding()
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (it.isFocused)
                                viewModel.updateNoteText(
                                    if (uiStates.noteText.equals(
                                            "Enter your notes..",
                                            ignoreCase = true
                                        )
                                    )
                                        "" else uiStates.noteText.orEmpty()
                                )
                            else
                                if (uiStates.noteText.isNullOrEmpty())
                                    viewModel.updateNoteText("Enter your notes..")

                        },
                    value = uiStates.noteText.orEmpty(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                        viewModel.updateNoteText(it)
                    },
                    readOnly = false,
                    textStyle = TextStyle(
                        fontFamily = noteAppFirebaseCrudFamily,
                        textAlign = TextAlign.Start,
                        color = if (uiStates.noteText.equals("Enter your notes..", false))
                            Color.Black.copy(0.5f)
                        else
                            Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                    )
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp, start = 48.dp, end = 48.dp)
                    .height(48.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Color.Black
                    ),
                onClick = {
                    viewModel.checkNotes(
                        Notes(
                            note = uiStates.noteText.orEmpty(),
                            title = uiStates.titleText.orEmpty()
                        ),
                        onSuccess = {
                            navigateBack()
                        }
                    )
                }
            ) {
                Text(
                    text = if (uiStates.note == null) "Save" else "Update",
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


            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)
            )
        }

    }
}