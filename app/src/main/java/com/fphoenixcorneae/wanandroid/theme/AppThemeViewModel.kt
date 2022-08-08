package com.fphoenixcorneae.wanandroid.theme

import androidx.lifecycle.viewModelScope
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @desc：ThemeViewModel
 * @date：2022/07/28 15:11
 */
class AppThemeViewModel : BaseViewModel() {

    private val _theme = MutableStateFlow<Theme>(Theme.LightBlue)
    val theme = _theme.asStateFlow()

    fun switchTheme(theme: Theme) {
        viewModelScope.launch {
            _theme.emit(theme)
        }
    }
}