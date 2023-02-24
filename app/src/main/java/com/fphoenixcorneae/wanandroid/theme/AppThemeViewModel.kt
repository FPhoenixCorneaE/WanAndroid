package com.fphoenixcorneae.wanandroid.theme

import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.launch
import com.fphoenixcorneae.jetpackmvvm.startup.defaultMMKV
import com.fphoenixcorneae.wanandroid.constant.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull

/**
 * @desc：ThemeViewModel
 * @date：2022/07/28 15:11
 */
class AppThemeViewModel : BaseViewModel() {

    private val _theme = MutableStateFlow(getThemePreference())
    val theme = _theme.asStateFlow()

    fun switchTheme(theme: Theme) {
        launch {
            if (_theme.firstOrNull() != theme) {
                _theme.emit(theme)
                setThemePreference(theme)
            }
        }
    }

    fun darkMode() {
        launch {
            switchTheme(if (_theme.firstOrNull() == Theme.LightBlue) Theme.DarkBlue else Theme.LightBlue)
        }
    }

    fun getThemePreference() = run {
        when (defaultMMKV.getString(Constants.Preference.THEME, null)) {
            Theme.DarkBlue.javaClass.simpleName -> Theme.DarkBlue
            else -> Theme.LightBlue
        }
    }

    fun setThemePreference(theme: Theme) = defaultMMKV.putString(Constants.Preference.THEME, theme.javaClass.simpleName)
}