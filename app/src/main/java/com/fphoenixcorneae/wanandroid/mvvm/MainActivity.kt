package com.fphoenixcorneae.wanandroid.mvvm

import android.view.View
import androidx.activity.addCallback
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.wanandroid.databinding.ActivityMainBinding
import com.fphoenixcorneae.wanandroid.mvvm.splash.SplashDialog
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：首页
 * @date：2022/04/29 17:49
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun ActivityMainBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun ActivityMainBinding.initView() {
        SplashDialog().show(activity = mContext)

        onBackPressedDispatcher.addCallback(this@MainActivity) {
            finish()
        }
    }
}