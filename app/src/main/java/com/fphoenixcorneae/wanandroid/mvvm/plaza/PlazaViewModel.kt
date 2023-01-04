package com.fphoenixcorneae.wanandroid.mvvm.plaza

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

/**
 * @desc：
 * @date：2023/01/03 17:28
 */
class PlazaViewModel : BaseViewModel() {
    val articlePageState by lazy { PageState() }

    private val _plazaArticle: MutableStateFlow<Result<PageBean<ArticleBean>>?> = MutableStateFlow(null)
    val plazaArticle = _plazaArticle.asStateFlow()

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
            }
        }
    }

    fun setArticlePage(page: Int) {
        launch {
            articlePageState.page.emit(page)
        }
    }

    fun setArticleRefreshing(isRefresh: Boolean) {
        launch {
            articlePageState.isRefreshing.emit(isRefresh)
        }
    }
}