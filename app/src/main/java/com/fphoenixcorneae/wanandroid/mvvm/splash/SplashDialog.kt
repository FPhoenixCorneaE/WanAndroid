package com.fphoenixcorneae.wanandroid.mvvm.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.constant.UrlConstants
import com.fphoenixcorneae.wanandroid.databinding.DialogSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @desc：SplashActivity
 * @date：2022/05/10 17:30
 */
@SuppressLint("CustomSplashScreen")
class SplashDialog : BaseDialog<DialogSplashBinding>() {

    private val mViewModel by viewModels<SplashViewModel>()

    override fun DialogSplashBinding.initViewBinding() {
        viewModel = mViewModel
    }

    override fun DialogSplashBinding.initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(5_000)
            dismiss()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getDailyImage()
    }

    override fun DialogSplashBinding.initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            mViewModel.splashResult.collect {
                var bingImgUrl: String? = null
                it?.images?.getOrNull(0)?.urlbase?.run {
                    bingImgUrl = "${UrlConstants.BING_IMG}${this}_1080x1920.jpg"
                }
                mViewModel.setSplashImg(data = bingImgUrl)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
    }

    override fun getWidth() = WindowManager.LayoutParams.MATCH_PARENT

    override fun getHeight() = WindowManager.LayoutParams.MATCH_PARENT

    override fun getWindowAnimations(): Int = R.style.DialogAnimation_Splash
}