package com.fphoenixcorneae.wanandroid.mvvm.login

import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.wanandroid.databinding.DialogLoginBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：登录弹窗
 * @date：2023/02/23 17:42
 */
class LoginDialog : BaseDialog<DialogLoginBinding>() {

    private val mViewModel by viewModels<LoginViewModel>()

    override fun DialogLoginBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
        viewModel = mViewModel
    }
}