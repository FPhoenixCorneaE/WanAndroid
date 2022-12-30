package com.fphoenixcorneae.wanandroid.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter(value = ["isRefreshing", "onRefresh"], requireAll = false)
fun configSwipeRefreshLayout(
    swipeRefreshLayout: SwipeRefreshLayout,
    isRefreshing: Boolean?,
    onRefreshListener: SwipeRefreshLayout.OnRefreshListener?,
) {
    swipeRefreshLayout.isRefreshing = isRefreshing == true
    swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
}