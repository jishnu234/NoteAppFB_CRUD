package com.example.noteappfirebasecrud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteappfirebasecrud.ui.screens.landing.NoteLandingScreen
import com.example.noteappfirebasecrud.ui.screens.note_edit_screen.NoteEditScreen
import com.example.noteappfirebasecrud.ui.screens.notescreen.NoteScreen

@Composable
fun NoteAppFirebaseCrudNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.NoteLandingScreen.route
    ) {

        composable(
            route = Screens.NoteLandingScreen.route
        ) {
            NoteLandingScreen { navController.navigate(Screens.NoteScreen.route) }
        }

        composable(
            route = Screens.NoteScreen.route
        ) {
            NoteScreen { navController.navigate(Screens.NoteEditScreen.route) }
        }

        composable(
            route = Screens.NoteEditScreen.route
        ) {
            NoteEditScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}