package com.fphoenixcorneae.wanandroid.application

import androidx.lifecycle.viewModelScope
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * @desc：EventViewModel
 * @date：2022/09/21 17:08
 */
class EventViewModel : BaseViewModel() {
    /** 搜索icon点击 */
    val searchIconClicked = Channel<Boolean>(Channel.CONFLATED)

    fun setSearchIconClicked(clicked: Boolean) {
        viewModelScope.launch {
            searchIconClicked.send(clicked)
        }
    }
}