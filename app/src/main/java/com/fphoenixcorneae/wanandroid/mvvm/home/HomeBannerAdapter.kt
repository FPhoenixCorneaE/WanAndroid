package com.fphoenixcorneae.wanandroid.mvvm.home

import androidx.databinding.DataBindingUtil
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.ItemHomeBannerBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * @desc：首页Banner适配器
 * @date：2022/05/19 10:48
 */
class HomeBannerAdapter : BaseBannerAdapter<HomeBannerBean>() {

    override fun bindData(
        holder: BaseViewHolder<HomeBannerBean>?,
        data: HomeBannerBean?,
        position: Int,
        pageSize: Int
    ) {
        holder?.let {
            val dataBinding: ItemHomeBannerBinding? = DataBindingUtil.bind(it.itemView)
            dataBinding?.imgData = data?.imagePath
        }
    }

    override fun getLayoutId(viewType: Int) = R.layout.item_home_banner
}