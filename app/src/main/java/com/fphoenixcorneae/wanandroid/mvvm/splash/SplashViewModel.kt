package com.fphoenixcorneae.wanandroid.mvvm.splash

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @desc：SplashViewModel
 * @date：2022/05/11 17:11
 */
class SplashViewModel : BaseViewModel() {

    private val _splashResult = MutableStateFlow<Result<SplashBean?>?>(null)
    val splashResult: StateFlow<Result<SplashBean?>?>
        get() = _splashResult

    private val _splashImg = MutableStateFlow<String?>(null)
    val splashImg: StateFlow<String?>
        get() = _splashImg

    /**
     * 获取每日一图
     */
    fun getDailyImage() {
        request(block = {
            apiService.getDailyImage()
        }, result = _splashResult)
    }

    fun setSplashImg(data: String?) {
        _splashImg.value = data
    }
}