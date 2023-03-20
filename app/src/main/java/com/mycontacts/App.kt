package com.mycontacts

import android.app.Application
import android.content.ContentResolver

class App : Application() {

//    var contentResolver: ContentResolver = getContentResolver()
//     lateinit var instance: App

    override fun onCreate() {
        super.onCreate()
//        instance = this
    }

//    fun getInstance(): App = instance

    fun getResolver():ContentResolver = contentResolver



}