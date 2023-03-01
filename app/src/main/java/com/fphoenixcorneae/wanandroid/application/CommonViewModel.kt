package com.fphoenixcorneae.wanandroid.application

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.wanandroid.mvvm.mine.UserManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：
 * @date：2022/08/31 10:56
 */
class CommonViewModel : BaseViewModel() {

    private val _hasLoggedOn = MutableStateFlow(UserManager.hasLoggedOn())
    val hasLoggedOn = _hasLoggedOn.asStateFlow()

    fun setLoginStatus(b: Boolean) {
        launch {
            _hasLoggedOn.emit(b)
        }
    }
}