package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeQaBinding

/**
 * @desc：首页问答Fragment
 * @date：2022/05/19 10:05
 */
class HomeQaFragment : BaseFragment<FragmentHomeQaBinding>() {

    private val mViewModel by viewModels<HomeViewModel>()
    private val mQaAdapter by lazy { HomeQaAdapter() }

    override fun FragmentHomeQaBinding.initViewBinding() {
        viewModel = mViewModel
        qaAdapter = mQaAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentHomeQaBinding.initObserver() {
        with(mViewModel) {
            homeQa.collectWithLifecycle(this@HomeQaFragment) {
                it?.parseResult(
                    fragment = this@HomeQaFragment,
                    onSuccess = {
                        it?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else if (it.isFirstPage()) {
                                mQaAdapter.submitList(it.datas)
                            } else {
                                it.datas?.let {
                                    val oldSize = mQaAdapter.items.size
                                    mQaAdapter.addAll(it)
                                    rvQa.scrollToPosition(oldSize)
                                }
                            }
                        }
                    },
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getHomeQa(isRefresh = true)
    }
}