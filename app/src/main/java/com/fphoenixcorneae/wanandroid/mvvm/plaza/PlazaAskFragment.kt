package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentPlazaAskBinding
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeQaAdapter

/**
 * @desc：广场每日一问Fragment
 * @date：2023/01/04 09:23
 */
class PlazaAskFragment : BaseFragment<FragmentPlazaAskBinding>() {

    private val mViewModel by viewModels<PlazaViewModel>()
    private val mAskAdapter by lazy { HomeQaAdapter() }

    override fun FragmentPlazaAskBinding.initViewBinding() {
        viewModel = mViewModel
        askAdapter = mAskAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentPlazaAskBinding.initObserver() {
        with(mViewModel) {
            plazaAsk.collectWithLifecycle(this@PlazaAskFragment) {
                it.parseResult(
                    fragment = this@PlazaAskFragment,
                    onSuccess = {
                        mViewModel.setAskRefreshing(false)
                        it?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else if (it.isFirstPage()) {
                                mAskAdapter.submitList(it.datas)
                            } else {
                                it.datas?.let {
                                    val oldSize = mAskAdapter.items.size
                                    mAskAdapter.addAll(it)
                                    rvAsk.scrollToPosition(oldSize)
                                }
                            }
                        }
                    },
                    onError = {
                        mViewModel.setAskRefreshing(false)
                    }
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getAsk()
    }
}