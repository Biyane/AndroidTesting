package com.example.unit.robolectirc

import android.app.Activity
import android.content.Intent

class RobolectricActivity : Activity() {


    fun nextActivity() {
        val intent = Intent(this, RobolectricActivity2::class.java)
        startActivity(intent)
    }
}