package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.common.ext.dp
import com.fphoenixcorneae.common.ext.isNotNullOrEmpty
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeArticleBinding
import com.fphoenixcorneae.wanandroid.ext.getThemeAttr
import com.fphoenixcorneae.wanandroid.ext.launchRepeatOnLifecycle
import com.fphoenixcorneae.wanandroid.widget.recyclerview.OnScrollLoadMoreListener
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @desc：首页文章Fragment
 * @date：2022/05/16 17:49
 */
class HomeArticleFragment : BaseFragment<FragmentHomeArticleBinding>() {

    private val mViewModel by viewModels<HomeViewModel>()
    private val mBannerViewPager by lazy {
        BannerViewPager<HomeBannerBean>(mContext).apply {
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 128.dp).apply {
                topMargin = 20.dp
            }
            setLifecycleRegistry(lifecycle)
                .setAdapter(mBannerAdapter)
                .setInterval(3000)
                .setScrollDuration(1500)
                .disallowParentInterceptDownEvent(true)
                .setAutoPlay(true)
                .setIndicatorGravity(IndicatorGravity.CENTER)
                .setIndicatorMargin(0, 0, 0, 20.dp)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setIndicatorSliderGap(8.dp)
                .setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                .setIndicatorSliderColor(getThemeAttr(R.attr.colorAccent), getThemeAttr(R.attr.colorPrimary))
                .setIndicatorSliderRadius(4.dp)
                .setIndicatorHeight(4.dp)
                .setIndicatorSliderWidth(10.dp, 20.dp)
                .setPageMargin(20.dp)
                .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                .setRevealWidth(0.dp, 100.dp)
                .create()
        }
    }
    private val mBannerAdapter by lazy { HomeBannerAdapter() }
    private val mArticleAdapter by lazy {
        HomeArticleAdapter().apply { setDiffCallback(diffCallback = ArticleItemCallback()) }
    }

    override fun FragmentHomeArticleBinding.initViewBinding() {
        articleAdapter = mArticleAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentHomeArticleBinding.initView() {
        srlRefresh.setOnRefreshListener {
            initData(null)
            viewLifecycleOwner.lifecycleScope.launch {
                delay(1_500)
                srlRefresh.isRefreshing = false
            }
        }
        rvArticle.apply {
            setHasFixedSize(true)
            addOnScrollListener(object : OnScrollLoadMoreListener() {
                override fun onLoadMore() {
                    mViewModel.getHomeArticle(isRefresh = false)
                }
            })
        }
    }

    override fun FragmentHomeArticleBinding.initObserver() {
        // 首页Banner
        launchRepeatOnLifecycle {
            mViewModel.homeBanner.collect {
                it.parseResult(
                    fragment = this@HomeArticleFragment,
                    onSuccess = { homeBanners ->
                        mBannerViewPager.refreshData(homeBanners)
                        mArticleAdapter.setHeaderView(mBannerViewPager)
                    })
            }
        }
        // 首页文章列表
        launchRepeatOnLifecycle {
            mViewModel.homeArticle.collect {
                it.parseResult(
                    fragment = this@HomeArticleFragment,
                    onSuccess = { pageBean ->
                        pageBean?.let {
                            if (it.isEmpty()) {
                                showEmpty(null)
                            } else {
                                showContent()
                                if (it.isFirstPage()) {
                                    mArticleAdapter.setDiffNewData(it.datas?.apply {
                                        // 首页置顶文章
                                        mViewModel.homeTopArticle.value?.let {
                                            if (it.isSuccess()) {
                                                it.getResponseData()?.let {
                                                    if (it.isNotNullOrEmpty()) {
                                                        addAll(0, it)
                                                    }
                                                }
                                            }
                                        }
                                    })
                                } else {
                                    it.datas?.let {
                                        val data = mArticleAdapter.data
                                        val oldSize = data.size
                                        data.addAll(it)
                                        mArticleAdapter.setDiffNewData(data)
                                        rvArticle.scrollToPosition(oldSize)
                                    }
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.apply {
            getHomeBanner()
            getHomeTopArticle()
            getHomeArticle(isRefresh = true)
        }
    }
}