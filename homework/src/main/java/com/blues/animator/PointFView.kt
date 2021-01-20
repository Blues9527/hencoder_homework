package com.blues.animator

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp

class PointFView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        strokeWidth = 50.dp
    }

    var pointF = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPoint(pointF.x, pointF.y, paint)
    }


    class PointFEvaluator : TypeEvaluator<PointF> {

        override fun evaluate(fraction: Float, start: PointF, end: PointF): PointF {
            //start + (end - start)*fraction
            val pointX = start.x + (end.x - start.x) * fraction
            val pointY = start.y + (end.y - start.y) * fraction

            return PointF(pointX, pointY)
        }

    }
}