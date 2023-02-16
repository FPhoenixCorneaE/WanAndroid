package com.fphoenixcorneae.wanandroid.mvvm.project

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
 * @desc：ProjectViewModel
 * @date：2022/09/01 11:37
 */
class ProjectViewModel : BaseViewModel() {

    /** 项目分类 */
    private val _projectClassify = MutableStateFlow<MutableList<ClassifyBean>?>(null)
    val projectClassify = _projectClassify.asStateFlow()

    /** 项目数据 */
    private val _projectData = MutableStateFlow<Result<PageBean<ArticleBean>>?>(null)
    val projectData = _projectData.asStateFlow()

    /** 页面状态 */
    val pageState by lazy { PageState() }

    /**
     * 项目分类
     */
    fun getProjectClassify() {
        request(
            block = {
                apiService.getProjectClassify()
            },
            success = {
                launch { _projectClassify.emit(it) }
            }
        )
    }

    /**
     * 项目数据
     */
    fun getProjectData(isNewest: Boolean, classifyId: Int, isRefresh: Boolean = true) {
        setRefreshing(isRefresh = isRefresh)
        launch {
            (if (isRefresh) 0 else pageState.page.first() + 1).also {
                setPage(it)
                request(
                    block = {
                        if (isNewest) {
                            apiService.getProjectNewestData(page = it)
                        } else {
                            apiService.getProjectDataByClassifyId(page = it, cid = classifyId)
                        }
                    },
                    result = _projectData
                )
                setRefreshing(isRefresh = false)
            }
        }
    }

    /**
     * 加载更多项目
     */
    fun loadMoreProjectData(isNewest: Boolean, classifyId: Int) {
        getProjectData(isNewest = isNewest, classifyId = classifyId, isRefresh = false)
    }

    fun setPage(page: Int) {
        launch {
            pageState.page.emit(page)
        }
    }

    fun setRefreshing(isRefresh: Boolean) {
        launch {
            if (!isRefresh) {
                delay(400)
            }
            pageState.isRefreshing.emit(isRefresh)
        }
    }
}