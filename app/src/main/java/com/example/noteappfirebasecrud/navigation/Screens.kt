package com.example.noteappfirebasecrud.navigation

import com.example.noteappfirebasecrud.model.Notes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class Screens(val route: String) {
    object NoteLandingScreen: Screens(route = "landing_screen")
    object NoteScreen: Screens(route = "note_screen")
    object NoteEditScreen: Screens(route = "note_edit_screen/{${KEY_NOTE_EDIT_SCREEN}}"){
        fun generateNoteEditScreeRoute(note: Notes?): String {
            return try {
                if (note != null) {
                    "note_edit_screen/${Json.encodeToString(note)}"
                } else "note_edit_screen/${null}"

            } catch (_: Exception) {
                "note_edit_screen/${null}"
            }
        }
    }

    companion object{
        const val KEY_NOTE_EDIT_SCREEN = "key_note_edit_screen"
    }
}