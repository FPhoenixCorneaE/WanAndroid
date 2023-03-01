package com.fphoenixcorneae.wanandroid.mvvm.mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentMineBinding
import com.fphoenixcorneae.wanandroid.mvvm.login.LoginDialog
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：我的
 * @date：2023/02/20 11:35
 */
class MineFragment : BaseFragment<FragmentMineBinding>() {

    private val mViewModel by viewModels<MineViewModel>()
    private val mAdapter by lazy {
        MineAdapter().apply {
            addAll(
                listOf(
                    MineItemBean(R.drawable.ic_integral, "我的积分", "8888"),
                    MineItemBean(R.drawable.ic_collect, "我的收藏"),
                    MineItemBean(R.drawable.ic_article, "我的文章"),
                    MineItemBean(R.drawable.ic_todo_list, "待办清单"),
                    MineItemBean(R.drawable.ic_website, "开源网站"),
                    MineItemBean(R.drawable.ic_join, "加入我们"),
                    MineItemBean(R.drawable.ic_change_theme, "更换主题"),
                    MineItemBean(R.drawable.ic_setting, "设置"),
                )
            )
            setOnItemClickListener { adapter, view, position ->
                when (position) {
                    0 -> LoginDialog().show(this@MineFragment)
                    6 -> appThemeViewModel.darkMode()
                    else -> {}
                }
            }
        }
    }

    override fun FragmentMineBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
        viewModel = mViewModel
        adapter = mAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getUserIntegral()
    }
}