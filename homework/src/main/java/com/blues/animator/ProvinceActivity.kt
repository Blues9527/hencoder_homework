package com.blues.animator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blues.homework.R
import kotlinx.android.synthetic.main.activity_province.*

class ProvinceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province)


        val animator =
            ObjectAnimator.ofObject(
                province,
                "province",
                ProvinceView.ProvinceEvaluator(),
                "澳门特别行政区"
            )

        animator.startDelay = 1000
        animator.duration = 8000
        animator.start()
    }

}