package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.os.Parcelable
import androidx.annotation.Keep
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import kotlinx.parcelize.Parcelize

/**
 * @desc：广场导航数据
 * @date：2023/01/03 17:55
 */
@Keep
@Parcelize
data class NavigationBean(
    var articles: ArrayList<ArticleBean>,
    var cid: Int,
    var name: String,
) : Parcelable
