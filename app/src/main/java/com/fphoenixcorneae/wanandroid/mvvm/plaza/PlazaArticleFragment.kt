package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentPlazaArticleBinding
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeArticleAdapter

/**
 * @desc：广场文章Fragment
 * @date：2023/01/04 09:23
 */
class PlazaArticleFragment : BaseFragment<FragmentPlazaArticleBinding>() {

    private val mViewModel by viewModels<PlazaViewModel>()
    private val mArticleAdapter by lazy { HomeArticleAdapter() }

    override fun FragmentPlazaArticleBinding.initViewBinding() {
        viewModel = mViewModel
        articleAdapter = mArticleAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentPlazaArticleBinding.initObserver() {
        with(mViewModel) {
            plazaArticle.collectWithLifecycle(this@PlazaArticleFragment) {
                it.parseResult(
                    fragment = this@PlazaArticleFragment,
                    onSuccess = {
                        it?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else if (it.isFirstPage()) {
                                mArticleAdapter.submitList(it.datas)
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
        mViewModel.getArticle()
    }
}