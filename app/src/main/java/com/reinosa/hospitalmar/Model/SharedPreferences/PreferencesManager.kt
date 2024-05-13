package com.reinosa.hospitalmar.Model.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

object UserPreferences {

    private const val PREFERENCE_NAME = "user_preference"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun saveCredentials(context: Context, username: String, password: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    fun getSavedUsername(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_USERNAME, null)
    }

    fun getSavedPassword(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_PASSWORD, null)
    }
    fun clearCredentials(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.remove(KEY_USERNAME)
        editor.remove(KEY_PASSWORD)
        editor.apply()
    }
}
