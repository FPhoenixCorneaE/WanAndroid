package com.fphoenixcorneae.wanandroid.mvvm.plaza

import androidx.recyclerview.widget.DiffUtil

/**
 * @desc：DiffUtil.ItemCallback：SystemItemCallback
 * @date：2023/02/15 14:13
 */
class SystemItemCallback : DiffUtil.ItemCallback<SystemBean>() {
    /**
     * 判断是否是同一个item
     * @param oldItem New data
     * @param newItem old Data
     */
    override fun areItemsTheSame(oldItem: SystemBean, newItem: SystemBean): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     * @param oldItem New data
     * @param newItem old Data
     */
    override fun areContentsTheSame(oldItem: SystemBean, newItem: SystemBean): Boolean {
        return oldItem.name == newItem.name
                && oldItem.children == newItem.children
                && oldItem.courseId == newItem.courseId
                && oldItem.order == newItem.order
    }

    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    override fun getChangePayload(oldItem: SystemBean, newItem: SystemBean): Any? {
        return null
    }
}