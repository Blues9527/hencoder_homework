package com.blues.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blues.homework.R
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera)

        val topFlipAnimator = ObjectAnimator.ofFloat(camera, "flipTop", -60f)
        topFlipAnimator.startDelay = 200
        topFlipAnimator.duration = 1000

        val bottomFlipAnimator = ObjectAnimator.ofFloat(camera, "flipBottom", 60f)
        bottomFlipAnimator.startDelay = 1000
        bottomFlipAnimator.duration = 1000

        val rotationFlipAnimator = ObjectAnimator.ofFloat(camera, "flipRotation", 270f)
        rotationFlipAnimator.startDelay = 200
        rotationFlipAnimator.duration = 1000


        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomFlipAnimator, rotationFlipAnimator, topFlipAnimator)
        animatorSet.start()

//        val p1 = PropertyValuesHolder.ofFloat("flipBottom", 30f)
//        val p2 = PropertyValuesHolder.ofFloat("flipRotation", 60f)
//        val p3 = PropertyValuesHolder.ofFloat("flipTop", 30f)
//        val animator = ObjectAnimator.ofPropertyValuesHolder(camera, p1, p2, p3)
//        animator.startDelay = 1000
//        animator.start()
    }

}