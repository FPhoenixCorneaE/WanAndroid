package com.fphoenixcorneae.wanandroid.startup

import android.content.Context
import androidx.startup.Initializer
import com.fphoenixcorneae.common.CommonInitializer
import com.fphoenixcorneae.common.ext.isDebuggable
import com.fphoenixcorneae.common.ext.isMainProcess
import com.fphoenixcorneae.common.ext.logd
import com.fphoenixcorneae.wanandroid.constant.Constants
import com.tencent.bugly.crashreport.CrashReport

/**
 * @desc：初始化Bugly
 * @date：2022/05/06 09:52
 */
class BuglyInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        "Bugly 初始化".logd("startup")
        // 设置是否为上报进程
        val strategy = CrashReport.UserStrategy(context)
        strategy.isUploadProcess = isMainProcess
        CrashReport.initCrashReport(context, Constants.BUGLY_APP_ID, isDebuggable, strategy)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(CommonInitializer::class.java)
    }
}