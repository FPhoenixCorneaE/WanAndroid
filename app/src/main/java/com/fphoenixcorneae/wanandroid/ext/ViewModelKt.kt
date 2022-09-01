package com.fphoenixcorneae.wanandroid.ext

import com.fphoenixcorneae.jetpackmvvm.base.application.BaseApplication
import com.fphoenixcorneae.wanandroid.application.CommonViewModel

val commonViewModel by lazy {
    BaseApplication.getInstance().getAndroidViewModel(CommonViewModel::class.java)
}