package com.example.noteappfirebasecrud.storage

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceImpl @Inject constructor(
    private val preference: SharedPreferences
): Preference {
    override fun updateLaunchStatus(isFirstLaunch: Boolean) {
        preference
            .edit()
            .putBoolean(Preference.KEY_FIRST_LAUNCH, isFirstLaunch)
            .apply()
    }

    override fun isFirstLaunch(): Boolean =
        preference.getBoolean(Preference.KEY_FIRST_LAUNCH, true)


}