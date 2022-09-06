package com.fphoenixcorneae.wanandroid.mvvm.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.common.ext.toHtml
import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.constant.Constants
import com.fphoenixcorneae.wanandroid.databinding.FragmentProjectBinding
import com.fphoenixcorneae.wanandroid.ext.setNavigator
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter

/**
 * @desc：项目Fragment
 * @date：2022/08/30 16:13
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    private val mViewModel by viewModels<ProjectViewModel>()
    private val mFragmentPagerCreator by lazy { FragmentPagerItems.with(mContext) }
    private val mFragmentStateAdapter by lazy {
        FragmentStatePager2ItemAdapter(this, mFragmentPagerCreator.create())
    }

    override fun FragmentProjectBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentProjectBinding.initView() {
        StatusBarUtil.setSmartPadding(mContext, flMagicIndicator)
    }

    override fun FragmentProjectBinding.initObserver() {
        with(appThemeViewModel) {
            theme.collectWithLifecycle(this@ProjectFragment) {
                flMagicIndicator.setNavigator(vpProject, mFragmentStateAdapter)
            }
        }
        with(mViewModel) {
            projectClassify.collectWithLifecycle(this@ProjectFragment) {
                it?.let {
                    mFragmentPagerCreator.create().clear()
                    listOf(
                        ClassifyBean(name = getString(R.string.project_newest)),
                        *it.toTypedArray()
                    ).forEach {
                        mFragmentPagerCreator.add(
                            it.name.toHtml(),
                            ProjectChildFragment::class.java,
                            Bundle().apply {
                                putInt(Constants.Key.CLASSIFY_ID, it.id)
                                putBoolean(Constants.Key.NEWEST_PROJECT, it.id == 0)
                            })
                    }
                    flMagicIndicator.navigator?.notifyDataSetChanged()
                    vpProject.adapter?.notifyItemRangeChanged(0, mFragmentStateAdapter.itemCount)
                    vpProject.offscreenPageLimit = mFragmentStateAdapter.itemCount
                }
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getProjectClassify()
    }
}