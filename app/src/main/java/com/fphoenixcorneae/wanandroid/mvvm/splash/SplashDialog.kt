package com.fphoenixcorneae.wanandroid.mvvm.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.common.ext.screenHeight
import com.fphoenixcorneae.common.ext.screenWidth
import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.jetpackmvvm.ext.parseResult
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

    override fun initViewBinding(): DialogSplashBinding {
        return DialogSplashBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mViewBinding.apply {
            viewModel = mViewModel
        }
        viewLifecycleOwner.lifecycleScope.launch {
//            delay(5_000)
//            dismiss()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getDailyImage()
    }

    override fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            mViewModel.splashResult.collect {
                it.parseResult(
                    dialog = this@SplashDialog,
                    onSuccess = {
                        val bingImgUrl = "${UrlConstants.BING_IMG}${it?.images?.getOrNull(0)?.urlbase}_1080x1920.jpg"
                        mViewModel.setSplashImg(data = bingImgUrl)
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
    }

    override fun getWidth() = screenWidth

    override fun getHeight() = screenHeight

    override fun getWindowAnimations(): Int = R.style.DialogAnimation_Splash
}