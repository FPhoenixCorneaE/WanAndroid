package com.fphoenixcorneae.wanandroid.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.view.animation.*
import android.view.animation.Animation.AnimationListener
import androidx.core.content.res.ResourcesCompat
import com.fphoenixcorneae.wanandroid.R

/**
 * @desc：
 * @date：2023/02/28 14:00
 */
class ProgressButton @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val progressButtonDuration = 200
    private val scaleAnimationDuration = 300
    private val rotateAnimationDuration = 400
    private var mRectFPaint: Paint = Paint()
    private var mTextPaint: Paint = Paint()
    private var mProgressPaint: Paint = Paint()
    private var mStrokeWidth = 0
    private var mPadding = 0
    private var mSpace = 0f
    private var mRadius = 0f
    private var mProRadius = 0
    private val mStartAngle = 0f
    private var mProgressButtonAnim: ProgressButtonAnim? = null
    private var mProgressScaleAnim: ScaleAnimation? = null
    private var mProgressRotateAnim: RotateAnimation? = null
    private var mText = ""
    private var mTextColor = Color.WHITE
    private var mTextSize = dip2px(15f).toFloat()
    private var mTextBold = false
    private var mFontFamilyResId = 0
    private var mBgColor = Color.RED
    private var mProgressColor = Color.WHITE
    private var mStop = false
    private var mReset = false

    fun setBgColor(color: Int): ProgressButton {
        mBgColor = color
        return this
    }

    fun setTextColor(color: Int): ProgressButton {
        mTextColor = color
        return this
    }

    fun setTextSize(mTextSize: Float): ProgressButton {
        this.mTextSize = mTextSize
        return this
    }

    fun setProColor(color: Int): ProgressButton {
        mProgressColor = color
        return this
    }

    fun setButtonText(s: String): ProgressButton {
        mText = s
        invalidate()
        return this
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyleAttr, 0)
        mBgColor = typedArray.getColor(R.styleable.ProgressButton_pbBgColor, Color.RED)
        mProgressColor = typedArray.getColor(R.styleable.ProgressButton_pbProgressColor, Color.WHITE)
        mText = typedArray.getString(R.styleable.ProgressButton_pbText) ?: ""
        mTextColor = typedArray.getColor(R.styleable.ProgressButton_pbTextColor, Color.WHITE)
        mTextSize =
            typedArray.getDimension(R.styleable.ProgressButton_pbTextSize, dip2px(15f).toFloat())
        mTextBold = typedArray.getBoolean(R.styleable.ProgressButton_pbTextBold, false)
        mFontFamilyResId = typedArray.getResourceId(R.styleable.ProgressButton_pbFontFamily, 0)
        typedArray.recycle()
    }

    private fun initPaint() {
        mStrokeWidth = dip2px(2f)
        mPadding = dip2px(2f)
        mProRadius = measuredHeight / 5
        mProgressRotateAnim = RotateAnimation(
            0f, 360f, Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        mProgressRotateAnim!!.repeatCount = -1
        // 不停顿
        mProgressRotateAnim!!.interpolator = LinearInterpolator()
        // 停在最后
        mProgressRotateAnim!!.fillAfter = true
        mRectFPaint.isAntiAlias = true
        mRectFPaint.style = Paint.Style.FILL
        mRectFPaint.strokeWidth = mStrokeWidth.toFloat()
        mTextPaint.isAntiAlias = true
        mTextPaint.style = Paint.Style.FILL
        mProgressPaint.isAntiAlias = true
        mProgressPaint.style = Paint.Style.STROKE
        mProgressPaint.strokeWidth = (mStrokeWidth shr 1.toFloat().toInt()).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mTextPaint.color = mTextColor
        mTextPaint.textSize = mTextSize
        mTextPaint.isFakeBoldText = mTextBold
        if (!isInEditMode && mFontFamilyResId != 0) {
            mTextPaint.typeface = ResourcesCompat.getFont(context, mFontFamilyResId)
        }
        mRectFPaint.color = mBgColor
        mProgressPaint.color = mProgressColor
        // RectF对象
        val mRectF = RectF()
        mRectF.left = mPadding + mSpace
        mRectF.top = mPadding.toFloat()
        mRectF.right = measuredWidth - mPadding - mSpace
        mRectF.bottom = measuredHeight - mPadding.toFloat()
        mRadius = (measuredHeight - 2 * mPadding shr 1.toFloat().toInt()).toFloat()
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mRectFPaint)
        if (mRectF.width() == mRectF.height() && !mStop && !mReset) {
            val mRectFPro = RectF()
            mRectFPro.left = measuredWidth / 2.0f - mRectF.width() / 4
            mRectFPro.top = measuredHeight / 2.0f - mRectF.width() / 4
            mRectFPro.right = measuredWidth / 2.0f + mRectF.width() / 4
            mRectFPro.bottom = measuredHeight / 2.0f + mRectF.width() / 4
            canvas.drawArc(mRectFPro, mStartAngle, 100f, false, mProgressPaint)
        }
        if (mSpace < (measuredWidth - measuredHeight) / 2.0f) {
            canvas.drawText(
                mText,
                measuredWidth / 2.0f - getFontLength(mTextPaint, mText) / 2.0f,
                measuredHeight / 2.0f + getFontHeight(mTextPaint, mText) / 3.0f,
                mTextPaint
            )
        }
    }

    fun startAnim() {
        mReset = false
        mStop = false
        isClickable = false
        if (mProgressButtonAnim == null) {
            mProgressButtonAnim = ProgressButtonAnim()
            mProgressButtonAnim!!.duration = progressButtonDuration.toLong()
        }
        clearAnimation()
        startAnimation(mProgressButtonAnim)
    }

    fun startProAnim() {
        if (mProgressRotateAnim != null) {
            clearAnimation()
            mProgressRotateAnim!!.duration = rotateAnimationDuration.toLong()
            startAnimation(mProgressRotateAnim)
        }
    }

    fun stopAnim(mOnStopAnim: OnStopAnim) {
        clearAnimation()
        mStop = true
        invalidate()
        if (mProgressScaleAnim != null) {
            clearAnimation()
        } else {
            val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val width = wm.defaultDisplay.width
            mProgressScaleAnim = ScaleAnimation(
                1.0f,
                width / measuredHeight * 3.5f,
                1.0f,
                width / measuredHeight * 3.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
        mProgressScaleAnim!!.duration = scaleAnimationDuration.toLong()
        startAnimation(mProgressScaleAnim)
        mProgressScaleAnim!!.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                clearAnimation()
                mOnStopAnim.onStop()
            }
        })
    }

    fun reset() {
        mProgressButtonAnim?.cancel()
        mProgressRotateAnim?.cancel()
        mProgressScaleAnim?.cancel()
        mReset = true
        mProgressButtonAnim?.let {
            clearAnimation()
            it.duration = progressButtonDuration.toLong()
            it.setAnimationListener(object : AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    isClickable = true
                }
            })
            startAnimation(mProgressButtonAnim)
        }
    }

    private inner class ProgressButtonAnim : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation,
        ) {
            super.applyTransformation(interpolatedTime, t)
            val interpolatedTime = if (mReset) 1 - interpolatedTime else interpolatedTime
            mSpace = (measuredWidth - measuredHeight) / 2.0f * interpolatedTime
            invalidate()
            if (interpolatedTime == 1.0f && !mReset) {
                startProAnim()
            }
        }
    }

    private fun dip2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun getFontLength(paint: Paint?, str: String): Float {
        val rect = Rect()
        paint!!.getTextBounds(str, 0, str.length, rect)
        return rect.width().toFloat()
    }

    private fun getFontHeight(paint: Paint?, str: String): Float {
        val rect = Rect()
        paint!!.getTextBounds(str, 0, str.length, rect)
        return rect.height().toFloat()
    }

    interface OnStopAnim {
        fun onStop()
    }

    init {
        initAttrs(attrs, defStyleAttr)
        initPaint()
    }
}