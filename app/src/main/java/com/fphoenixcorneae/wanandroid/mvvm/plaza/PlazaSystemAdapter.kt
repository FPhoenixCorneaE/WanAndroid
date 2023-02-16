package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseDifferAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.common.ext.toHtml
import com.fphoenixcorneae.flowlayout.FlowItem
import com.fphoenixcorneae.flowlayout.FlowLayout
import com.fphoenixcorneae.wanandroid.databinding.ItemPlazaSystemBinding

/**
 * @desc：
 * @date：2023/02/15 14:09
 */
class PlazaSystemAdapter :
    BaseDifferAdapter<SystemBean, DataBindingHolder<ItemPlazaSystemBinding>>(SystemItemCallback()) {

    init {
        setItemAnimation(AnimationType.ScaleIn)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<ItemPlazaSystemBinding> {
        return DataBindingHolder(ItemPlazaSystemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<ItemPlazaSystemBinding>,
        position: Int,
        item: SystemBean?,
    ) {
        with(holder.binding) {
            system = item
            rvSystemChild.apply {
                mDatas = item?.children?.map { FlowItem(it.name.toHtml()) } ?: mutableListOf()
                mOnItemClickListener = object : FlowLayout.OnItemClickListener {
                    override fun onItemClick(item: FlowItem, position: Int, selectedData: List<FlowItem>) {
                    }
                }
            }
            executePendingBindings()
        }
    }
}