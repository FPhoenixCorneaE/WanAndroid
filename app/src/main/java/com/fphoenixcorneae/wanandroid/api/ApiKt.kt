package com.fphoenixcorneae.wanandroid.api

import com.fphoenixcorneae.coretrofit.RetrofitFactory

/** 双重校验锁式-单例, 封装 Service, 方便直接快速调用接口 */
val apiService by lazy {
    RetrofitFactory.createService<ApiService>()
}