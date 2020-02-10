package com.codertainment.materialintro.prefs

import android.content.Context

class PreferencesManager(context: Context) {
  private val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

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

  companion object {
    private const val PREFERENCES_NAME = "material_intro_preferences"
  }
}