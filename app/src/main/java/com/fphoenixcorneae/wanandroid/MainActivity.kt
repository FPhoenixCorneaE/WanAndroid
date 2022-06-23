package com.fphoenixcorneae.wanandroid

import android.view.View
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.wanandroid.databinding.ActivityMainBinding
import com.fphoenixcorneae.wanandroid.mvvm.splash.SplashDialog

/**
 * @desc：首页
 * @date：2022/04/29 17:49
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mSplashDialog by lazy {
        SplashDialog()
    }

    override fun ActivityMainBinding.initViewBinding() {
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun ActivityMainBinding.initView() {
        mSplashDialog.show(activity = mContext)
    }
}