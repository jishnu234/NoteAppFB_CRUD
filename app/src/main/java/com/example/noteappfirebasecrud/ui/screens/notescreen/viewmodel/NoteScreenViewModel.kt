package com.example.noteappfirebasecrud.ui.screens.notescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappfirebasecrud.network.NetworkService
import com.example.noteappfirebasecrud.ui.screens.notescreen.model.NoteScreenUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val networkService: NetworkService
): ViewModel() {

    private val _uiStates = MutableStateFlow(NoteScreenUiStates())
    val uiStates: StateFlow<NoteScreenUiStates> = _uiStates

    init {
        getNotes()
    }


    private fun getNotes() = viewModelScope.launch {
        networkService.getNotes()
    }

}