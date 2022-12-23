package com.fphoenixcorneae.wanandroid.theme

import android.graphics.Color
import com.fphoenixcorneae.common.ext.hexString2ColorInt
import com.fphoenixcorneae.jetpackmvvm.base.application.BaseApplication

/**
 * 切换主题, 全局可用
 */
val appThemeViewModel by lazy {
    BaseApplication.getInstance().getAndroidViewModel(AppThemeViewModel::class.java)
}

sealed interface Theme {
    val primary: Int
    val primaryVariant: Int
    val secondary: Int
    val secondaryVariant: Int
    val background: Int
    val surface: Int
    val error: Int
    val onPrimary: Int
    val onSecondary: Int
    val onBackground: Int
    val onSurface: Int
    val onError: Int
    val body1: Int
    val body2: Int
    val body3: Int
    val button1: Int
    val button2: Int
    val button3: Int
    val caption1: Int
    val caption2: Int
    val caption3: Int
    val subtitle1: Int
    val subtitle2: Int
    val subtitle3: Int

    object LightBlue : Theme {
        override val primary: Int get() = "#3F51B5".hexString2ColorInt()
        override val primaryVariant: Int get() = "#303F9F".hexString2ColorInt()
        override val secondary: Int get() = "#80DEEA".hexString2ColorInt()
        override val secondaryVariant: Int get() = secondary
        override val background: Int get() = Color.WHITE
        override val surface: Int get() = Color.WHITE
        override val error: Int get() = "#B00020".hexString2ColorInt()
        override val onPrimary: Int get() = "#6200EE".hexString2ColorInt()
        override val onSecondary: Int get() = Color.YELLOW
        override val onBackground: Int get() = Color.BLACK
        override val onSurface: Int get() = "#BB86FC".hexString2ColorInt()
        override val onError: Int get() = error
        override val body1: Int get() = "#333333".hexString2ColorInt()
        override val body2: Int get() = "#666666".hexString2ColorInt()
        override val body3: Int get() = "#999999".hexString2ColorInt()
        override val button1: Int get() = "#000000".hexString2ColorInt()
        override val button2: Int get() = "#FFFFFF".hexString2ColorInt()
        override val button3: Int get() = "#AAAAAA".hexString2ColorInt()
        override val caption1: Int get() = "#000000".hexString2ColorInt()
        override val caption2: Int get() = "#222222".hexString2ColorInt()
        override val caption3: Int get() = "#333333".hexString2ColorInt()
        override val subtitle1: Int get() = "#666666".hexString2ColorInt()
        override val subtitle2: Int get() = "#888888".hexString2ColorInt()
        override val subtitle3: Int get() = "#BBBBBB".hexString2ColorInt()
    }

    object DarkBlue : Theme {
        override val primary: Int get() = "#9FA8DA".hexString2ColorInt()
        override val primaryVariant: Int get() = "#303F9F".hexString2ColorInt()
        override val secondary: Int get() = "#80DEEA".hexString2ColorInt()
        override val secondaryVariant: Int get() = secondary
        override val background: Int get() = Color.BLACK
        override val surface: Int get() = Color.BLACK
        override val error: Int get() = Color.RED
        override val onPrimary: Int get() = Color.BLACK
        override val onSecondary: Int get() = Color.YELLOW
        override val onBackground: Int get() = Color.WHITE
        override val onSurface: Int get() = Color.WHITE
        override val onError: Int get() = error
        override val body1: Int get() = "#333333".hexString2ColorInt()
        override val body2: Int get() = "#666666".hexString2ColorInt()
        override val body3: Int get() = "#999999".hexString2ColorInt()
        override val button1: Int get() = "#000000".hexString2ColorInt()
        override val button2: Int get() = "#FFFFFF".hexString2ColorInt()
        override val button3: Int get() = "#AAAAAA".hexString2ColorInt()
        override val caption1: Int get() = "#000000".hexString2ColorInt()
        override val caption2: Int get() = "#222222".hexString2ColorInt()
        override val caption3: Int get() = "#333333".hexString2ColorInt()
        override val subtitle1: Int get() = "#666666".hexString2ColorInt()
        override val subtitle2: Int get() = "#888888".hexString2ColorInt()
        override val subtitle3: Int get() = "#BBBBBB".hexString2ColorInt()
    }
}