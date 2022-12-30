package com.fphoenixcorneae.wanandroid.mvvm.project

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.common.ext.getRandomColor
import com.fphoenixcorneae.wanandroid.databinding.ItemProjectChildBinding
import com.fphoenixcorneae.wanandroid.mvvm.home.ArticleBean

/**
 * @desc：
 * @date：2022/08/30 17:37
 */
class ProjectChildAdapter : BaseQuickAdapter<ArticleBean, DataBindingHolder<ItemProjectChildBinding>>() {

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<ItemProjectChildBinding> {
        return DataBindingHolder(ItemProjectChildBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<ItemProjectChildBinding>,
        position: Int,
        item: ArticleBean?,
    ) {
        with(holder.binding) {
            article = item
            envelopePlaceholder = GradientDrawable().apply {
                setColor(getRandomColor())
                cornerRadius = 4f
            }
            executePendingBindings()
        }
    }
}