package com.fphoenixcorneae.wanandroid.mvvm.mine

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * @desc：
 * @date：2023/02/23 15:45
 */
@Keep
@Parcelize
data class MineItemBean(
    val iconRes: Int? = null,
    val name: String? = null,
    val value: String? = null,
) : Parcelable
