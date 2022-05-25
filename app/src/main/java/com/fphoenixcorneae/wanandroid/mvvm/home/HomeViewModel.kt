package com.fphoenixcorneae.wanandroid.mvvm.home

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.jetpackmvvm.ext.requestNoCheck
import com.fphoenixcorneae.wanandroid.api.ApiResponse
import com.fphoenixcorneae.wanandroid.api.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：HomeViewModel
 * @date：2022/05/19 10:49
 */
class HomeViewModel : BaseViewModel() {

    /** 首页Banner */
    private val _homeBanner = MutableStateFlow<Result<MutableList<HomeBannerBean>>?>(null)
    val homeBanner = _homeBanner.asStateFlow()

    /** 首页置顶文章 */
    private val _homeTopArticle = MutableStateFlow<ApiResponse<MutableList<ArticleBean>>?>(null)
    val homeTopArticle = _homeTopArticle.asStateFlow()

    /** 首页文章列表 */
    private val _homeArticle = MutableStateFlow<Result<PageBean<ArticleBean>>?>(null)
    val homeArticle = _homeArticle.asStateFlow()

    /** 首页问答列表 */
    private val _homeQa = MutableStateFlow<Result<PageBean<ArticleBean>>?>(null)
    val homeQa = _homeQa.asStateFlow()

    /** 首页文章页面状态 */
    private val homeArticlePageState by lazy { PageState() }

    /** 首页问答页面状态 */
    private val homeQaPageState by lazy { PageState() }

    /**
     * 获取首页Banner
     */
    fun getHomeBanner() {
        request(
            block = {
                apiService.getHomeBanner()
            },
            result = _homeBanner
        )
    }

    /**
     * 获取首页置顶文章
     */
    fun getHomeTopArticle() {
        requestNoCheck(
            block = {
                apiService.getHomeTopArticle()
            },
            success = {
                _homeTopArticle.value = it
            }
        )
    }

    /**
     * 首页文章列表
     */
    fun getHomeArticle(isRefresh: Boolean) {
        homeArticlePageState.page.value = if (isRefresh) {
            0
        } else {
            homeArticlePageState.page.value + 1
        }
        homeArticlePageState.isRefreshing.value = isRefresh
        request(
            block = {
                apiService.getHomeArticle(page = homeArticlePageState.page.value)
            },
            result = _homeArticle
        )
    }

    /**
     * 首页问答列表
     */
    fun getHomeQa(isRefresh: Boolean) {
        homeQaPageState.page.value = if (isRefresh) {
            0
        } else {
            homeQaPageState.page.value + 1
        }
        homeQaPageState.isRefreshing.value = isRefresh
        request(
            block = {
                apiService.getHomeQa(page = homeQaPageState.page.value, cid = 440)
            },
            result = _homeQa
        )
    }
}