package com.example.noteappfirebasecrud.model

import java.time.LocalDate

data class Notes(
    val title: String,
    val notes: String,
    val date: LocalDate,
)
