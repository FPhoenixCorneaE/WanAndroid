package com.fphoenixcorneae.wanandroid.api

import com.fphoenixcorneae.wanandroid.constant.UrlConstants
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeBannerBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageBean
import com.fphoenixcorneae.wanandroid.mvvm.splash.SplashBean
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


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
    @GET("/banner/json")
    suspend fun getHomeBanner(): ApiResponse<MutableList<HomeBannerBean>>

    /**
     * 首页置顶文章集合数据
     */
    @GET("/article/top/json")
    suspend fun getHomeTopArticle(): ApiResponse<MutableList<ArticleBean>>

    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(
        @Path("page") page: Int
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 首页问答列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeQa(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResponse<PageBean<ArticleBean>>
}