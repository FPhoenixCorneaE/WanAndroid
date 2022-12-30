package com.fphoenixcorneae.wanandroid.mvvm.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseDifferAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.wanandroid.databinding.ItemHomeArticleBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：HomeArticleAdapter
 * @date：2022/05/23 17:47
 */
class HomeArticleAdapter :
    BaseDifferAdapter<ArticleBean, DataBindingHolder<ItemHomeArticleBinding>>(ArticleItemCallback()) {

    init {
        setItemAnimation(AnimationType.ScaleIn)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<ItemHomeArticleBinding> {
        return DataBindingHolder(ItemHomeArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<ItemHomeArticleBinding>,
        position: Int,
        item: ArticleBean?,
    ) {
        with(holder.binding) {
            themeViewModel = appThemeViewModel
            // 设置数据
            article = item
            addOnItemChildClickListener(ivCollect.id) { _, _, _ ->

            }
            executePendingBindings()
        }
    }
}