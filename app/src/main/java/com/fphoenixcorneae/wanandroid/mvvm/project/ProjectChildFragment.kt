package com.fphoenixcorneae.wanandroid.mvvm.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.constant.Constants
import com.fphoenixcorneae.wanandroid.databinding.FragmentProjectChildBinding

/**
 * @desc：项目的ChildFragment
 * @date：2022/09/01 14:04
 */
class ProjectChildFragment : BaseFragment<FragmentProjectChildBinding>() {

    private val mViewModel by viewModels<ProjectViewModel>()
    private val mProjectAdapter by lazy { ProjectChildAdapter() }

    override fun FragmentProjectChildBinding.initViewBinding() {
        viewModel = mViewModel
        projectAdapter = mProjectAdapter
        classifyId = arguments?.getInt(Constants.Key.CLASSIFY_ID) ?: 0
        isNewest = arguments?.getBoolean(Constants.Key.NEWEST_PROJECT) ?: false
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentProjectChildBinding.initObserver() {
        with(mViewModel) {
            projectData.collectWithLifecycle(this@ProjectChildFragment) {
                it?.parseResult(
                    fragment = this@ProjectChildFragment,
                    onSuccess = {
                        setRefreshing(isRefreshing = false)
                        it?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else if (it.isFirstPage()) {
                                mProjectAdapter.submitList(it.datas)
                            } else {
                                it.datas?.let {
                                    val oldSize = mProjectAdapter.items.size
                                    mProjectAdapter.addAll(it)
                                    rvProject.scrollToPosition(oldSize)
                                }
                            }
                        }
                    },
                    onError = {
                        setRefreshing(isRefreshing = false)
                    }
                )
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getProjectData(mViewBinding.isNewest ?: false, mViewBinding.classifyId ?: 0)
    }
}