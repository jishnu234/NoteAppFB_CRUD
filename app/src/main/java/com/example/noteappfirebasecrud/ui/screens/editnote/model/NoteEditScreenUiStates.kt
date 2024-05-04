package com.example.noteappfirebasecrud.ui.screens.editnote.model

import com.example.noteappfirebasecrud.model.Notes

data class NoteEditScreenUiStates(
    val isLoading: Boolean = false,
    val note: Notes? = null,

    val noteText: String? = null,
    val titleText: String? = null,

)
