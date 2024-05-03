package com.example.noteappfirebasecrud.ui.screens.note_edit_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappfirebasecrud.model.Notes
import com.example.noteappfirebasecrud.network.NetworkService
import com.example.noteappfirebasecrud.ui.screens.note_edit_screen.model.NoteEditScreenUiStates
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "NoteEditScreenViewModel"
@HiltViewModel
class NoteEditScreenViewModel @Inject constructor(
    private val service: NetworkService,
): ViewModel() {

    val firebase = Firebase.firestore

    private val _uiStates = MutableStateFlow(NoteEditScreenUiStates())
    val uiStates: StateFlow<NoteEditScreenUiStates> = _uiStates

    fun saveNote(note: Notes) = viewModelScope.launch {
        Log.d(TAG, "saveNote: entered")

        val city = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
        )

        firebase.collection("cities").document("LA")
            .set(city)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
}