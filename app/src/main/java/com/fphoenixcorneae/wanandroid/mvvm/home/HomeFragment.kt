package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeBinding
import com.fphoenixcorneae.wanandroid.ext.setNavigator
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter

/**
 * @desc：首页Fragment
 * @date：2022/05/06 10:04
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mFragmentStateAdapter by lazy {
        FragmentStatePager2ItemAdapter(
            this@HomeFragment,
            FragmentPagerItems.with(mContext)
                .add(R.string.title_fragment_home_article, HomeArticleFragment::class.java)
                .add(R.string.title_fragment_home_qa, HomeQaFragment::class.java)
                .create()
        )
    }

    override fun FragmentHomeBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentHomeBinding.initView() {
        StatusBarUtil.setSmartPadding(mContext, flTopLayout)
    }

    override fun FragmentHomeBinding.initObserver() {
        with(appThemeViewModel) {
            theme.collectWithLifecycle(this@HomeFragment) {
                flMagicIndicator.setNavigator(vpHome, mFragmentStateAdapter)
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}