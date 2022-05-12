package com.fphoenixcorneae.wanandroid.mvvm.splash


import androidx.annotation.Keep

/**
 * @desc：SplashBean
 * @date：2022/05/11 17:36
 */
@Keep
data class SplashBean(
    val images: List<Image?>? = null,
    val tooltips: Tooltips? = null
) {
    @Keep
    data class Image(
        val bot: Int? = null, // 1
        val copyright: String? = null, // 毕士达喷泉，纽约 (© Mitchell Funk/Getty Images)
        val copyrightlink: String? = null, // http://www.bing.com/search?q=%E6%AF%95%E5%A3%AB%E8%BE%BE%E5%96%B7%E6%B3%89&form=hpcapt&mkt=zh-cn
        val drk: Int? = null, // 1
        val enddate: String? = null, // 20181227
        val fullstartdate: String? = null, // 201812261600
        val hs: List<Any?>? = null,
        val hsh: String? = null, // 77b7992f8d4d4d35d5d3236d26ca4e2e
        val quiz: String? = null, // /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20181226_BethesdaSnow%22&FORM=HPQUIZ
        val startdate: String? = null, // 20181226
        val title: String? = null,
        val top: Int? = null, // 1
        val url: String? = null, // /az/hprichbg/rb/BethesdaSnow_ZH-CN3087618718_1920x1080.jpg
        val urlbase: String? = null, // /az/hprichbg/rb/BethesdaSnow_ZH-CN3087618718
        val wp: Boolean? = null // false
    )

    @Keep
    data class Tooltips(
        val loading: String? = null, // 正在加载...
        val next: String? = null, // 下一个图像
        val previous: String? = null, // 上一个图像
        val walle: String? = null, // 此图片不能下载用作壁纸。
        val walls: String? = null // 下载今日美图。仅限用作桌面壁纸。
    )
}