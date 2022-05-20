package com.fphoenixcorneae.wanandroid.mvvm.home

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.ApiResponse
import com.fphoenixcorneae.wanandroid.api.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：HomeViewModel
 * @date：2022/05/19 10:49
 */
class HomeViewModel : BaseViewModel() {

    private val _homeBanner = MutableStateFlow<Result<ApiResponse<ArrayList<HomeBannerBean>>>?>(null)
    val homeBanner = _homeBanner.asStateFlow()

    /**
     * 获取首页Banner
     */
    fun getHomeBanner() {
        request({
            apiService.getHomeBanner()
        }, result = _homeBanner)
    }
}