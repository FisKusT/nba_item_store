package com.fiskus.nbaitemsstore

import android.app.Application
import timber.log.Timber

class StoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}