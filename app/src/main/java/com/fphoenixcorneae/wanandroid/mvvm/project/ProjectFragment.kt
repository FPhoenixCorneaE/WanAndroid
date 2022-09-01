package com.fphoenixcorneae.wanandroid.mvvm.project

import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.wanandroid.databinding.FragmentProjectBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：项目Fragment
 * @date：2022/08/30 16:13
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    override fun FragmentProjectBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun FragmentProjectBinding.initView() {
        StatusBarUtil.setSmartPadding(mContext, flMagicIndicator)
    }
}