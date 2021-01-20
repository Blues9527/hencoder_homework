package com.blues.animator

import android.animation.ObjectAnimator
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blues.common.extension.dp
import com.blues.homework.R
import kotlinx.android.synthetic.main.activity_point.*

class PointFActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_point)

        val animator = ObjectAnimator.ofObject(
            pointF,
            "pointF",
            PointFView.PointFEvaluator(),
            PointF(150.dp, 250.dp)
        )
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()
    }


}