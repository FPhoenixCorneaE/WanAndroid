package com.fphoenixcorneae.wanandroid.mvvm.mine

import com.fphoenixcorneae.common.ext.gson.toJson
import com.fphoenixcorneae.common.ext.gson.toObject
import com.fphoenixcorneae.coretrofit.RetrofitFactory
import com.fphoenixcorneae.jetpackmvvm.startup.defaultMMKV
import com.fphoenixcorneae.wanandroid.constant.Constants
import com.fphoenixcorneae.wanandroid.ext.commonViewModel
import com.fphoenixcorneae.wanandroid.mvvm.login.UserInfoBean

/**
 * @desc：用户信息管理
 * @date：2023/02/23 11:01
 */
object UserManager {

    /**
     * 登录状态
     */
    fun hasLoggedOn(): Boolean = defaultMMKV.getBoolean(Constants.User.LOGIN_STATUS, false)

    /**
     * 登录成功
     */
    fun login() = apply {
        defaultMMKV.putBoolean(Constants.User.LOGIN_STATUS, true)
        commonViewModel.setLoginStatus(true)
    }

    /**
     * 用户信息
     */
    fun userInfo(): UserInfoBean? =
        defaultMMKV.getString(Constants.User.USER_INFO, null).toObject(UserInfoBean::class.java)

    /**
     * 保存用户信息
     */
    fun saveUser(userInfoBean: UserInfoBean?) = apply {
        defaultMMKV.putString(Constants.User.USER_INFO, userInfoBean.toJson())
    }

    /**
     * 退出登录
     */
    fun logout(): Boolean = runCatching {
        // 清空Cookies
        RetrofitFactory.clearCookies()
        // 清空用户信息
        saveUser(null)
        defaultMMKV.putBoolean(Constants.User.LOGIN_STATUS, false)
        commonViewModel.setLoginStatus(false)
        true
    }.getOrDefault(false)
}