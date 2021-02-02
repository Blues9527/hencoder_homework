import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blues.common.extension.dp
import com.blues.homework.R

val RADIUS = 150.dp
val PADDING = 10.dp

class CircleAvatarView(context: Context?, attributeSet: AttributeSet) : View(context, attributeSet) {

    //初始化画笔
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val count1 = canvas.saveLayer(RectF(width / 2f - RADIUS - PADDING, height / 2f - RADIUS - PADDING, width / 2f + RADIUS + PADDING, height / 2f + RADIUS + PADDING), null)
        canvas.drawCircle(width / 2f, height / 2f, RADIUS + PADDING, paint)
        canvas.restoreToCount(count1)

        val count2 = canvas.saveLayer(RectF(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS), null)
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)
        paint.xfermode = mode
        canvas.drawBitmap(getAvatar(2 * RADIUS.toInt()), width / 2f - RADIUS, height / 2f - RADIUS, paint)
        paint.xfermode = null
        canvas.restoreToCount(count2)
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