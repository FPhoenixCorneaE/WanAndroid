package com.fphoenixcorneae.wanandroid.mvvm.home

import androidx.recyclerview.widget.DiffUtil

/**
 * @desc：DiffUtil.ItemCallback：ArticleItemCallback
 * @date：2022/05/24 11:00
 */
class ArticleItemCallback : DiffUtil.ItemCallback<ArticleBean>() {
    /**
     * 判断是否是同一个item
     * @param oldItem New data
     * @param newItem old Data
     */
    override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     * @param oldItem New data
     * @param newItem old Data
     */
    override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
        return oldItem.link == newItem.link
                && oldItem.author == newItem.author
                && oldItem.shareUser == newItem.shareUser
                && oldItem.title == newItem.title
    }

    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    override fun getChangePayload(oldItem: ArticleBean, newItem: ArticleBean): Any? {
        return null
    }
}