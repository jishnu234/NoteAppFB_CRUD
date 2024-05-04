package com.example.noteappfirebasecrud.ui.screens.editnote.viewmodel

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappfirebasecrud.model.Notes
import com.example.noteappfirebasecrud.ui.screens.editnote.model.NoteEditScreenUiStates
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


private const val TAG = "NoteEditScreenViewModel"

@HiltViewModel
class NoteEditScreenViewModel @Inject constructor(
    private val reference: CollectionReference
) : ViewModel() {

    private val _uiStates = MutableStateFlow(NoteEditScreenUiStates())
    val uiStates: StateFlow<NoteEditScreenUiStates> = _uiStates

    val snackBarHostState = SnackbarHostState()


    fun saveToOrUpdateDB(notes: Notes, onSuccess: () -> Unit) = viewModelScope.launch {

        _uiStates.value = _uiStates.value.copy(isLoading = true)

        val currentDate = try {
            LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: Exception) {
            null
        }

        val value = mapOf(
            "title" to notes.title,
            "note" to notes.note,
            "date" to (notes.date ?: currentDate)
        )

        if (_uiStates.value.note == null)
            reference.add(value)
                .addOnSuccessListener {
                    _uiStates.value = _uiStates.value.copy(
                        isLoading = false
                    )
                    CoroutineScope(Dispatchers.Default).launch {
                        snackBarHostState
                            .showSnackbar(
                                message = "Note saved to DB",
                                withDismissAction = false,
                                duration = SnackbarDuration.Short
                            )
                    }
                    onSuccess()
                }
                .addOnFailureListener {
                    _uiStates.value = _uiStates.value.copy(
                        isLoading = false
                    )
                    CoroutineScope(Dispatchers.Default).launch {
                        snackBarHostState
                            .showSnackbar(
                                message = it.message.orEmpty(),
                                withDismissAction = false,
                                duration = SnackbarDuration.Short
                            )
                    }
                    Log.d(TAG, "saveToDB: Failed adding data")
                }
        else {

            reference.document(uiStates.value.note?.documentId.orEmpty())
                .update(value)
                .addOnSuccessListener {
                    _uiStates.value = _uiStates.value.copy(
                        isLoading = false
                    )
                    CoroutineScope(Dispatchers.Default).launch {
                        snackBarHostState
                            .showSnackbar(
                                message = "Note updated",
                                withDismissAction = false,
                                duration = SnackbarDuration.Short
                            )
                    }
                    onSuccess()
                }
                .addOnFailureListener {
                    _uiStates.value = _uiStates.value.copy(
                        isLoading = false
                    )
                    CoroutineScope(Dispatchers.Default).launch {
                        snackBarHostState
                            .showSnackbar(
                                message = "Failed ${it.message.orEmpty()}",
                                withDismissAction = false,
                                duration = SnackbarDuration.Short
                            )
                    }
                }
        }
    }

    fun setArgs(args: String) {
        try {
            val note = Json.decodeFromString<Notes>(args)
            Log.d(TAG, "setArgs: decoded Data : $note")
            _uiStates.value = _uiStates.value.copy(
                note = note,
                noteText = note.note,
                titleText = note.title
            )
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Default).launch {
                snackBarHostState
                    .showSnackbar(
                        message = e.message.orEmpty(),
                        withDismissAction = false,
                        duration = SnackbarDuration.Short
                    )
            }
        }
    }

    fun updateNoteText(text: String) {
        _uiStates.value = _uiStates.value.copy(
            noteText = text
        )
    }

    fun updateTitleText(text: String) {
        _uiStates.value = _uiStates.value.copy(
            titleText = text
        )
    }

    fun checkNotes(notes: Notes, onSuccess: () -> Unit) = viewModelScope.launch {

        if (
            (notes.note.isEmpty() || notes.note.equals("Enter your notes..", false)) &&
            (notes.title.isEmpty() || notes.title.equals("Enter your Title..", false))
        ) {
            snackBarHostState.showSnackbar(
                "Don't fool me. Where is the notes..",
                duration = SnackbarDuration.Short
            )
        } else {
            if (notes.note.isEmpty() || notes.note.equals("Enter your notes..", false)) {
                snackBarHostState.showSnackbar(
                    "Hey, dude note is missing",
                    duration = SnackbarDuration.Short
                )
            } else if (notes.title.isEmpty() || notes.title.equals("Enter your Title..", false)) {
                snackBarHostState.showSnackbar(
                    "Hey, dude title is missing",
                    duration = SnackbarDuration.Short
                )
            } else {
                if (
                    notes.note == uiStates.value.note?.note &&
                    notes.title == uiStates.value.note?.title
                ) {
                    snackBarHostState.showSnackbar(
                        "Heehee.. No change in data",
                        duration = SnackbarDuration.Short
                    )
                } else {
                    saveToOrUpdateDB(notes, onSuccess)
                }
            }
        }


    }

}