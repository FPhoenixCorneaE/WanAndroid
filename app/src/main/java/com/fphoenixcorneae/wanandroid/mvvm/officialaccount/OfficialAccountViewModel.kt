package com.fphoenixcorneae.wanandroid.mvvm.officialaccount

import com.fphoenixcorneae.coretrofit.model.Result
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageBean
import com.fphoenixcorneae.wanandroid.mvvm.home.PageState
import com.fphoenixcorneae.wanandroid.mvvm.project.ClassifyBean
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

/**
 * @desc：OfficialAccountViewModel
 * @date：2023/02/20 10:44
 */
class OfficialAccountViewModel : BaseViewModel() {

    /** 公众号分类 */
    private val _officialAccountClassify = MutableStateFlow<MutableList<ClassifyBean>?>(null)
    val officialAccountClassify = _officialAccountClassify.asStateFlow()

    /** 公众号数据 */
    private val _officialAccountData = MutableStateFlow<Result<PageBean<ArticleBean>>?>(null)
    val officialAccountData = _officialAccountData.asStateFlow()

    /** 页面状态 */
    val pageState by lazy { PageState() }

    /**
     * 公众号分类
     */
    fun getOfficialAccountClassify() {
        request(
            block = {
                apiService.getOfficialAccountClassify()
            },
            success = {
                launch { _officialAccountClassify.emit(it) }
            }
        )
    }

    /**
     * 公众号数据
     */
    fun getOfficialAccountData(classifyId: Int, isRefresh: Boolean = true) {
        setRefreshing(isRefresh = isRefresh)
        launch {
            (if (isRefresh) 0 else pageState.page.first() + 1).also {
                setPage(it)
                request(
                    block = {
                        apiService.getOfficialAccountDataByClassifyId(page = it, cid = classifyId)
                    },
                    result = _officialAccountData
                )
                setRefreshing(isRefresh = false)
            }
        }
    }

    /**
     * 加载更多公众号数据
     */
    fun loadMoreOfficialAccountData(classifyId: Int) {
        getOfficialAccountData(classifyId = classifyId, isRefresh = false)
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