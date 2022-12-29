package com.fphoenixcorneae.wanandroid.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter(value = ["isRefreshing"], requireAll = false)
fun configSwipeRefreshLayout(
    swipeRefreshLayout: SwipeRefreshLayout,
    isRefreshing: Boolean?,
) {
    isRefreshing?.let {
        swipeRefreshLayout.isRefreshing = it
    }
}