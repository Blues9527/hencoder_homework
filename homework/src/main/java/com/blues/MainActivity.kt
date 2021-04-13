package com.blues

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blues.animator.CameraActivity
import com.blues.animator.PointFActivity
import com.blues.animator.ProvinceActivity
import com.blues.animator.ScaleActivity
import com.blues.homework.R
import com.blues.layout.ColorTextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tag.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
    }

}
