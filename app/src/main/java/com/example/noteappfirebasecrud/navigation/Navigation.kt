package com.example.noteappfirebasecrud.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.noteappfirebasecrud.storage.Preference
import com.example.noteappfirebasecrud.ui.screens.editnote.NoteEditScreen
import com.example.noteappfirebasecrud.ui.screens.landing.NoteLandingScreen
import com.example.noteappfirebasecrud.ui.screens.notescreen.NoteScreen

@Composable
fun NoteAppFirebaseCrudNavigation(
    preference: Preference,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = if (preference.isFirstLaunch()) Screens.NoteLandingScreen.route else Screens.NoteScreen.route
    ) {

        composable(
            route = Screens.NoteLandingScreen.route
        ) {
            NoteLandingScreen {
                preference.updateLaunchStatus(false)
                if (it.lifecycle.currentState == Lifecycle.State.RESUMED)
                    navController.navigate(Screens.NoteScreen.route)
            }
        }

        composable(
            route = Screens.NoteScreen.route
        ) { backStackEntry ->
            NoteScreen(
                viewModel = hiltViewModel()
            ) {
                if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED)
                    navController.navigate(Screens.NoteEditScreen.generateNoteEditScreeRoute(it))
            }
        }

        composable(
            route = Screens.NoteEditScreen.route,
            arguments = listOf(
                navArgument(
                    name = Screens.KEY_NOTE_EDIT_SCREEN,
                    builder = {
                        type = NavType.StringType
                        nullable = true
                    }
                )
            )
        ) {
            NoteEditScreen(
                args = it.arguments?.getString(Screens.KEY_NOTE_EDIT_SCREEN),
                viewModel = hiltViewModel(),
                navigateBack = {
                    if (it.lifecycle.currentState == Lifecycle.State.RESUMED)
                        navController.popBackStack()
                }
            )
        }
    }

}