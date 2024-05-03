package com.example.noteappfirebasecrud.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteappfirebasecrud.storage.Preference
import com.example.noteappfirebasecrud.ui.screens.landing.NoteLandingScreen
import com.example.noteappfirebasecrud.ui.screens.note_edit_screen.NoteEditScreen
import com.example.noteappfirebasecrud.ui.screens.notescreen.NoteScreen
import com.example.noteappfirebasecrud.ui.screens.notescreen.viewmodel.NoteScreenViewModel

@Composable
fun NoteAppFirebaseCrudNavigation(
    preference: Preference,
    navController: NavHostController
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
                navController.navigate(Screens.NoteScreen.route)
            }
        }

        composable(
            route = Screens.NoteScreen.route
        ) {
            NoteScreen(
                viewModel = hiltViewModel<NoteScreenViewModel>()
            ) { navController.navigate(Screens.NoteEditScreen.route) }
        }

        composable(
            route = Screens.NoteEditScreen.route
        ) {
            NoteEditScreen(
                viewModel = hiltViewModel(),
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}