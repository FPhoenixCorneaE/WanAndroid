package com.fphoenixcorneae.wanandroid.mvvm.mine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.wanandroid.databinding.ItemMineBinding

/**
 * @desc：
 * @date：2023/02/23 15:46
 */
class MineAdapter : BaseQuickAdapter<MineItemBean, DataBindingHolder<ItemMineBinding>>() {

    init {
        setItemAnimation(AnimationType.AlphaIn)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<ItemMineBinding> {
        return DataBindingHolder(ItemMineBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: DataBindingHolder<ItemMineBinding>, position: Int, item: MineItemBean?) {
        with(holder.binding) {
            data = item
            executePendingBindings()
        }
    }
}