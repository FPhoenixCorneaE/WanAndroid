package com.fphoenixcorneae.wanandroid.mvvm.home

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

/**
 * @desc：HomeViewModel
 * @date：2022/05/19 10:49
 */
class HomeViewModel : BaseViewModel() {

    /** 首页Banner */
    private val _homeBanner = MutableStateFlow<MutableList<HomeBannerBean>?>(null)
    val homeBanner = _homeBanner.asStateFlow()

    /** 首页置顶文章 */
    private val _homeTopArticle = MutableStateFlow<MutableList<ArticleBean>?>(null)
    val homeTopArticle = _homeTopArticle.asStateFlow()

    /** 首页文章列表 */
    private val _homeArticle = MutableStateFlow<Result<PageBean<ArticleBean>>?>(null)
    val homeArticle = _homeArticle.asStateFlow()

    /** 首页问答列表 */
    private val _homeQa = MutableStateFlow<Result<PageBean<ArticleBean>>?>(null)
    val homeQa = _homeQa.asStateFlow()

    /** 首页文章页面状态 */
    val homeArticlePageState by lazy { PageState() }

    /** 首页问答页面状态 */
    val homeQaPageState by lazy { PageState() }

    fun getHomeArticleData() {
        getHomeBanner()
        getHomeTopArticle()
        getHomeArticle(isRefresh = true)
    }

    /**
     * 获取首页Banner
     */
    fun getHomeBanner() {
        request(
            block = {
                apiService.getHomeBanner()
            },
            success = {
                _homeBanner.value = it
            }
        )
    }

    /**
     * 获取首页置顶文章
     */
    fun getHomeTopArticle() {
        request(
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
        setHomeArticleRefreshing(isRefresh = isRefresh)
        launch {
            (if (isRefresh) 0 else homeArticlePageState.page.first() + 1).also {
                setHomeArticlePage(it)
                request(
                    block = {
                        apiService.getHomeArticle(page = it)
                    },
                    result = _homeArticle
                )
                setHomeArticleRefreshing(isRefresh = false)
            }
        }
    }

    /**
     * 加载更多文章
     */
    fun loadMoreHomeArticle() {
        getHomeArticle(isRefresh = false)
    }

    /**
     * 首页问答列表
     */
    fun getHomeQa(isRefresh: Boolean) {
        setHomeQaRefreshing(isRefresh = isRefresh)
        launch {
            (if (isRefresh) 0 else homeQaPageState.page.first() + 1).also {
                setHomeQaPage(it)
                request(
                    block = {
                        apiService.getHomeQa(page = it, cid = 440)
                    },
                    result = _homeQa
                )
                setHomeQaRefreshing(isRefresh = false)
            }
        }
    }

    /**
     * 加载更多问答
     */
    fun loadMoreHomeQa() {
        getHomeQa(isRefresh = false)
    }

    fun setHomeArticlePage(page: Int) {
        launch {
            homeArticlePageState.page.emit(page)
        }
    }

    fun setHomeArticleRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            homeArticlePageState.isRefreshing.emit(isRefresh)
        }
    }

    fun setHomeQaPage(page: Int) {
        launch {
            homeQaPageState.page.emit(page)
        }
    }

    fun setHomeQaRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            homeQaPageState.isRefreshing.emit(isRefresh)
        }
    }
}