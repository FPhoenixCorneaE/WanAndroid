package com.fphoenixcorneae.wanandroid.mvvm

import android.view.View
import androidx.activity.addCallback
import com.fphoenixcorneae.aspectj.AspectjHandler
import com.fphoenixcorneae.common.ext.logd
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.wanandroid.databinding.ActivityMainBinding
import com.fphoenixcorneae.wanandroid.ext.commonViewModel
import com.fphoenixcorneae.wanandroid.mvvm.login.LoginDialog
import com.fphoenixcorneae.wanandroid.mvvm.login.MustLogin
import com.fphoenixcorneae.wanandroid.mvvm.mine.UserManager
import com.fphoenixcorneae.wanandroid.mvvm.splash.SplashDialog
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：首页
 * @date：2022/04/29 17:49
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var mLoginDialog: LoginDialog? = null

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

        AspectjHandler.init { cls, joinPoint ->
            "notifyHandler() called with: clazz = [$cls], joinPoint = [$joinPoint]".logd("AndroidAspectj")
            runCatching {
                when (cls) {
                    MustLogin::class.java -> {
                        if (UserManager.hasLoggedOn()) {
                            joinPoint.proceed()
                        } else {
                            mLoginDialog = LoginDialog()
                            mLoginDialog?.show(this@MainActivity)
                        }
                    }
                    else -> joinPoint.proceed()
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun ActivityMainBinding.initObserver() {
        commonViewModel.hasLoggedOn.collectWithLifecycle(this@MainActivity) {
            if (it) {
                if (mLoginDialog?.dialog?.isShowing == true) {
                    mLoginDialog?.dismissAllowingStateLoss()
                    mLoginDialog = null
                }
            }
        }
    }
}