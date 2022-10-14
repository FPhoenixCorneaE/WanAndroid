package com.fphoenixcorneae.wanandroid.mvvm.splash

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.requestNoCheck
import com.fphoenixcorneae.wanandroid.api.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：SplashViewModel
 * @date：2022/05/11 17:11
 */
class SplashViewModel : BaseViewModel() {

    private val _splashResult = MutableStateFlow<SplashBean?>(null)
    val splashResult = _splashResult.asStateFlow()

    private val _splashImg = MutableStateFlow<String?>(null)
    val splashImg = _splashImg.asStateFlow()

    private val _logoVisible = MutableStateFlow(true)
    val logoVisible = _logoVisible.asStateFlow()

    /**
     * 获取每日一图
     */
    fun getDailyImage() {
        requestNoCheck(
            block = {
                apiService.getDailyImage()
            },
            success = {
                _splashResult.value = it
            },
            error = {
                _splashResult.value = null
            }
        )
    }

    fun setSplashImg(data: String?) {
        launch {
            _splashImg.value = data
            _logoVisible.value = data.isNullOrEmpty()
        }
    }
}