package com.example.noteappfirebasecrud.network

import com.example.noteappfirebasecrud.model.Notes

interface NetworkService {

    suspend fun getNotes(): Notes

    suspend fun submitNotes(note: Notes)
}