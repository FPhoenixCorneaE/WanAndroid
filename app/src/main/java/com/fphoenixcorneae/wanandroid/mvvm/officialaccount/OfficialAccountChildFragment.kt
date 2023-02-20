package com.fphoenixcorneae.wanandroid.mvvm.officialaccount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.common.ext.toast
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.constant.Constants
import com.fphoenixcorneae.wanandroid.databinding.FragmentOfficialAccountChildBinding
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeArticleAdapter

/**
 * @desc：公众号的ChildFragment
 * @date：2023/02/20 10:53
 */
class OfficialAccountChildFragment : BaseFragment<FragmentOfficialAccountChildBinding>() {

    private val mViewModel by viewModels<OfficialAccountViewModel>()
    private val mOfficialAccountAdapter by lazy { HomeArticleAdapter() }

    override fun FragmentOfficialAccountChildBinding.initViewBinding() {
        viewModel = mViewModel
        officialAccountAdapter = mOfficialAccountAdapter
        classifyId = arguments?.getInt(Constants.Key.CLASSIFY_ID) ?: 0
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentOfficialAccountChildBinding.initObserver() {
        with(mViewModel) {
            officialAccountData.collectWithLifecycle(this@OfficialAccountChildFragment) {
                it?.parseResult(
                    fragment = this@OfficialAccountChildFragment,
                    onSuccess = {
                        it?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else if (it.isFirstPage()) {
                                mOfficialAccountAdapter.submitList(it.datas)
                            } else {
                                it.datas?.let {
                                    val oldSize = mOfficialAccountAdapter.items.size
                                    mOfficialAccountAdapter.addAll(it)
                                    rvOfficialAccount.scrollToPosition(oldSize)
                                }
                                if (it.isNoMore()) {
                                    toast(getString(R.string.no_more_data))
                                }
                            }
                        }
                    },
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getOfficialAccountData(mViewBinding.classifyId ?: 0)
    }
}