package com.fphoenixcorneae.wanandroid.api

import com.fphoenixcorneae.wanandroid.constant.UrlConstants
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeBannerBean
import com.fphoenixcorneae.wanandroid.mvvm.splash.SplashBean
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import retrofit2.http.GET
import retrofit2.http.Headers


/**
 * @desc：ApiService
 * @date：2022/05/11 17:27
 */
interface ApiService {

    /**
     * 获取每日一图
     * http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
     */
    @Headers(RetrofitUrlManager.DOMAIN_NAME_HEADER.plus(UrlConstants.Domain.BING))
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    suspend fun getDailyImage(): SplashBean?

    /**
     * 获取首页Banner
     */
    @GET("banner/json")
    suspend fun getHomeBanner(): ApiResponse<ArrayList<HomeBannerBean>>
}