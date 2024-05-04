package com.example.noteappfirebasecrud.ui.screens.notescreen.model

import com.example.noteappfirebasecrud.ui.components.NoteErrorScreenType

data class NoteScreenUiStates(
    val isLoading: Boolean = false,
    val notesCount: Int? = null,
    val errorType: NoteErrorScreenType? = null
)
