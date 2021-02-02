package com.blues.pieview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150.dp
private const val OFFSET = 50f

class PieView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var angels = floatArrayOf(30f, 60f, 90f, 120f, 60f)
    private var colors = listOf(Color.BLUE, Color.LTGRAY, Color.GREEN, Color.YELLOW, Color.RED)
    private var startAngel = 0f

    private var mark = angels.size - 2


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((index, value) in angels.withIndex()) {
            paint.color = colors[index]
            if (index == mark) {
                canvas.save()
                canvas.translate(
                    OFFSET * cos(Math.toRadians((startAngel + angels[mark] / 2).toDouble())).toFloat(),
                    OFFSET * sin(Math.toRadians((startAngel + angels[mark] / 2).toDouble())).toFloat()
                )
            }
            canvas.drawArc(
                width / 2 - RADIUS,
                height / 2 - RADIUS,
                width / 2 + RADIUS,
                height / 2 + RADIUS,
                startAngel,
                value,
                true, paint
            )
            startAngel += value
            if (index == mark) {
                canvas.restore()
            }
        }
    }
}