package com.example.noteappfirebasecrud.ui.screens.notescreen.viewmodel

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappfirebasecrud.model.Notes
import com.example.noteappfirebasecrud.ui.components.NoteErrorScreenType
import com.example.noteappfirebasecrud.ui.screens.notescreen.model.NoteScreenUiStates
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NoteScreenViewModel"
@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val ref: CollectionReference
): ViewModel() {

    private val _uiStates = MutableStateFlow(NoteScreenUiStates())
    val uiStates: StateFlow<NoteScreenUiStates> = _uiStates
    
    val noteCollection = mutableStateListOf<Notes>()

    val snackBarHostState = SnackbarHostState()


    fun getNotes() = viewModelScope.launch {

        _uiStates.value = _uiStates.value.copy(isLoading = true)

        ref.get().addOnSuccessListener { result ->
            Log.d(TAG, "getNotes: result - $result")
                noteCollection.clear()
                if (result.documents.isEmpty()) {
                    Log.d(TAG, "getNotes: result is empty")
                    _uiStates.value = _uiStates.value.copy(
                        errorType = NoteErrorScreenType.Empty(),
                        isLoading = false
                    )
                }
                else {
                    Log.d(TAG, "getNotes: result : ${result.documents.map { it }}")
                    _uiStates.value = _uiStates.value.copy(
                        errorType = null,
                        isLoading = false,
                        notesCount = result.documents.count()
                    )

                    for (document in result) {
                        Log.d(TAG, "getNotes: documentId - ${document.id}")
                        try {
                            noteCollection.add(
                                Notes(
                                    title = document.data.getValue("title").toString(),
                                    date = document.data.getValue("date").toString(),
                                    note = document.data.getValue("note").toString(),
                                    documentId = document.id
                                )
                            ) 
                        } catch (e: Exception) {
                            Log.d(TAG, "getNotes: ${e.message}")
                        }
                        
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "getNotes: error Occured ${it.message}")
                _uiStates.value = _uiStates.value.copy(
                    errorType = NoteErrorScreenType.GenericError(),
                    isLoading = false
                )
            }

    }

    fun deleteNote(documentId: String) = viewModelScope.launch {
        _uiStates.value = _uiStates.value.copy(
            isLoading = true
        )

        ref.document(documentId)
            .delete()
            .addOnSuccessListener {
                getNotes()
            }
            .addOnFailureListener {
                CoroutineScope(Dispatchers.Default).launch {
                    snackBarHostState.showSnackbar(
                        "Error deleting note"
                    )
                }
                _uiStates.value = _uiStates.value.copy(
                    isLoading = false
                )
            }

    }

}