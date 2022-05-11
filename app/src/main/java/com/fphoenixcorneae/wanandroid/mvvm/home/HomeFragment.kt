package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Bundle
import android.view.View
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.wanandroid.databinding.FragmentHomeBinding

/**
 * @desc：首页Fragment
 * @date：2022/05/06 10:04
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}