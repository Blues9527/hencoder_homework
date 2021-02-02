package com.blues.cameraview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp
import com.blues.homework.R

val PADDING = 100.dp
val SIZE = 200.dp

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var camera = Camera()

    init {
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate(PADDING + SIZE / 2, PADDING + SIZE / 2)
        canvas.rotate(-30f)
        canvas.clipRect(-SIZE, -SIZE, SIZE, 0f )
        canvas.rotate(30f)
        canvas.translate(-(PADDING + SIZE / 2), -(PADDING + SIZE / 2))
        canvas.drawBitmap(getAvatar(SIZE.toInt()), PADDING, PADDING, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(PADDING + SIZE / 2, PADDING + SIZE / 2)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-SIZE, 0f, SIZE, SIZE)
        canvas.rotate(30f)
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