package com.fphoenixcorneae.wanandroid.ext

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment

@ColorInt
fun Context.getThemeAttr(
    @AttrRes themeAttrId: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true,
) = run {
    theme.resolveAttribute(themeAttrId, typedValue, resolveRefs)
    typedValue.data
}

@ColorInt
fun Fragment.getThemeAttr(
    @AttrRes themeAttrId: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true,
) = run {
    requireContext().theme.resolveAttribute(themeAttrId, typedValue, resolveRefs)
    typedValue.data
}