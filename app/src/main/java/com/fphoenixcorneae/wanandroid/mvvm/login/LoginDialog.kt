package com.fphoenixcorneae.wanandroid.mvvm.login

import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.wanandroid.databinding.DialogLoginBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel

/**
 * @desc：登录弹窗
 * @date：2023/02/23 17:42
 */
class LoginDialog : BaseDialog<DialogLoginBinding>() {

    override fun DialogLoginBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }
}