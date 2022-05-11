package com.fphoenixcorneae.wanandroid.mvvm.splash

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.common.ext.screenHeight
import com.fphoenixcorneae.common.ext.screenWidth
import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.DialogSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @desc：SplashActivity
 * @date：2022/05/10 17:30
 */
@SuppressLint("CustomSplashScreen")
class SplashDialog : BaseDialog<DialogSplashBinding>() {
    override fun initViewBinding(): DialogSplashBinding {
        return DialogSplashBinding.inflate(layoutInflater)
    }

    override fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2_000)
            dismiss()
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