package com.fphoenixcorneae.wanandroid.ext

import com.fphoenixcorneae.jetpackmvvm.base.application.BaseApplication
import com.fphoenixcorneae.wanandroid.application.CommonViewModel
import com.fphoenixcorneae.wanandroid.application.EventViewModel

val commonViewModel by lazy {
    BaseApplication.getInstance().getAndroidViewModel(CommonViewModel::class.java)
}
val eventViewModel by lazy {
    BaseApplication.getInstance().getAndroidViewModel(EventViewModel::class.java)
}