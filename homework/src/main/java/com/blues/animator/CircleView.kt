package com.blues.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp


class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
    }

    var radius = 50.dp
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }
}