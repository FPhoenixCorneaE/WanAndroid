package com.fphoenixcorneae.wanandroid.application

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * @desc：EventViewModel
 * @date：2022/09/21 17:08
 */
class EventViewModel : BaseViewModel() {
    /** 搜索icon点击 */
    private val _searchIconClicked = Channel<Boolean>(Channel.CONFLATED)
    val searchIconClicked = _searchIconClicked.receiveAsFlow()

    fun setSearchIconClicked(clicked: Boolean) {
        launch {
            _searchIconClicked.send(clicked)
        }
    }
}