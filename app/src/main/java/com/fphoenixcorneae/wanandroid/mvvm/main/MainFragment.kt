package com.fphoenixcorneae.wanandroid.mvvm.main

import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.common.ext.*
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.navigation.NavigationItem
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentMainBinding
import com.fphoenixcorneae.wanandroid.ext.eventViewModel
import com.fphoenixcorneae.wanandroid.mvvm.home.HomeFragment
import com.fphoenixcorneae.wanandroid.mvvm.mine.MineFragment
import com.fphoenixcorneae.wanandroid.mvvm.officialaccount.OfficialAccountFragment
import com.fphoenixcorneae.wanandroid.mvvm.plaza.PlazaFragment
import com.fphoenixcorneae.wanandroid.mvvm.project.ProjectFragment
import com.fphoenixcorneae.wanandroid.theme.Theme
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter

/**
 * @desc：主页Fragment
 * @date：2022/05/06 10:10
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()

    override fun FragmentMainBinding.initViewBinding() {
        viewModel = mViewModel
        mainAdapter = FragmentStatePager2ItemAdapter(
            this@MainFragment,
            FragmentPagerItems.with(mContext)
                .add(R.string.homepage, HomeFragment::class.java)
                .add(R.string.project, ProjectFragment::class.java)
                .add(R.string.plaza, PlazaFragment::class.java)
                .add(R.string.official_account, OfficialAccountFragment::class.java)
                .add(R.string.mine, MineFragment::class.java)
                .create(),
            viewLifecycleOwner.lifecycle
        )
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentMainBinding.initView() {
        mViewModel.setNavigationBlurBackground(ColorDrawable(getColor(R.color.color_gray_aaeeeeee)))
        easyNavigation.coloredBackground(coloredBackground = true)
            .iconScale(iconActiveScale = 1.1f, iconInactiveScale = 0.9f)
            .textSize(textActiveSize = 16.Dp, textInactiveSize = 14.Dp)
            .textFont(font = Typeface.defaultFromStyle(Typeface.BOLD))
            .setTabs(
                items = listOf(
                    NavigationItem(
                        title = getString(R.string.homepage),
                        bgColor = getRandomColor().setColorAlpha(.4f),
                        imgRes = R.mipmap.ic_tab_main_home
                    ),
                    NavigationItem(
                        title = getString(R.string.project),
                        bgColor = getRandomColor().setColorAlpha(.4f),
                        imgRes = R.mipmap.ic_tab_main_project
                    ),
                    NavigationItem(
                        title = getString(R.string.plaza),
                        bgColor = getRandomColor().setColorAlpha(.4f),
                        imgRes = R.mipmap.ic_tab_main_plaza
                    ),
                    NavigationItem(
                        title = getString(R.string.official_account),
                        bgColor = getRandomColor().setColorAlpha(.4f),
                        imgRes = R.mipmap.ic_tab_main_official_account
                    ),
                    NavigationItem(
                        title = getString(R.string.mine),
                        bgColor = getRandomColor().setColorAlpha(.4f),
                        imgRes = R.mipmap.ic_tab_main_mine
                    )
                )
            )
            .navigationHeight(navigationHeight = 60.dp)
            .disableShowShadow()
            .onItemClickListener { position ->
                when (position) {
                    0 -> vpMain.setCurrentItem(position, false)
                    1 -> vpMain.setCurrentItem(position, false)
                    2 -> vpMain.setCurrentItem(position, false)
                    3 -> vpMain.setCurrentItem(position, false)
                    4 -> vpMain.setCurrentItem(position, false)
                    else -> appThemeViewModel.switchTheme(Theme.DarkBlue)
                }
            }
    }

    override fun FragmentMainBinding.initObserver() {
        with(appThemeViewModel) {
            theme.collectWithLifecycle(this@MainFragment) {
                easyNavigation.itemColor(
                    itemActiveColor = it.onPrimary,
                    itemInactiveColor = it.onSurface
                ).refreshLayout(100)
            }
        }
        with(eventViewModel) {
            searchIconClicked.collectWithLifecycle(this@MainFragment) {
                "searchIconClicked: $it".logd()
                navigate(R.id.fragmentSearch)
            }
        }
    }
}