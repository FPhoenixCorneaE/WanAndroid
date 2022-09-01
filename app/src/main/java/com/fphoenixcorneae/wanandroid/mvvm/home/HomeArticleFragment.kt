package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.QuickAdapterHelper
import com.fphoenixcorneae.common.ext.isNotNullOrEmpty
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.launchRepeatOnLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeArticleBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @desc：首页文章Fragment
 * @date：2022/05/16 17:49
 */
class HomeArticleFragment : BaseFragment<FragmentHomeArticleBinding>() {

    private val mViewModel by viewModels<HomeViewModel>()
    private val mArticleHeaderAdapter by lazy { HomeArticleHeaderAdapter(lifecycle) }
    private val mArticleAdapter by lazy { HomeArticleAdapter() }

    override fun FragmentHomeArticleBinding.initViewBinding() {
        viewModel = mViewModel
        articleAdapter = QuickAdapterHelper.Builder(mArticleAdapter).build().apply {
            addBeforeAdapter(mArticleHeaderAdapter)
        }.adapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentHomeArticleBinding.initView() {
        srlRefresh.setOnRefreshListener {
            initData(null)
            viewLifecycleOwner.lifecycleScope.launch {
                delay(1000)
                srlRefresh.isRefreshing = false
            }
        }
    }

    override fun FragmentHomeArticleBinding.initObserver() {
        with(mViewModel) {
            // 首页Banner
            launchRepeatOnLifecycle {
                homeBanner.collect {
                    it.parseResult(
                        fragment = this@HomeArticleFragment,
                        onSuccess = { homeBanners ->
                            mArticleHeaderAdapter.setItem(homeBanners, null)
                        })
                }
            }
            // 首页文章列表
            launchRepeatOnLifecycle {
                homeArticle.collect {
                    it.parseResult(
                        fragment = this@HomeArticleFragment,
                        onSuccess = { pageBean ->
                            pageBean?.let {
                                if (it.isEmpty()) {
                                    showEmpty(null)
                                } else {
                                    showContent()
                                    if (it.isFirstPage()) {
                                        mArticleAdapter.submitList(it.datas?.apply {
                                            // 首页置顶文章
                                            mViewModel.homeTopArticle.value?.let {
                                                if (it.isSuccess()) {
                                                    it.getResponseData()?.let {
                                                        if (it.isNotNullOrEmpty()) {
                                                            addAll(0, it)
                                                        }
                                                    }
                                                }
                                            }
                                        })
                                    } else {
                                        it.datas?.let {
                                            val data = mArticleAdapter.items.toMutableList()
                                            val oldSize = data.size
                                            data.addAll(it)
                                            mArticleAdapter.submitList(data)
                                            rvArticle.scrollToPosition(oldSize)
                                        }
                                    }
                                }
                            }
                        })
                }
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.apply {
            getHomeBanner()
            getHomeTopArticle()
            getHomeArticle(isRefresh = true)
        }
    }
}