object Deps {

    /**
     * FPhoenixCorneaE
     */
    object FPhoenixCorneaE {
        private const val jetpackMvvm = "com.github.FPhoenixCorneaE:JetpackMvvm:3.0.1"
        private const val easyNavigation = "com.github.FPhoenixCorneaE.EasyNavigation:easyNavigation:1.1.0"
        private const val viewPagerAdapter = "com.github.FPhoenixCorneaE:ViewPagerAdapter:1.0.1-beta01"

        fun dependencies() = listOf(jetpackMvvm, easyNavigation, viewPagerAdapter)
    }

    /**
     * AndroidX
     */
    object AndroidX {
        private const val appcompat = "androidx.appcompat:appcompat:1.5.1"
        private const val coreKtx = "androidx.core:core-ktx:1.9.0"
        private const val coreSplashScreen = "androidx.core:core-splashscreen:1.0.0-beta02"
        private const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        private const val material = "com.google.android.material:material:1.5.0"
        private const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        private const val cardView = "androidx.cardview:cardview:1.0.0"
        private const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        fun dependencies() =
            listOf(
                appcompat,
                coreKtx,
                coreSplashScreen,
                constraintLayout,
                material,
                recyclerView,
                cardView,
                swipeRefreshLayout
            )
    }

    /**
     * 腾讯Bugly异常上报
     */
    object Bugly {
        private const val crashReport = "com.tencent.bugly:crashreport:4.1.9"

        fun dependencies() = listOf(crashReport)
    }

    /**
     * 性能优化工具
     */
    object LeakCanary {
        private const val android = "com.squareup.leakcanary:leakcanary-android:2.9.1"

        fun dependencies() = listOf(android)
    }

    /**
     * 第三方库
     */
    object ThirdParty {
        /** https://github.com/zhpanvip/BannerViewPager */
        private const val bannerViewPager = "com.github.zhpanvip:bannerviewpager:3.5.5"

        /** https://github.com/hackware1993/MagicIndicator */
        private const val magicIndicator = "com.github.hackware1993:MagicIndicator:1.7.0"

        /** https://github.com/CymChad/BaseRecyclerViewAdapterHelper */
        private const val baseRecyclerViewAdapterHelper = "io.github.cymchad:BaseRecyclerViewAdapterHelper:4.0.0-beta01"

        fun dependencies() = listOf(bannerViewPager, magicIndicator, baseRecyclerViewAdapterHelper)
    }

    /**
     * Test
     */
    object Test {
        private const val junit = "junit:junit:4.13.2"
        private const val extJunit = "androidx.test.ext:junit:1.1.2"
        private const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"

        fun testDependencies() = listOf(junit)
        fun androidTestDependencies() = listOf(extJunit, espressoCore)
    }
}