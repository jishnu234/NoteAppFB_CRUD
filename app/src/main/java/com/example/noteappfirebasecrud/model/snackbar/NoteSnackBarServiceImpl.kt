package com.example.noteappfirebasecrud.model.snackbar

import androidx.compose.material3.SnackbarData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteSnackBarServiceImpl: NoteSnackBarService {

    private val _snackBar = MutableStateFlow<SnackbarData?>(null)
    val snackBar: StateFlow<SnackbarData?> = _snackBar


    private var job: Job? = null
    override fun showSnackBar(scope: CoroutineScope, snackBarData: SnackbarData) {
        job?.cancel()
        job = scope.launch {
            _snackBar.update { snackBarData }
            delay(1000L)
            _snackBar.update { null }
        }
    }

    override fun hideSnackBar() {
        _snackBar.update { null }
    }
}