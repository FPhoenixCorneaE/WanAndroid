package com.fphoenixcorneae.wanandroid

import android.view.View
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.wanandroid.databinding.ActivityMainBinding
import com.fphoenixcorneae.wanandroid.mvvm.splash.SplashDialog
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：首页
 * @date：2022/04/29 17:49
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mSplashDialog by lazy {
        SplashDialog()
    }

    override fun ActivityMainBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun ActivityMainBinding.initView() {
        mSplashDialog.show(activity = mContext)
    }

    override fun onBackPressed() {
        finish()
    }
}