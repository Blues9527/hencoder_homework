package com.blues.scaleableimageview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.blues.common.extension.dp
import com.blues.homework.R
import kotlin.math.max
import kotlin.math.min

private val IMAGE_SIZE = 300.dp.toInt()
private const val EXTRA_SCALE_FACTOR = 1.5f

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = getAvatar(IMAGE_SIZE)

    private val gestureListener = CustomGestureDetector()
    private val gestureDetector = GestureDetectorCompat(context, gestureListener)
    private val runnable = CustomFrameRunner()

    private val scaleGestureListener = CustomScaleGestureListener()
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    //原始偏移
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    //实际偏移
    private var offsetX = 0f
    private var offsetY = 0f

    private var smallScale = 0f

    private var largeScale = 0f

    //记录当前图片缩放状态
    private var isLarge = false

    private var scroller = OverScroller(context)

    //缩放因子
    var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    //缩放动画
    private val scaleAnimator =
        ObjectAnimator.ofFloat(this, "currentScale", smallScale, largeScale)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        originalOffsetX = (width - IMAGE_SIZE) / 2f
        originalOffsetY = (height - IMAGE_SIZE) / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            largeScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            largeScale = width / bitmap.width.toFloat()
            smallScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        }

        scaleAnimator.setFloatValues(smallScale, largeScale)
        currentScale = smallScale
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleFraction = (currentScale - smallScale) / (largeScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        //计算当前的缩放比例
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
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

    private fun fixOffset() {
        //偏移修正x轴
        offsetX = min(offsetX, ((bitmap.width * largeScale) - width) / 2)
        offsetX = max(offsetX, -((bitmap.width * largeScale) - width) / 2)

        //偏移修正y轴
        offsetY = min(offsetY, ((bitmap.height * largeScale) - height) / 2)
        offsetY = max(offsetY, -((bitmap.height * largeScale) - height) / 2)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        //使用gesture detector来处理触摸事件
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }


    inner class CustomGestureDetector : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isLarge) {
                scroller.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    (-((bitmap.width * largeScale) - width) / 2).toInt(),
                    (((bitmap.width * largeScale) - width) / 2).toInt(),
                    (-((bitmap.height * largeScale) - height) / 2).toInt(),
                    (((bitmap.height * largeScale) - height) / 2).toInt()
                )

                ViewCompat.postOnAnimation(this@ScalableImageView, runnable)
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            isLarge = !isLarge

            if (isLarge) {
                offsetX = (e.x - width / 2f) * (1 - (largeScale / smallScale))
                offsetY = (e.y - height / 2f) * (1 - (largeScale / smallScale))
                fixOffset()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {

            if (isLarge) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffset()
                invalidate()
            }

            return false
        }
    }

    inner class CustomScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - (largeScale / smallScale))
            offsetY = (detector.focusY - height / 2f) * (1 - (largeScale / smallScale))
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempScale = currentScale * detector.scaleFactor
            return if (tempScale > largeScale || tempScale < smallScale) {
                false
            } else {
                currentScale *= detector.scaleFactor
                true
            }
        }

    }

    inner class CustomFrameRunner : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()

                ViewCompat.postOnAnimation(this@ScalableImageView, this)
            }
        }

    }
}