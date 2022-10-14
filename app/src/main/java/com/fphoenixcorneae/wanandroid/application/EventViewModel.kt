package com.fphoenixcorneae.wanandroid.application

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import kotlinx.coroutines.channels.Channel

/**
 * @desc：EventViewModel
 * @date：2022/09/21 17:08
 */
class EventViewModel : BaseViewModel() {
    /** 搜索icon点击 */
    val searchIconClicked = Channel<Boolean>(Channel.CONFLATED)

    fun setSearchIconClicked(clicked: Boolean) {
        launch {
            searchIconClicked.send(clicked)
        }
    }
}