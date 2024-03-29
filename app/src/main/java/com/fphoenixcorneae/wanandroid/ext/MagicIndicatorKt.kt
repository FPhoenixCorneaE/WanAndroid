package com.fphoenixcorneae.wanandroid.ext

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager2.widget.ViewPager2
import com.fphoenixcorneae.common.ext.Dp
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.wanandroid.widget.magicindicator.helper.bindViewPager2
import com.fphoenixcorneae.wanandroid.widget.magicindicator.indicators.LinePagerIndicator
import com.fphoenixcorneae.wanandroid.widget.magicindicator.titles.ScaleTransitionPagerTitleView
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView

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
                    textSize = 24f
                    typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    text = pagerAdapter.getPageTitle(position = index).toString()
                    normalColor = appThemeViewModel.theme.value.surface
                    selectedColor = appThemeViewModel.theme.value.secondary
                    setOnClickListener { viewPager2.setCurrentItem(index, true) }
                }
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_WRAP_CONTENT
                    lineHeight = 8.Dp
                    roundRadius = 4.Dp
                    startInterpolator = AccelerateInterpolator()
                    endInterpolator = DecelerateInterpolator(2.0f)
                    xOffset = 2.Dp
                    yOffset = 12.Dp
                    setColors(appThemeViewModel.theme.value.onSecondary, Color.TRANSPARENT)
                }
            }
        }
    }
    bindViewPager2(viewPager2)
}