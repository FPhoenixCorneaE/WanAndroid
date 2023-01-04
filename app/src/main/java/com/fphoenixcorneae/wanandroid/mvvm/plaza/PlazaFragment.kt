package com.fphoenixcorneae.wanandroid.mvvm.plaza

import android.view.View
import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentPlazaBinding
import com.fphoenixcorneae.wanandroid.ext.setNavigator
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeArticleFragment
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeQaFragment
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter

/**
 * @desc：广场Fragment
 * @date：2023/01/03 14:11
 */
class PlazaFragment : BaseFragment<FragmentPlazaBinding>() {

    override fun FragmentPlazaBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentPlazaBinding.initView() {
        StatusBarUtil.setSmartPadding(mContext, flTopLayout)
    }

    override fun FragmentPlazaBinding.initObserver() {
        with(appThemeViewModel) {
            theme.collectWithLifecycle(this@PlazaFragment) {
                flMagicIndicator.setNavigator(vpPlaza, FragmentStatePager2ItemAdapter(
                    this@PlazaFragment,
                    FragmentPagerItems.with(mContext)
                        .add(R.string.tab_plaza_article, PlazaArticleFragment::class.java)
                        .add(R.string.tab_plaza_ask, HomeQaFragment::class.java)
                        .add(R.string.tab_plaza_system, HomeArticleFragment::class.java)
                        .add(R.string.tab_plaza_navigation, HomeQaFragment::class.java)
                        .create(),
                    viewLifecycleOwner.lifecycle
                ))
            }
        }
    }
}