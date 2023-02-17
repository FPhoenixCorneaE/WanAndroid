package com.fphoenixcorneae.wanandroid.mvvm.plaza

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

/**
 * @desc：
 * @date：2023/01/03 17:28
 */
class PlazaViewModel : BaseViewModel() {
    val articlePageState by lazy { PageState() }
    val askPageState by lazy { PageState() }
    val systemPageState by lazy { PageState() }
    val navigationPageState by lazy { PageState() }

    private val _plazaArticle: MutableStateFlow<Result<PageBean<ArticleBean>>?> = MutableStateFlow(null)
    val plazaArticle = _plazaArticle.asStateFlow()
    private val _plazaAsk: MutableStateFlow<Result<PageBean<ArticleBean>>?> = MutableStateFlow(null)
    val plazaAsk = _plazaAsk.asStateFlow()
    private val _plazaSystem: MutableStateFlow<Result<MutableList<SystemBean>>?> = MutableStateFlow(null)
    val plazaSystem = _plazaSystem.asStateFlow()
    private val _plazaNavigation: MutableStateFlow<Result<MutableList<NavigationBean>>?> = MutableStateFlow(null)
    val plazaNavigation = _plazaNavigation.asStateFlow()

    fun getArticle(isRefresh: Boolean = true) {
        setArticleRefreshing(isRefresh = isRefresh)
        launch {
            (if (isRefresh) 0 else articlePageState.page.first() + 1).also {
                setArticlePage(it)
                request(
                    block = {
                        apiService.getPlazaArticle(it)
                    },
                    result = _plazaArticle
                )
                setArticleRefreshing(isRefresh = false)
            }
        }
    }

    fun getAsk(isRefresh: Boolean = true) {
        setAskRefreshing(isRefresh = isRefresh)
        launch {
            (if (isRefresh) 1 else askPageState.page.first() + 1).also {
                setAskPage(it)
                request(
                    block = {
                        apiService.getPlazaAsk(it)
                    },
                    result = _plazaAsk
                )
                setAskRefreshing(isRefresh = false)
            }
        }
    }

    fun getSystem() {
        setSystemRefreshing(isRefresh = true)
        request(
            block = {
                apiService.getPlazaSystem()
            },
            result = _plazaSystem
        )
        setSystemRefreshing(isRefresh = false)
    }

    fun getNavigation() {
        setNavigationRefreshing(isRefresh = true)
        request(
            block = {
                apiService.getPlazaNavigation()
            },
            result = _plazaNavigation
        )
        setNavigationRefreshing(isRefresh = false)
    }

    fun setArticlePage(page: Int) {
        launch {
            articlePageState.page.emit(page)
        }
    }

    fun setArticleRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            articlePageState.isRefreshing.emit(isRefresh)
        }
    }

    fun setAskPage(page: Int) {
        launch {
            askPageState.page.emit(page)
        }
    }

    fun setAskRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            askPageState.isRefreshing.emit(isRefresh)
        }
    }

    fun setSystemRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            systemPageState.isRefreshing.emit(isRefresh)
        }
    }

    fun setNavigationRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            navigationPageState.isRefreshing.emit(isRefresh)
        }
    }
}