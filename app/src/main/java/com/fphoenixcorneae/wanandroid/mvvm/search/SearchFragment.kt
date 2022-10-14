package com.fphoenixcorneae.wanandroid.mvvm.search

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.fphoenixcorneae.common.ext.*
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.toolbar.CommonToolbar
import com.fphoenixcorneae.wanandroid.R
import com.fphoenixcorneae.wanandroid.databinding.FragmentSearchBinding
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
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
        return (super.initToolbar() as? CommonToolbar)?.apply {
            fillStatusBar = true
            leftType = CommonToolbar.TYPE_LEFT_NONE
            rightType = CommonToolbar.TYPE_RIGHT_TEXT_VIEW
            rightText = getString(R.string.cancel)
            rightTextSize = 16.Sp
            centerType = CommonToolbar.TYPE_CENTER_SEARCH_VIEW
            centerSearchBgRes = R.drawable.shape_bg_toolbar_search
            centerSearchHintText = getString(R.string.search_hint_text)
            centerSearchTextSize = 16.Sp
        }
    }

    @OptIn(FlowPreview::class)
    override fun FragmentSearchBinding.initListener() {
        (mToolbar as? CommonToolbar)?.apply {
            onToolbarClickListener = { v, action, extra ->
                when (action) {
                    CommonToolbar.MotionAction.ACTION_RIGHT_TEXT -> {
                        // 取消
                        onBackPressed()
                    }
                    CommonToolbar.MotionAction.ACTION_SEARCH_SUBMIT -> {
                        // 搜索框输入状态下,键盘提交触发
                    }
                    else -> {}
                }
            }
            callbackFlow {
                val watcher = centerSearchEditText.doAfterTextChanged {
                    trySend(it.toString())
                }
                awaitClose {
                    centerSearchEditText.removeTextChangedListener(watcher)
                }
                // 对流进行降频
            }.debounce(400).collectWithLifecycle(this@SearchFragment) {
                "搜索内容：$it".logi()
            }
        }
    }

    override fun FragmentSearchBinding.initObserver() {
        with(appThemeViewModel) {
            theme.collectWithLifecycle(this@SearchFragment) {
                (mToolbar as? CommonToolbar)?.apply {
                    centerSearchHintTextColor = it.subtitle3
                    centerSearchLeftIconTint = it.surface
                    centerSearchRightDeleteTint = it.surface
                    centerSearchRightVoiceTint = it.surface
                    centerSearchTextColor = it.surface
                    rightTextColor = it.surface
                    statusBarColor = it.primary
                    toolbarColor = it.primary
                }
            }
        }
    }

    override fun onBackPressed() {
        navigateUp()
    }

    /**
     * 默认返回
     */
    private fun defaultBackPressed() {
        // 关闭软键盘
        (mToolbar as? CommonToolbar)?.centerSearchEditText?.hideSoftInput()
        popBackStack()
    }
}