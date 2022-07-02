package com.fphoenixcorneae.wanandroid.mvvm.main

import androidx.lifecycle.viewModelScope
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @desc：MainViewModel
 * @date：2022/7/2 22:46
 */
class MainViewModel : BaseViewModel() {

    private val _navigationBlurBackground = MutableStateFlow<Any?>(null)
    val navigationBlurBackground = _navigationBlurBackground.asStateFlow()

    fun setNavigationBlurBackground(background: Any?) {
        viewModelScope.launch {
            _navigationBlurBackground.emit(background)
        }
    }
}