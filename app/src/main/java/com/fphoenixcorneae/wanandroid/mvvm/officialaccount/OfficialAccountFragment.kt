package com.fphoenixcorneae.wanandroid.mvvm.officialaccount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.common.ext.toHtml
import com.fphoenixcorneae.common.util.statusbar.StatusBarUtil
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.ext.collectWithLifecycle
import com.fphoenixcorneae.wanandroid.constant.Constants
import com.fphoenixcorneae.wanandroid.databinding.FragmentOfficialAccountBinding
import com.fphoenixcorneae.wanandroid.ext.setNavigator
import com.fphoenixcorneae.wanandroid.theme.appThemeViewModel
import com.fphoenixcorneae.widget.viewpager.FragmentPagerItems
import com.fphoenixcorneae.widget.viewpager.FragmentStatePager2ItemAdapter

/**
 * @desc：公众号Fragment
 * @date：2023/02/20 10:26
 */
class OfficialAccountFragment : BaseFragment<FragmentOfficialAccountBinding>() {

    private val mViewModel by viewModels<OfficialAccountViewModel>()
    private val mFragmentPagerCreator by lazy { FragmentPagerItems.with(mContext) }
    private lateinit var mFragmentStateAdapter: FragmentStatePager2ItemAdapter

    override fun FragmentOfficialAccountBinding.initViewBinding() {
        themeViewModel = appThemeViewModel
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun FragmentOfficialAccountBinding.initView() {
        mFragmentStateAdapter = FragmentStatePager2ItemAdapter(
            this@OfficialAccountFragment,
            mFragmentPagerCreator.create(),
            viewLifecycleOwner.lifecycle
        )
        StatusBarUtil.setSmartPadding(mContext, flMagicIndicator)
    }

    override fun FragmentOfficialAccountBinding.initObserver() {
        with(appThemeViewModel) {
            theme.collectWithLifecycle(this@OfficialAccountFragment) {
                flMagicIndicator.setNavigator(vpOfficialAccount, mFragmentStateAdapter)
            }
        }
        with(mViewModel) {
            officialAccountClassify.collectWithLifecycle(this@OfficialAccountFragment) {
                it?.let {
                    mFragmentPagerCreator.create().clear()
                    it.forEach {
                        mFragmentPagerCreator.add(
                            it.name.toHtml(),
                            OfficialAccountChildFragment::class.java,
                            Bundle().apply {
                                putInt(Constants.Key.CLASSIFY_ID, it.id)
                            })
                    }
                    flMagicIndicator.navigator?.notifyDataSetChanged()
                    vpOfficialAccount.adapter?.notifyItemRangeChanged(0, mFragmentStateAdapter.itemCount)
                    vpOfficialAccount.offscreenPageLimit = mFragmentStateAdapter.itemCount
                }
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.getOfficialAccountClassify()
    }
}