package com.fphoenixcorneae.wanandroid.theme

import android.graphics.Color
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
        override val primary: Int get() = 0xFF3F51B5.toInt()
        override val primaryVariant: Int get() = 0xFF303F9F.toInt()
        override val secondary: Int get() = 0xFF80DEEA.toInt()
        override val secondaryVariant: Int get() = secondary
        override val background: Int get() = Color.WHITE
        override val surface: Int get() = Color.WHITE
        override val error: Int get() = 0xFFB00020.toInt()
        override val onPrimary: Int get() = 0xFF6200EE.toInt()
        override val onSecondary: Int get() = Color.YELLOW
        override val onBackground: Int get() = Color.BLACK
        override val onSurface: Int get() = 0xFFBB86FC.toInt()
        override val onError: Int get() = error
        override val body1: Int get() = 0xFF333333.toInt()
        override val body2: Int get() = 0xFF666666.toInt()
        override val body3: Int get() = 0xFF999999.toInt()
        override val button1: Int get() = 0xFF000000.toInt()
        override val button2: Int get() = 0xFFFFFFFF.toInt()
        override val button3: Int get() = 0xFFAAAAAA.toInt()
        override val caption1: Int get() = 0xFF000000.toInt()
        override val caption2: Int get() = 0xFF222222.toInt()
        override val caption3: Int get() = 0xFF333333.toInt()
        override val subtitle1: Int get() = 0xFF666666.toInt()
        override val subtitle2: Int get() = 0xFF888888.toInt()
        override val subtitle3: Int get() = 0xFFBBBBBB.toInt()
    }

    object DarkBlue : Theme {
        override val primary: Int get() = 0xFF9FA8DA.toInt()
        override val primaryVariant: Int get() = 0xFF303F9F.toInt()
        override val secondary: Int get() = 0xFF80DEEA.toInt()
        override val secondaryVariant: Int get() = secondary
        override val background: Int get() = Color.BLACK
        override val surface: Int get() = Color.BLACK
        override val error: Int get() = Color.RED
        override val onPrimary: Int get() = Color.BLACK
        override val onSecondary: Int get() = Color.YELLOW
        override val onBackground: Int get() = Color.WHITE
        override val onSurface: Int get() = Color.WHITE
        override val onError: Int get() = error
        override val body1: Int get() = 0xFF333333.toInt()
        override val body2: Int get() = 0xFF666666.toInt()
        override val body3: Int get() = 0xFF999999.toInt()
        override val button1: Int get() = 0xFF000000.toInt()
        override val button2: Int get() = 0xFFFFFFFF.toInt()
        override val button3: Int get() = 0xFFAAAAAA.toInt()
        override val caption1: Int get() = 0xFF000000.toInt()
        override val caption2: Int get() = 0xFF222222.toInt()
        override val caption3: Int get() = 0xFF333333.toInt()
        override val subtitle1: Int get() = 0xFF666666.toInt()
        override val subtitle2: Int get() = 0xFF888888.toInt()
        override val subtitle3: Int get() = 0xFFBBBBBB.toInt()
    }
}