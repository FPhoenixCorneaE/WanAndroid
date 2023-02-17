package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentPlazaNavigationBinding

/**
 * @desc：广场导航Fragment
 * @date：2023/01/04 09:23
 */
class PlazaNavigationFragment : BaseFragment<FragmentPlazaNavigationBinding>() {

    private val mViewModel by viewModels<PlazaViewModel>()
    private val mNavigationAdapter by lazy { PlazaNavigationAdapter() }

    override fun FragmentPlazaNavigationBinding.initViewBinding() {
        viewModel = mViewModel
        navigationAdapter = mNavigationAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentPlazaNavigationBinding.initObserver() {
        with(mViewModel) {
            plazaNavigation.collectWithLifecycle(this@PlazaNavigationFragment) {
                it.parseResult(
                    fragment = this@PlazaNavigationFragment,
                    onSuccess = {
                        mNavigationAdapter.submitList(it)
                    },
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getNavigation()
    }
}