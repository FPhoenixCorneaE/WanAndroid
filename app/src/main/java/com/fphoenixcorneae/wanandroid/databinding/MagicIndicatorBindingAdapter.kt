package com.fphoenixcorneae.wanandroid.databinding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fphoenixcorneae.wanandroid.widget.magicindicator.helper.bindViewPager2
import net.lucode.hackware.magicindicator.MagicIndicator

@BindingAdapter("bindViewPager2")
fun bindViewPager2(view: MagicIndicator, viewPager2: ViewPager2) {
    view.bindViewPager2(viewPager2)
}