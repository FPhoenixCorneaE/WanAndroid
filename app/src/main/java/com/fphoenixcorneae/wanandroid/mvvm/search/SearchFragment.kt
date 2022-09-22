package com.fphoenixcorneae.wanandroid.mvvm.search

import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.wanandroid.databinding.FragmentSearchBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：搜索Fragment
 * @date：2022/09/21 17:02
 */
class SearchFragment:BaseFragment<FragmentSearchBinding>() {
    override fun FragmentSearchBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }
}