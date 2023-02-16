package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chad.library.adapter.base.QuickAdapterHelper
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeArticleBinding

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
    }

    override fun FragmentHomeArticleBinding.initObserver() {
        with(mViewModel) {
            // 首页Banner
            homeBanner.collectWithLifecycle(this@HomeArticleFragment) {
                mArticleHeaderAdapter.setItem(it, null)
            }
            // 首页文章列表
            homeArticle.collectWithLifecycle(this@HomeArticleFragment) {
                it.parseResult(
                    fragment = this@HomeArticleFragment,
                    onSuccess = {
                        it?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else if (it.isFirstPage()) {
                                mArticleAdapter.submitList(it.datas?.apply {
                                    // 首页置顶文章
                                    mViewModel.homeTopArticle.value?.let {
                                        addAll(0, it)
                                    }
                                })
                            } else {
                                it.datas?.let {
                                    val oldSize = mArticleAdapter.items.size
                                    mArticleAdapter.addAll(it)
                                    rvArticle.scrollToPosition(oldSize)
                                }
                            }
                        }
                    },
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getHomeArticleData()
    }
}