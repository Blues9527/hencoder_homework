package com.blues.animator

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blues.common.extension.dp
import com.blues.homework.R
import kotlinx.android.synthetic.main.activity_scale.*

class ScaleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)

        val animator = ObjectAnimator.ofFloat(view, "radius", 150.dp)
        animator.startDelay = 1000
        animator.start()
    }
}