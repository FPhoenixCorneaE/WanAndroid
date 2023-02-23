package com.fphoenixcorneae.wanandroid.mvvm.mine

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * @desc：用户信息
 * @date：2023/02/23 10:54
 */
@Keep
@Parcelize
data class UserInfoBean(
    /** 当前积分 */
    var coinCount: Int = 0,
    /** 当前级别 */
    var level: Int = 0,
    /** 当前排名 */
    var rank: Int = 0,
    /** 用户id */
    var userId: Int = 0,
    /** 用户名称 */
    var username: String? = null,
) : Parcelable


