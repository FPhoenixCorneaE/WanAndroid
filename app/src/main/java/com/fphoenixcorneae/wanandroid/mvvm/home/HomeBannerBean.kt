package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * @desc：首页Banner
 * @date：2019-10-24 13:04
 */
@Keep
@Parcelize
data class HomeBannerBean(
    var id: Int,
    var title: String?,
    var desc: String?,
    var type: Int,
    var url: String?,
    var imagePath: String?
) : Parcelable