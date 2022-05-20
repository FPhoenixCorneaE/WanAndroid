package com.fphoenixcorneae.wanandroid.ext

import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.fphoenixcorneae.common.ext.applicationContext

@ColorInt
fun getThemeColor(@AttrRes themeAttrId: Int) = run {
    val typedArray = applicationContext.theme.obtainStyledAttributes(intArrayOf(themeAttrId))
    val color = typedArray.getColor(0, Color.TRANSPARENT)
    typedArray.recycle()
    color
}