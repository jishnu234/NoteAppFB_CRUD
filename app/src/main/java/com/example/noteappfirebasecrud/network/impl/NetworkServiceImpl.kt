package com.example.noteappfirebasecrud.network.impl

import android.util.Log
import com.example.noteappfirebasecrud.model.Notes
import com.example.noteappfirebasecrud.network.NetworkService
import java.time.LocalDate

class NetworkServiceImpl: NetworkService {
    override suspend fun getNotes(): Notes {
        return Notes("dlksajlf", "nfaksldf", LocalDate.now())
    }

    override suspend fun submitNotes(notes: Notes) {
        Log.d("TAG", "submitNotes: $notes")
    }
}