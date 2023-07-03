package com.example.trovehero

import android.app.Application
import com.example.trovehero.utils.PreferenceHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.units(this)
    }
}