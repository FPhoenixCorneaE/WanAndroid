package com.fphoenixcorneae.wanandroid.mvvm.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.chad.library.adapter.base.BaseSingleItemAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.common.ext.dp
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.RecyclerItemHomeArticleHeaderBinding
import com.fphoenixcorneae.wanandroid.ext.getThemeAttrColor
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

/**
 * @desc：
 * @date：2022/08/31 16:36
 */
class HomeArticleHeaderAdapter(
    private val lifecycle: Lifecycle,
) : BaseSingleItemAdapter<List<HomeBannerBean>, DataBindingHolder<RecyclerItemHomeArticleHeaderBinding>>() {
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<RecyclerItemHomeArticleHeaderBinding> {
        return DataBindingHolder(RecyclerItemHomeArticleHeaderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<RecyclerItemHomeArticleHeaderBinding>,
        item: List<HomeBannerBean>?,
    ) {
        with(holder.binding) {
            rlBanner.setLifecycleRegistry(lifecycle)
                .setAdapter(HomeBannerAdapter())
                .setInterval(3000)
                .setScrollDuration(1500)
                .disallowParentInterceptDownEvent(true)
                .setAutoPlay(true)
                .setIndicatorGravity(IndicatorGravity.CENTER)
                .setIndicatorMargin(0, 0, 0, 20.dp)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setIndicatorSliderGap(8.dp)
                .setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                .setIndicatorSliderColor(
                    context.getThemeAttrColor(R.attr.colorAccent),
                    context.getThemeAttrColor(R.attr.colorPrimary)
                )
                .setIndicatorSliderRadius(4.dp)
                .setIndicatorHeight(4.dp)
                .setIndicatorSliderWidth(10.dp, 20.dp)
                .setPageMargin(20.dp)
                .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                .setRevealWidth(0.dp, 100.dp)
                .create()
            item?.let {
                rlBanner.refreshData(it)
            }
        }
    }
}