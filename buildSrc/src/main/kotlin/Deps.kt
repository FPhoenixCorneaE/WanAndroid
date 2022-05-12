object Deps {

    /**
     * FPhoenixCorneaE
     */
    object FPhoenixCorneaE {
        private const val jetpackMvvm = "com.github.FPhoenixCorneaE:JetpackMvvm:1.3.3"
        private const val easyNavigation = "com.github.FPhoenixCorneaE.EasyNavigation:easyNavigation:1.0.8"

        fun dependencies() = listOf(jetpackMvvm, easyNavigation)
    }

    /**
     * AndroidX
     */
    object AndroidX {
        private const val multiDex = "androidx.multidex:multidex:2.0.1"
        private const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        private const val coreKtx = "androidx.core:core-ktx:1.7.0"
        private const val coreSplashScreen = "androidx.core:core-splashscreen:1.0.0-beta02"
        private const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"
        private const val material = "com.google.android.material:material:1.4.0"
        private const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        private const val cardView = "androidx.cardview:cardview:1.0.0"

        fun dependencies() =
            listOf(multiDex, appcompat, coreKtx, coreSplashScreen, constraintLayout, material, recyclerView, cardView)
    }

    /**
     * 腾讯Bugly异常上报
     */
    object Bugly {
        private const val crashReport = "com.tencent.bugly:crashreport:3.4.4"
        private const val nativeCrashReport = "com.tencent.bugly:nativecrashreport:3.9.2"

        fun dependencies() = listOf(crashReport, nativeCrashReport)
    }

    /**
     * 性能优化工具
     */
    object LeakCanary {
        private const val android = "com.squareup.leakcanary:leakcanary-android:2.5"

        fun dependencies() = listOf(android)
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