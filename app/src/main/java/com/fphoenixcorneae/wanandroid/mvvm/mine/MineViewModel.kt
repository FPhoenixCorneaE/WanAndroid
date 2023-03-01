package com.fphoenixcorneae.wanandroid.mvvm.mine

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.home.PageState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：MineViewModel
 * @date：2023/02/20 11:38
 */
class MineViewModel : BaseViewModel() {
    private val _userIntegral = MutableStateFlow<UserIntegralBean?>(null)
    val userIntegral = _userIntegral.asStateFlow()

    /** 页面状态 */
    val pageState by lazy { PageState() }

    /**
     * 获取用户积分
     */
    fun getUserIntegral(isRefresh: Boolean = false) {
        setRefreshing(isRefresh = isRefresh)
        if (UserManager.hasLoggedOn()) {
            request(
                block = {
                    apiService.getUserIntegral()
                },
                success = {
                    launch { _userIntegral.emit(it) }
                },
            )
        }
        setRefreshing(isRefresh = false)
    }

    fun setRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            pageState.isRefreshing.emit(isRefresh)
        }
    }
}