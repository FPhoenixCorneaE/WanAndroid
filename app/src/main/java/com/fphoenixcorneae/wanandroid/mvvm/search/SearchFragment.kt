package com.fphoenixcorneae.wanandroid.mvvm.search

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.fphoenixcorneae.common.ext.hideSoftInput
import com.fphoenixcorneae.common.ext.popBackStack
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.toolbar.CommonToolbar
import com.fphoenixcorneae.wanandroid.databinding.FragmentSearchBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

/**
 * @desc：搜索Fragment
 * @date：2022/09/21 17:02
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun FragmentSearchBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return super.initToolbar().apply {

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun FragmentSearchBinding.initListener() {
        rlToolbar.onToolbarClickListener = { v, action, extra ->
            when (action) {
                CommonToolbar.MotionAction.ACTION_SEARCH_SUBMIT -> {
                    // 搜索框输入状态下,键盘提交触发
                }
                else -> {}
            }
        }
        callbackFlow {
            val watcher = rlToolbar.centerSearchEditText.doAfterTextChanged {
                trySend(it.toString())
            }

            invokeOnClose {
                rlToolbar.centerSearchEditText.removeTextChangedListener(watcher)
            }
            // 对流进行降频
        }.debounce(200).collectWithLifecycle(this@SearchFragment) {

        }
    }

    override fun onBackPressed() {

    }

    /**
     * 默认返回
     */
    private fun defaultBackPressed() {
        // 关闭软键盘
        mViewBinding.rlToolbar.centerSearchEditText.hideSoftInput()
        popBackStack()
    }
}