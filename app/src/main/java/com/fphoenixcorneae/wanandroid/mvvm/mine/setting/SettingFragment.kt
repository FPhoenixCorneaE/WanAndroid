package com.fphoenixcorneae.wanandroid.mvvm.mine.setting

import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.common.ext.navigateUp
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.constant.JmConstants
import com.fphoenixcorneae.toolbar.CommonToolbar
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentSettingBinding
import com.fphoenixcorneae.wanandroid.ext.commonViewModel

/**
 * @desc：
 * @date：2023/03/02 10:59
 */
class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private val mViewModel by viewModels<SettingViewModel>()

    override fun FragmentSettingBinding.initViewBinding() {
        globalViewModel = commonViewModel
        viewModel = mViewModel
    }

    override fun initToolbar(): View? {
        return (super.initToolbar() as? CommonToolbar)?.apply {
            centerText = getString(R.string.setting)
            fillStatusBar = true
            leftType = JmConstants.Toolbar.LEFT_TYPE
            leftImageTint = JmConstants.Toolbar.LEFT_IMAGE_TINT_COLOR
        }
    }

    override fun FragmentSettingBinding.initListener() {
        (mToolbar as? CommonToolbar)?.apply {
            onToolbarClickListener = { v, action, extra ->
                when (action) {
                    CommonToolbar.MotionAction.ACTION_LEFT_BUTTON -> {
                        navigateUp()
                    }
                    else -> {}
                }
            }
        }
    }
}