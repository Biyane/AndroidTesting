package com.example.espresso

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
class EspressoActivity : ComponentActivity(R.layout.activity_espresso) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<TextView>(R.id.text1).setOnClickListener {
            findViewById<TextView>(R.id.hello2).text = "updated"
        }
    }

    fun makeFirstText() {
        findViewById<TextView>(R.id.text1).text = "text1"
    }

    fun makeFirstText2() {
        findViewById<TextView>(R.id.hello2).text = "text2"
    }

    fun makeFirstText3() {
        findViewById<TextView>(R.id.text3).text = "text3"
    }

}