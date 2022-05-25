package com.fphoenixcorneae.wanandroid.mvvm.home

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.ItemHomeArticleBinding

/**
 * @desc：HomeArticleAdapter
 * @date：2022/05/23 17:47
 */
class HomeArticleAdapter :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_home_article) {

    /**
     * 当 ViewHolder 创建完毕以后，会执行此回掉
     * 可以在这里做任何你想做的事情
     */
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        // 绑定 view
        DataBindingUtil.bind<ItemHomeArticleBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        // 获取 Binding
        DataBindingUtil.getBinding<ItemHomeArticleBinding>(holder.itemView)?.apply {
            // 设置数据
            article = item
            executePendingBindings()
        }
    }
}