package com.blues.animator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.blues.common.extension.dp
import com.blues.homework.R

val PADDING = 100.dp
val SIZE = 200.dp

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var camera = Camera()

    var flipBottom = 30f
        set(value) {
            field = value
            invalidate()
        }

    var flipTop = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)

    }

    override fun onDraw(canvas: Canvas) {

        canvas.save()
        canvas.translate(PADDING + SIZE / 2, PADDING + SIZE / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(flipTop)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-SIZE, -SIZE, SIZE, 0f)
        canvas.rotate(flipRotation)
        canvas.translate(-(PADDING + SIZE / 2), -(PADDING + SIZE / 2))
        canvas.drawBitmap(getAvatar(SIZE.toInt()), PADDING, PADDING, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(PADDING + SIZE / 2, PADDING + SIZE / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(flipBottom)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-SIZE, 0f, SIZE, SIZE)
        canvas.rotate(flipRotation)
        canvas.translate(-(PADDING + SIZE / 2), -(PADDING + SIZE / 2))
        canvas.drawBitmap(getAvatar(SIZE.toInt()), PADDING, PADDING, paint)
        canvas.restore()

    }


    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        //第一次解析，快速获取bitmap信息
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }
}