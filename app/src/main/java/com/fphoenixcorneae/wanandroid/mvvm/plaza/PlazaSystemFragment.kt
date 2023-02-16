package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentPlazaSystemBinding

/**
 * @desc：广场每日一问Fragment
 * @date：2023/01/04 09:23
 */
class PlazaSystemFragment : BaseFragment<FragmentPlazaSystemBinding>() {

    private val mViewModel by viewModels<PlazaViewModel>()
    private val mSystemAdapter by lazy { PlazaSystemAdapter() }

    override fun FragmentPlazaSystemBinding.initViewBinding() {
        viewModel = mViewModel
        systemAdapter = mSystemAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentPlazaSystemBinding.initObserver() {
        with(mViewModel) {
            plazaSystem.collectWithLifecycle(this@PlazaSystemFragment) {
                it.parseResult(
                    fragment = this@PlazaSystemFragment,
                    onSuccess = {
                        mSystemAdapter.submitList(it)
                    },
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getSystem()
    }
}