package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeBinding
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter

/**
 * @desc：首页Fragment
 * @date：2022/05/06 10:04
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initView() {
        mViewBinding.apply {
            StatusBarUtil.setSmartPadding(mContext, flTopLayout)
            vpHome.adapter =
                FragmentStatePager2ItemAdapter(
                    this@HomeFragment,
                    FragmentPagerItems.with(mContext)
                        .add(R.string.title_fragment_home_article, HomeArticleFragment::class.java)
                        .create()
                )
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}