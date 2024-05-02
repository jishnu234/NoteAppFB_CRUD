package com.example.noteappfirebasecrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.noteappfirebasecrud.navigation.NoteAppFirebaseCrudNavigation
import com.example.noteappfirebasecrud.ui.theme.NoteAppFirebaseCRUDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppFirebaseCRUDTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

//                CompositionLocalProvider(LocalNoteNavController provides navController) {
                NoteAppFirebaseCrudNavigation(navController)
//                }

            }
        }
    }
}