package com.fphoenixcorneae.wanandroid.mvvm.project

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：ProjectViewModel
 * @date：2022/09/01 11:37
 */
class ProjectViewModel : BaseViewModel() {

    /** 项目分类 */
    private val _projectClassify = MutableStateFlow<MutableList<ClassifyBean>?>(null)
    val projectClassify = _projectClassify.asStateFlow()

    /** 项目数据 */
    private val _projectData = MutableStateFlow<Result<PageBean<MutableList<ArticleBean>>>?>(null)
    val projectData = _projectData.asStateFlow()

    /** 页面状态 */
    private val pageState by lazy { PageState() }

    /** 项目分类id */
    private var mClassifyId = 0

    /**
     * 项目分类
     */
    fun getProjectClassify() {
        request(
            block = {
                apiService.getProjectClassify()
            },
            success = {
                _projectClassify.value = it
            }
        )
    }

    /**
     * 项目数据
     */
    fun getProjectData(classifyId: Int, isRefresh: Boolean) {
        mClassifyId = classifyId
        pageState.page.value = if (isRefresh) {
            0
        } else {
            pageState.page.value + 1
        }
        pageState.isRefreshing.value = isRefresh
        request(
            block = {
                apiService.getProjectDataByClassifyId(page = pageState.page.value, cid = classifyId)
            },
            result = _projectData
        )
    }

    /**
     * 加载更多项目
     */
    fun loadMoreProjectData() {
        getProjectData(classifyId = mClassifyId, isRefresh = false)
    }
}