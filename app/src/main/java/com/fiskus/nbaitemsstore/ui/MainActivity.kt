package com.fiskus.nbaitemsstore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fiskus.nbaitemsstore.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate")
    }
}