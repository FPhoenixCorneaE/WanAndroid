package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseDifferAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.common.ext.toHtml
import com.fphoenixcorneae.flowlayout.FlowItem
import com.fphoenixcorneae.flowlayout.FlowLayout
import com.fphoenixcorneae.wanandroid.databinding.ItemPlazaNavigationBinding

/**
 * @desc：
 * @date：2023/02/17 15:26
 */
class PlazaNavigationAdapter :
    BaseDifferAdapter<NavigationBean, DataBindingHolder<ItemPlazaNavigationBinding>>(NavigationItemCallback()) {

    init {
        setItemAnimation(AnimationType.ScaleIn)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<ItemPlazaNavigationBinding> {
        return DataBindingHolder(ItemPlazaNavigationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<ItemPlazaNavigationBinding>,
        position: Int,
        item: NavigationBean?,
    ) {
        with(holder.binding) {
            navigation = item
            rvNavigationChild.apply {
                mDatas = item?.articles?.map { FlowItem(it.title?.toHtml()) } ?: mutableListOf()
                mOnItemClickListener = object : FlowLayout.OnItemClickListener {
                    override fun onItemClick(item: FlowItem, position: Int, selectedData: List<FlowItem>) {
                    }
                }
            }
            executePendingBindings()
        }
    }
}