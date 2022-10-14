package com.fphoenixcorneae.wanandroid.theme

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @desc：ThemeViewModel
 * @date：2022/07/28 15:11
 */
class AppThemeViewModel : BaseViewModel() {

    private val _theme = MutableStateFlow<Theme>(Theme.LightBlue)
    val theme = _theme.asStateFlow()

    fun switchTheme(theme: Theme) {
        launch {
            _theme.emit(theme)
        }
    }
}