package com.example.noteappfirebasecrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.noteappfirebasecrud.navigation.NoteAppFirebaseCrudNavigation
import com.example.noteappfirebasecrud.storage.Preference
import com.example.noteappfirebasecrud.ui.theme.NoteAppFirebaseCRUDTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            ),
        )
        super.onCreate(savedInstanceState)

        setContent {
            NoteAppFirebaseCRUDTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                /* CompositionLocalProvider(LocalNoteNavController provides navController) { */
                NoteAppFirebaseCrudNavigation(
                    navController = navController, preference = preference
                )
//                }

            }
        }
    }
}