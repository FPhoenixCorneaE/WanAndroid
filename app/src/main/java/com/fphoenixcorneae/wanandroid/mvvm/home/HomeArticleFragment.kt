package com.fphoenixcorneae.wanandroid.mvvm.home

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.common.ext.dp
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeArticleBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import kotlinx.coroutines.launch

/**
 * @desc：首页文章Fragment
 * @date：2022/05/16 17:49
 */
class HomeArticleFragment : BaseFragment<FragmentHomeArticleBinding>() {

    private val mViewModel by viewModels<HomeViewModel>()
    private val mBannerAdapter by lazy { HomeBannerAdapter() }

    override fun initViewBinding(): FragmentHomeArticleBinding {
        return FragmentHomeArticleBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initView() {
        mViewBinding.apply {
            homeBanner.setLifecycleRegistry(lifecycle)
                .setAdapter(mBannerAdapter as BaseBannerAdapter<Any>)
                .setInterval(3000)
                .setScrollDuration(1500)
                .setIndicatorGravity(IndicatorGravity.CENTER)
                .setIndicatorMargin(0, 0, 0, 20.dp)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setIndicatorSliderGap(8.dp)
                .setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                .setIndicatorSliderColor(Color.WHITE, Color.RED)
                .setIndicatorSliderRadius(4.dp)
                .setIndicatorHeight(4.dp)
                .setIndicatorSliderWidth(10.dp, 20.dp)
                .setPageMargin(20.dp)
                .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                .setRevealWidth(0.dp, 100.dp)
                .create()
        }
    }

    override fun initObserver() {
        // 首页Banner
        viewLifecycleOwner.lifecycleScope.launch {
            mViewModel.homeBanner.collect {
                it.parseResult(fragment = this@HomeArticleFragment, onSuccess = {
                    it?.getResponseData()?.let {
                        mViewBinding.homeBanner.refreshData(it)
                    }
                })
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getHomeBanner()
    }
}