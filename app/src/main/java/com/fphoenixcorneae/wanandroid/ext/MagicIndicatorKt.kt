package com.fphoenixcorneae.wanandroid.ext

import android.content.Context
import android.graphics.Typeface
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager2.widget.ViewPager2
import com.fphoenixcorneae.common.ext.Dp
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.wanandroid.widget.magicindicator.helper.bindViewPager2
import com.fphoenixcorneae.wanandroid.widget.magicindicator.titles.ScaleTransitionPagerTitleView
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 * Set navigator
 */
fun MagicIndicator.setNavigator(
    viewPager2: ViewPager2,
    pagerAdapter: FragmentStatePager2ItemAdapter,
    adjustMode: Boolean = false,
) {
    viewPager2.apply {
        offscreenPageLimit = if (pagerAdapter.itemCount > 1) pagerAdapter.itemCount else 1
        adapter = pagerAdapter
    }
    navigator = CommonNavigator(context).apply {
        isAdjustMode = adjustMode
        isSkimOver = true
        adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int = pagerAdapter.itemCount

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                return ScaleTransitionPagerTitleView(context = context).apply {
                    minScale = 0.8f
                    textSize = 18f
                    typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    text = pagerAdapter.getPageTitle(position = index).toString()
                    normalColor = appThemeViewModel.theme.value.surface
                    selectedColor = appThemeViewModel.theme.value.secondary
                    setOnClickListener { viewPager2.setCurrentItem(index, true) }
                }
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_EXACTLY
                    lineHeight = 4.Dp
                    lineWidth = 40.Dp
                    roundRadius = 8.Dp
                    startInterpolator = AccelerateInterpolator()
                    endInterpolator = DecelerateInterpolator(2.0f)
                    setColors(appThemeViewModel.theme.value.surface)
                }
            }
        }
    }
    bindViewPager2(viewPager2)
}