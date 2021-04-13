package com.blues.text

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp
import com.blues.homework.R

private val IMAGE_SIZE = 150.dp
private val IMAGE_PADDING = 50.dp

class MultiLineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id condimentum nisl, id facilisis sem. Suspendisse dolor purus, vehicula sit amet lacus ac, maximus aliquet justo. Quisque porttitor, orci vitae pulvinar ullamcorper, orci nisi tincidunt felis, et laoreet mauris dui vitae sapien. Aliquam arcu eros, pulvinar eget tempor elementum, sodales in nisl. Nam nibh diam, mollis id turpis vitae, tincidunt pellentesque risus. Cras fermentum rhoncus facilisis. Praesent sollicitudin, eros in vehicula rhoncus, arcu augue accumsan quam, id finibus dolor elit iaculis leo. Ut ut nisl at eros tincidunt pretium. Morbi auctor leo et mollis mattis. Praesent faucibus, leo vitae vulputate placerat, massa quam volutpat turpis, auctor ultrices ligula erat id justo. Nulla ac justo arcu. Nullam in vulputate lacus. Praesent a est ut nisi venenatis feugiat ut vitae lectus. Fusce molestie, mauris et fringilla mollis, lectus sem tincidunt leo, nec euismod odio sapien ut metus. Nulla convallis eget odio in ultrices. Aenean egestas mauris quis dignissim dignissim."

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        val staticLayout =
//            StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//        staticLayout.draw(canvas)

        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING, paint)

        paint.getFontMetrics(fontMetrics)
        val measureWidth = floatArrayOf(0f)

        var start = 0
        var count: Int
        var maxWidth: Float
        var verticalOffset = -fontMetrics.top
        while (start < text.length) {
            maxWidth =
                if (verticalOffset + fontMetrics.bottom < IMAGE_PADDING || verticalOffset + fontMetrics.top > IMAGE_SIZE + IMAGE_PADDING) {
                    width.toFloat()
                } else {
                    width.toFloat() - IMAGE_SIZE
                }

            count =
                paint.breakText(
                    text,
                    start,
                    text.length,
                    true,
                    maxWidth,
                    measureWidth
                )
            canvas.drawText(text, start, start + count, 0f, verticalOffset, paint)
            start += count
            verticalOffset += paint.fontSpacing
        }
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




