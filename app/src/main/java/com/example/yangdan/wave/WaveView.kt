package com.example.yangdan.wave

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Created by yangdan on 2017/8/27.
 */

class WaveView : View {
    internal var mAbovePath: Path = Path()
    internal var mBelowPath: Path  = Path()
    internal var mAbovePaint: Paint = Paint()
    internal var mBelowPaint: Paint = Paint()
    internal var φ: Float = 0.toFloat()
    internal var w: Double = 0.toDouble()
    private var mListener: OnWaveAnimationListener? = null

    fun setmListener(listener: OnWaveAnimationListener) {
        this.mListener = listener
    }

    private fun init() {
        mAbovePaint.style = Paint.Style.FILL
        mAbovePaint.isAntiAlias = true
        mAbovePaint.color = Color.WHITE

        mBelowPaint.style = Paint.Style.FILL
        mBelowPaint.isAntiAlias = true
        mBelowPaint.color = Color.WHITE
        mBelowPaint.alpha = 80

    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mAbovePath.reset()
        mBelowPath.reset()
        mAbovePath.moveTo(left.toFloat(), bottom.toFloat())
        mBelowPath.moveTo(left.toFloat(), bottom.toFloat())

        φ = φ - 0.1f
        w = 2 * Math.PI / width
        var x = 0f
        while (x <= width) {
            val y = (8 * Math.cos(w * x + φ) + 8).toFloat()
            val y1 = (8 * Math.sin(w * x + φ) + 8).toFloat()
            mAbovePath.lineTo(x, y)
            mBelowPath.lineTo(x, y1)
            if (mListener != null) {
                mListener!!.onWaveAinmation(y)
            }
            x = x + 20
        }
        mAbovePath.lineTo(width.toFloat(), bottom.toFloat())
        mBelowPath.lineTo(width.toFloat(), bottom.toFloat())

        canvas.drawPath(mAbovePath, mAbovePaint)
        canvas.drawPath(mBelowPath, mBelowPaint)

        postInvalidateDelayed(20)
    }

    interface OnWaveAnimationListener {
        fun onWaveAinmation(y: Float)
    }

}
