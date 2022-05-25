package com.fphoenixcorneae.wanandroid.application

import com.fphoenixcorneae.common.ext.*
import com.fphoenixcorneae.common.ext.algorithm.md5
import com.fphoenixcorneae.coretrofit.RetrofitFactory
import com.fphoenixcorneae.jetpackmvvm.base.application.BaseApplication
import com.fphoenixcorneae.wanandroid.constant.UrlConstants
import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * @desc：WanAndroidApp
 * @date：2022/05/06 09:29
 */
class WanAndroidApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initRetrofitUrlManager()
    }

    /**
     * 让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl
     */
    private fun initRetrofitUrlManager() {
        RetrofitUrlManager.getInstance().setDebug(isDebuggable)
        // 将每个 BaseUrl 进行初始化,运行时可以随时改变 DOMAIN_NAME 对应的值,从而达到切换 BaseUrl 的效果
        RetrofitUrlManager.getInstance().putDomain(UrlConstants.Domain.BING, UrlConstants.BING)
        RetrofitUrlManager.getInstance().putDomain(UrlConstants.Domain.WAN_ANDROID, UrlConstants.WAN_ANDROID)
        // 设置全局的 BaseUrl
        RetrofitUrlManager.getInstance().setGlobalDomain(UrlConstants.WAN_ANDROID)

        // 设置公共请求头
        RetrofitFactory.headers = hashMapOf(
            "platform" to "Android",
            "deviceId" to androidID,
            "deviceModel" to deviceModel,
            "versionCode" to appVersionCode.toString(),
            "versionName" to appVersionName,
            "sign" to appSignatureMD5,
        )
        // 设置公共请求参数
        RetrofitFactory.commonParams = hashMapOf("token" to androidID.md5())
    }
}