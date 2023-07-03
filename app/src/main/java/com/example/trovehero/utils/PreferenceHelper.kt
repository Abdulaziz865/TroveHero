package com.example.trovehero.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var preferences: SharedPreferences

    fun units(context: Context) {
        preferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var isStartApp: Boolean
        get() = preferences.getBoolean("start", false)
        set(value) = preferences.edit().putBoolean("start", value).apply()
}