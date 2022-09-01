package com.fphoenixcorneae.wanandroid.mvvm.home

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.fphoenixcorneae.common.ext.dp
import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.launchRepeatOnLifecycle
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.wanandroid.widget.magicindicator.titles.ScaleTransitionPagerTitleView
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

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
        homeAdapter = mFragmentStateAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentHomeBinding.initView() {
        StatusBarUtil.setSmartPadding(mContext, flTopLayout)
    }

    override fun FragmentHomeBinding.initObserver() {
        with(appThemeViewModel) {
            launchRepeatOnLifecycle {
                theme.collect {
                    flMagicIndicator.setNavigator()
                }
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    private fun MagicIndicator.setNavigator() {
        navigator = CommonNavigator(mContext).apply {
            isAdjustMode = false
            isSkimOver = true
            adapter = object : CommonNavigatorAdapter() {
                override fun getCount(): Int = mFragmentStateAdapter.itemCount

                override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                    return ScaleTransitionPagerTitleView(context = mContext).apply {
                        minScale = 0.8f
                        textSize = 18f
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                        text = mFragmentStateAdapter.getPageTitle(position = index).toString()
                        normalColor = appThemeViewModel.theme.value.surface
                        selectedColor = appThemeViewModel.theme.value.secondary
                        setOnClickListener { mViewBinding.vpHome.setCurrentItem(index, true) }
                    }
                }

                override fun getIndicator(context: Context): IPagerIndicator {
                    return LinePagerIndicator(mContext).apply {
                        mode = LinePagerIndicator.MODE_EXACTLY
                        lineHeight = 4.dp.toFloat()
                        lineWidth = 40.dp.toFloat()
                        roundRadius = 8.dp.toFloat()
                        startInterpolator = AccelerateInterpolator()
                        endInterpolator = DecelerateInterpolator(2.0f)
                        setColors(appThemeViewModel.theme.value.surface)
                    }
                }
            }
        }
    }
}