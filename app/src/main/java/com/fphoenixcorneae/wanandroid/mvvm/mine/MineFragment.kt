package com.fphoenixcorneae.wanandroid.mvvm.mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.common.ext.navigate
import com.fphoenixcorneae.common.ext.toastQQStyle
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentMineBinding
import com.fphoenixcorneae.wanandroid.ext.commonViewModel
import com.fphoenixcorneae.wanandroid.mvvm.login.MustLogin
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
                    MineItemBean(R.drawable.ic_integral, getString(R.string.my_integral)),
                    MineItemBean(R.drawable.ic_collect, getString(R.string.my_collection)),
                    MineItemBean(R.drawable.ic_article, getString(R.string.my_articles)),
                    MineItemBean(R.drawable.ic_todo_list, getString(R.string.todo_list)),
                    MineItemBean(R.drawable.ic_website, getString(R.string.open_source_website)),
                    MineItemBean(R.drawable.ic_join, getString(R.string.join_us)),
                    MineItemBean(R.drawable.ic_change_theme, getString(R.string.change_theme)),
                    MineItemBean(R.drawable.ic_setting, getString(R.string.setting)),
                )
            )
            setOnItemClickListener { adapter, view, position ->
                when (position) {
                    // 我的积分
                    0 -> go2Integral()
                    // 更换主题
                    6 -> appThemeViewModel.darkMode()
                    // 设置
                    7 -> navigate(R.id.fragmentSetting)
                    else -> {}
                }
            }
        }
    }

    override fun FragmentMineBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
        globalViewModel = commonViewModel
        viewModel = mViewModel
        adapter = mAdapter
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentMineBinding.initObserver() {
        commonViewModel.hasLoggedOn.collectWithLifecycle(viewLifecycleOwner) {
            if (it) {
                mViewModel.getUserIntegral()
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        with(mViewModel) {
            userIntegral.collectWithLifecycle(viewLifecycleOwner) {
                it?.let {
                    mAdapter.items[0].value = it.coinCount.toString()
                    mAdapter.notifyItemChanged(0)
                }
            }
        }
    }

    @MustLogin
    private fun go2Integral() {
        toastQQStyle("即将跳转我的积分界面！")
    }
}