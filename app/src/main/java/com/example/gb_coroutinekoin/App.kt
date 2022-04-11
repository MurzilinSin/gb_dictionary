package com.example.gb_coroutinekoin

import android.app.Application
import com.example.gb_coroutinekoin.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}