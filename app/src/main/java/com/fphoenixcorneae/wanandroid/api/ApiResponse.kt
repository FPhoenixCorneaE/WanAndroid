package com.fphoenixcorneae.wanandroid.api

import com.fphoenixcorneae.coretrofit.model.BaseResponse

/**
 * @desc：封装返回的数据
 * @date：2022/05/20 14:00
 */
data class ApiResponse<T>(
    val errorCode: Int,
    val errorMsg: String?,
    val data: T?
) : BaseResponse<T>() {
    override fun getResponseCode(): Int = errorCode

    override fun getResponseData(): T? = data

    override fun getResponseMsg(): String? = errorMsg

    override fun isSuccess(): Boolean = errorCode == 0
}
