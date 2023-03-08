package com.fphoenixcorneae.wanandroid.mvvm.mine.setting

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.requestNoCheck
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.mine.UserManager

/**
 * @desc：
 * @date：2023/03/02 13:41
 */
class SettingViewModel : BaseViewModel() {

    fun logout() {
        requestNoCheck(
            block = {
                apiService.logout()
            },
            success = {
                UserManager.logout()
            },
        )
    }
}