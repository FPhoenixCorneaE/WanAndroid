package com.fphoenixcorneae.wanandroid.mvvm.main

import android.graphics.Typeface
import android.view.View
import com.fphoenixcorneae.common.ext.dp
import com.fphoenixcorneae.common.ext.getColor
import com.fphoenixcorneae.common.ext.getRandomColor
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.navigation.NavigationItem
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentMainBinding

/**
 * @desc：主页Fragment
 * @date：2022/05/06 10:10
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun initViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initView() {
        mViewBinding.easyNavigation
            .coloredBackground(coloredBackground = true)
            .iconScale(iconActiveScale = 1.1f, iconInactiveScale = 0.9f)
            .textSize(textActiveSize = 16.dp.toFloat(), textInactiveSize = 14.dp.toFloat())
            .itemColor(getColor(resId = R.color.purple_500), getColor(R.color.purple_200))
            .textFont(font = Typeface.defaultFromStyle(Typeface.ITALIC))
            .setTabs(
                items = listOf(
                    NavigationItem(
                        title = "首页",
                        bgColor = getRandomColor(supportAlpha = true),
                        imgRes = R.mipmap.ic_tab_main_home
                    ),
                    NavigationItem(
                        title = "项目",
                        bgColor = getRandomColor(supportAlpha = true),
                        imgRes = R.mipmap.ic_tab_main_project
                    ),
                    NavigationItem(
                        title = "广场",
                        bgColor = getRandomColor(supportAlpha = true),
                        imgRes = R.mipmap.ic_tab_main_square
                    ),
                    NavigationItem(
                        title = "公众号",
                        bgColor = getRandomColor(supportAlpha = true),
                        imgRes = R.mipmap.ic_tab_main_vipcn
                    ),
                    NavigationItem(
                        title = "我的",
                        bgColor = getRandomColor(supportAlpha = true),
                        imgRes = R.mipmap.ic_tab_main_mine
                    )
                )
            )
            .navigationHeight(navigationHeight = 60.dp)
            .onItemClickListener {

            }
    }
}