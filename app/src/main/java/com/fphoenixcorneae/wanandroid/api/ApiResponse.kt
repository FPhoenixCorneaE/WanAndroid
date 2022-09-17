package com.fphoenixcorneae.wanandroid.api

import androidx.annotation.Keep
import com.fphoenixcorneae.coretrofit.model.BaseResponse

/**
 * @desc：封装返回的数据
 * @date：2022/05/20 14:00
 */
@Keep
data class ApiResponse<T>(
    private val errorCode: Int,
    private val errorMsg: String?,
    private val data: T?
) : BaseResponse<T>() {
    override fun getResponseCode(): Int = errorCode

    override fun getResponseData(): T? = data

    override fun getResponseMsg(): String? = errorMsg

    override fun isSuccess(): Boolean = errorCode == 0
}
