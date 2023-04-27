package com.mycontacts

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    //TODO What is this??
    companion object {
        private lateinit var app: App
        val instance: App get() = app
    }



}