package com.peter.creditfins.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.creditfins.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}