package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @desc：页面状态
 * @date：2022/05/23 16:33
 */
@Keep
@Parcelize
data class PageState(
    /** 页数 */
    val page: @RawValue MutableStateFlow<Int> = MutableStateFlow(0),
    /** 是否正在刷新 */
    val isRefreshing: @RawValue MutableStateFlow<Boolean> = MutableStateFlow(false)
) : Parcelable
