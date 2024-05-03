package com.example.noteappfirebasecrud.model.snackbar

import androidx.compose.material3.SnackbarData
import kotlinx.coroutines.CoroutineScope

interface NoteSnackBarService {

    fun showSnackBar(scope: CoroutineScope, snackBarData: SnackbarData): Unit

    fun hideSnackBar(): Unit
}