package com.fphoenixcorneae.wanandroid.mvvm.login

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * @desc：用户信息
 * @date：2023/03/01 14:41
 */
@Keep
@Parcelize
data class UserInfoBean(
    var admin: Boolean = false,
    var chapterTops: MutableList<String>? = mutableListOf(),
    var collectIds: MutableList<String>? = mutableListOf(),
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = "",
) : Parcelable
