package com.fphoenixcorneae.wanandroid.mvvm.project

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * @desc：项目、公众号分类
 * @date：2020-06-16 10:32
 */
@Keep
@Parcelize
data class ClassifyBean(
    var children: List<String> = listOf(),
    var courseId: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: Int = 0,
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
) : Parcelable
