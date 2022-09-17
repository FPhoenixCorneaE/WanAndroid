package com.fphoenixcorneae.wanandroid.mvvm.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseDifferAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.fphoenixcorneae.wanandroid.databinding.RecyclerItemHomeArticleBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：HomeArticleAdapter
 * @date：2022/05/23 17:47
 */
class HomeArticleAdapter :
    BaseDifferAdapter<ArticleBean, DataBindingHolder<RecyclerItemHomeArticleBinding>>(ArticleItemCallback()) {

    init {
        setItemAnimation(AnimationType.ScaleIn)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): DataBindingHolder<RecyclerItemHomeArticleBinding> {
        return DataBindingHolder(RecyclerItemHomeArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<RecyclerItemHomeArticleBinding>,
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