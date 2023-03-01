package com.fphoenixcorneae.wanandroid.mvvm.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import com.fphoenixcorneae.common.ext.toastQQStyle
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.ext.request
import com.fphoenixcorneae.wanandroid.api.apiService
import com.fphoenixcorneae.wanandroid.mvvm.mine.UserManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

/**
 * @desc：
 * @date：2023/02/28 15:20
 */
class LoginViewModel : BaseViewModel() {
    val account = MutableStateFlow<String?>(null)
    val password = MutableStateFlow<String?>(null)
    val repassword = MutableStateFlow<String?>(null)
    private val _passwordShow = MutableStateFlow(false)
    val passwordShow = _passwordShow.asStateFlow()
    private val _registerSuccess = MutableStateFlow<Boolean?>(null)
    val registerSuccess = _registerSuccess.asStateFlow()

    fun login() {
        request(
            {
                apiService.login(account.firstOrNull().orEmpty(), password.firstOrNull().orEmpty())
            },
            success = {
                UserManager.login().saveUser(it)
            },
            error = {
                toastQQStyle(it.errorMsg)
            }
        )
    }

    fun register() {
        request(
            {
                apiService.register(
                    account.firstOrNull().orEmpty(),
                    password.firstOrNull().orEmpty(),
                    repassword.firstOrNull().orEmpty(),
                )
            },
            success = {
                launch {
                    _registerSuccess.emit(true)
                }
            },
            error = {
                launch {
                    _registerSuccess.emit(false)
                }
                toastQQStyle(it.errorMsg)
            }
        )
    }

    /**
     * 清除账号
     */
    fun clearAccount() {
        launch {
            account.emit(null)
        }
    }

    /**
     * 清除密码
     */
    fun clearPassword() {
        launch {
            password.emit(null)
        }
    }

    /**
     * 切换密码状态
     */
    fun togglePasswordState(editText: EditText) {
        launch {
            _passwordShow.emit(_passwordShow.first().not().also {
                editText.transformationMethod = if (it) {
                    HideReturnsTransformationMethod.getInstance()
                } else {
                    PasswordTransformationMethod.getInstance()
                }
                editText.setSelection(editText.text.toString().length)
            })
        }
    }
}