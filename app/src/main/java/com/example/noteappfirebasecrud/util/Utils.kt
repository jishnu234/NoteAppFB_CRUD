package com.example.noteappfirebasecrud.util

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNoteNavController = compositionLocalOf<NavController>{ error("No NavController found!!") }