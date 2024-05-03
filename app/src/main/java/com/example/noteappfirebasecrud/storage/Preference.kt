package com.example.noteappfirebasecrud.storage

interface Preference {
    fun updateLaunchStatus(isFirstLaunch: Boolean)
    fun isFirstLaunch(): Boolean
    companion object {
        const val KEY_FIRST_LAUNCH = "note_first_launch"
    }
}