package com.fphoenixcorneae.wanandroid.api

import com.fphoenixcorneae.wanandroid.constant.UrlConstants
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeBannerBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageBean
import com.fphoenixcorneae.wanandroid.mvvm.mine.UserInfoBean
import com.fphoenixcorneae.wanandroid.mvvm.plaza.NavigationBean
import com.fphoenixcorneae.wanandroid.mvvm.plaza.SystemBean
import com.fphoenixcorneae.wanandroid.mvvm.project.ClassifyBean
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
        @Path("page") page: Int,
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 首页问答列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeQa(
        @Path("page") page: Int,
        @Query("cid") cid: Int,
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 项目分类
     */
    @GET("/project/tree/json")
    suspend fun getProjectClassify(): ApiResponse<MutableList<ClassifyBean>>

    /**
     * 根据分类id获取项目数据
     */
    @GET("/project/list/{page}/json")
    suspend fun getProjectDataByClassifyId(
        @Path("page") page: Int,
        @Query("cid") cid: Int,
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 获取最新项目数据
     */
    @GET("/article/listproject/{page}/json")
    suspend fun getProjectNewestData(
        @Path("page") page: Int,
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 获取广场文章列表数据
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getPlazaArticle(
        @Path("page") page: Int,
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 获取广场每日一问列表数据
     */
    @GET("/wenda/list/{page}/json")
    suspend fun getPlazaAsk(
        @Path("page") page: Int,
    ): ApiResponse<PageBean<ArticleBean>>


    /**
     * 获取广场体系数据
     */
    @GET("/tree/json")
    suspend fun getPlazaSystem(): ApiResponse<MutableList<SystemBean>>

    /**
     * 获取广场体系文章数据
     */
    @GET("/article/list/{page}/json")
    suspend fun getPlazaArticleById(
        @Path("page") page: Int,
        @Query("cid") cid: Int,
    ): ApiResponse<PageBean<ArticleBean>>


    /**
     * 获取广场导航数据
     */
    @GET("/navi/json")
    suspend fun getPlazaNavigation(): ApiResponse<MutableList<NavigationBean>>

    /**
     * 公众号分类
     */
    @GET("wxarticle/chapters/json")
    suspend fun getOfficialAccountClassify(): ApiResponse<MutableList<ClassifyBean>>

    /**
     * 获取公众号数据
     */
    @GET("wxarticle/list/{cid}/{page}/json")
    suspend fun getOfficialAccountDataByClassifyId(
        @Path("page") page: Int,
        @Path("cid") cid: Int,
    ): ApiResponse<PageBean<ArticleBean>>

    /**
     * 获取用户信息
     */
    @GET("/lg/coin/userinfo/json")
    fun getUserInfo(): ApiResponse<UserInfoBean>
}