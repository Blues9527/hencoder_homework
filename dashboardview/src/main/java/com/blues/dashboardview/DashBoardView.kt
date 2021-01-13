package com.blues.dashboardview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp
import kotlin.math.cos
import kotlin.math.sin

private val LENGTH = 120.dp
private val RADIUS = 150.dp
private const val SWEEP_ANGEL = 120f

class DashBoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    //创建画笔
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var dashPath = Path()
    private var rawPath = Path()
    private lateinit var effect: PathDashPathEffect

    init {
        paint.strokeWidth = 3.dp
        paint.style = Paint.Style.STROKE
        dashPath.addRect(0f, 0f, 3.dp, 8.dp, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rawPath.reset()
        rawPath.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + SWEEP_ANGEL / 2,
            360 - SWEEP_ANGEL
        )

        val pathMeasure = PathMeasure(rawPath, false)

        effect = PathDashPathEffect(
            dashPath,
            (pathMeasure.length - 3f) / 20f,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(rawPath, paint)

        paint.pathEffect = effect
        canvas.drawPath(rawPath, paint)
        paint.pathEffect = null

        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + LENGTH * cos(markToRadians(5)).toFloat(),
            height / 2f + LENGTH * sin(markToRadians(5).toFloat()),
            paint
        );
    }

    //计算弧度
    private fun markToRadians(mark: Int) =
        Math.toRadians((90 + SWEEP_ANGEL / 2 + mark * (360 - SWEEP_ANGEL) / 20).toDouble())

}