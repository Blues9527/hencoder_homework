package com.blues.layoutsize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp
import kotlin.math.min

private val DEFAULT_PADDING = 100.dp

private val RADIUS = 100.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = ((DEFAULT_PADDING + RADIUS) * 2).toInt()

        val widthSpec = resolveSize(size, widthMeasureSpec)

        val heightSpec = resolveSize(size, heightMeasureSpec)

        setMeasuredDimension(widthSpec, heightSpec)

    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(DEFAULT_PADDING + RADIUS, DEFAULT_PADDING + RADIUS, RADIUS, paint)
    }
}