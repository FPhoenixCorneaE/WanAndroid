package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.os.Parcelable
import androidx.annotation.Keep
import com.fphoenixcorneae.wanandroid.mvvm.project.ClassifyBean
import kotlinx.parcelize.Parcelize

/**
 * @desc：广场体系数据
 * @date：2023/01/03 17:54
 */
@Keep
@Parcelize
data class SystemBean(
    var children: ArrayList<ClassifyBean>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int,
) : Parcelable
