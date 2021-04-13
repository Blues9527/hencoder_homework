package com.blues.multitouchview

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blues.homework.R

class MultiTouchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multitouchview)
    }


    fun toMultiTouch1(view: View) {
        startActivity(Intent(this, MultiTouchActivity1::class.java))
    }

    fun toMultiTouch2(view: View) {
        startActivity(Intent(this, MultiTouchActivity2::class.java))
    }

    fun toMultiTouch3(view: View) {
        startActivity(Intent(this, MultiTouchActivity3::class.java))
    }
}