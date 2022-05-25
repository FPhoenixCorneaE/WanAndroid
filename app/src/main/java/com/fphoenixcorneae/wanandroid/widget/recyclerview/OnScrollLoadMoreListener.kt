package com.fphoenixcorneae.wanandroid.widget.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @desc：向上滑动加载更多监听
 * @date：2022/05/24 11:22
 */
abstract class OnScrollLoadMoreListener : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        when (val layoutManager = recyclerView.layoutManager) {
            is LinearLayoutManager -> {
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的itemPosition
                    val lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                    val itemCount = layoutManager.itemCount

                    // 判断是否最后一项完全显示
                    if (lastCompletelyVisibleItemPosition == itemCount - 1) {
                        // 加载更多
                        onLoadMore()
                    }
                }
            }
        }
    }

    /**
     * 加载更多
     */
    abstract fun onLoadMore()
}