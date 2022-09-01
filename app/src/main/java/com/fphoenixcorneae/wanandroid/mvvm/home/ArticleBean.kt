package com.fphoenixcorneae.wanandroid.mvvm.home

import android.os.Parcelable
import androidx.annotation.Keep
import com.fphoenixcorneae.common.ext.toHtml
import com.fphoenixcorneae.wanandroid.R
import kotlinx.parcelize.Parcelize

/**
 * @desc：文章数据
 * @date：2022/05/23 15:48
 */
@Keep
@Parcelize
data class ArticleBean(
    var apkLink: String?,
    var author: String?,
    var chapterId: Int,
    var chapterName: String?,
    var collect: Boolean,
    var courseId: Int,
    var desc: String?,
    var envelopePic: String?,
    var fresh: Boolean,
    var id: Int,
    var link: String?,
    var niceDate: String?,
    var origin: String?,
    var prefix: String?,
    var projectLink: String?,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String?,
    var shareUser: String?,
    var tags: List<TagsBean?>?,
    var title: String?,
    var type: Int?,
    var userId: Int?,
    var visible: Int?,
    var zan: Int?,
) : Parcelable {

    /**
     * 文章的标签
     */
    @Keep
    @Parcelize
    data class TagsBean(
        var name: String?,
        var url: String?,
    ) : Parcelable

    fun getIcon(link: String?) = link?.run {
        when {
            contains("wanandroid.com") -> R.mipmap.ic_logo_android
            contains("www.jianshu.com") -> R.mipmap.ic_logo_jianshu
            contains("juejin.im") -> R.mipmap.ic_logo_juejin
            contains("blog.csdn.net") -> R.mipmap.ic_logo_csdn
            contains("weixin.qq.com") -> R.mipmap.ic_logo_wechat
            else -> R.mipmap.ic_logo_other
        }
    }

    fun titleToHtml() = title?.toHtml()

    fun descToHtml() = desc?.toHtml()
}