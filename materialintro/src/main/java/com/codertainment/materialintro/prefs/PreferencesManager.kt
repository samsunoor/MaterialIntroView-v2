package com.codertainment.materialintro.prefs

import android.content.SharedPreferences

internal object PreferencesManager {

    private lateinit var sharedPreferences: SharedPreferences

    val isInitialized = ::sharedPreferences.isInitialized

    fun init(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    fun isDisplayed(id: String?): Boolean {
        return sharedPreferences.getBoolean(id, false)
    }

    fun setDisplayed(id: String?) {
        sharedPreferences.edit().putBoolean(id, true).apply()
    }

    fun reset(id: String?) {
        sharedPreferences.edit().putBoolean(id, false).apply()
    }

    fun resetAll() {
        sharedPreferences.edit().clear().apply()
    }

    const val PREFERENCES_NAME = "material_intro_preferences"
}