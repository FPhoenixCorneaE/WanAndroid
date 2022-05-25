package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @desc：分页数据的基类
 * @date：2022/05/23 15:55
 */
@Keep
@Parcelize
data class PageBean<T>(
    var datas: @RawValue MutableList<T>?,
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
) : Parcelable {

    /**
     * 是否第一页
     */
    fun isFirstPage(): Boolean {
        // WanAndroid 第一页该字段都为0
        return offset == 0
    }

    /**
     * 是否数据为空
     */
    fun isEmpty(): Boolean {
        // WanAndroid 第一页该字段都为0
        return isFirstPage() && datas.isNullOrEmpty()
    }

    /**
     * 是否还有更多数据
     */
    fun isNoMore(): Boolean {
        return over
    }
}
