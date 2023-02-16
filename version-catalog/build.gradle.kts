plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    `version-catalog`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

// 版本目录配置
catalog {
    /**
     * version - 用于声明可以被依赖项引用的版本
     * plugin  - 用于声明插件
     * library - 用于声明坐标的别名
     * bundle  - 用于声明依赖包
     */
    versionCatalog {
        version("android", "7.2.2")
        version("kotlin", "1.7.10")
        version("compileSdk", "33")
        version("buildToolsVersion", "33.0.0")
        version("minSdk", "21")
        version("targetSdk", "33")

        // plugin
        plugin("android-application", "com.android.application").versionRef("android")
        plugin("android-library", "com.android.library").versionRef("android")
        plugin("kotlin-android", "org.jetbrains.kotlin.android").versionRef("kotlin")
        plugin("kotlin-kapt", "org.jetbrains.kotlin.kapt").versionRef("kotlin")
        plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
        plugin("kotlin-parcelize", "org.jetbrains.kotlin.plugin.parcelize").versionRef("kotlin")

        // FPhoenixCorneaE
        library("fphoenixcorneae-jetpackMvvm", "com.github.FPhoenixCorneaE:JetpackMvvm:3.0.1")
        library("fphoenixcorneae-easyNavigation", "com.github.FPhoenixCorneaE.EasyNavigation:easyNavigation:1.1.0")
        library("fphoenixcorneae-viewPagerAdapter", "com.github.FPhoenixCorneaE:ViewPagerAdapter:1.0.1-beta01")
        library("fphoenixcorneae-flowLayout", "com.github.FPhoenixCorneaE:FlowLayout:1.0.8")
        bundle("fphoenixcorneae", listOf(
            "fphoenixcorneae-jetpackMvvm",
            "fphoenixcorneae-easyNavigation",
            "fphoenixcorneae-viewPagerAdapter",
            "fphoenixcorneae-flowLayout",
        ))

        // androidx
        library("androidx-activity-ktx", "androidx.activity", "activity-ktx").version("1.6.1")
        library("androidx-annotation", "androidx.annotation", "annotation").version("1.5.0")
        library("androidx-appcompat", "androidx.appcompat", "appcompat").version("1.5.1")
        library("androidx-cardview", "androidx.cardview", "cardview").version("1.0.0")
        library("androidx-constraintlayout", "androidx.constraintlayout", "constraintlayout").version("2.1.4")

        library("androidx-core-ktx", "androidx.core", "core-ktx").version("1.9.0")
        library("androidx-core-splashscreen", "androidx.core", "core-splashscreen").version("1.0.0")

        version("androidx-datastore", "1.0.0")
        library("androidx-datastore", "androidx.datastore", "datastore").versionRef("androidx-datastore")
        library("androidx-datastore-preferences", "androidx.datastore", "datastore-preferences")
            .versionRef("androidx-datastore")
        bundle("androidx-datastore", listOf(
            "androidx-datastore",
            "androidx-datastore-preferences"
        ))

        version("androidx-fragment", "1.5.4")
        library("androidx-fragment-ktx", "androidx.fragment", "fragment-ktx").versionRef("androidx-fragment")
        library("androidx-fragment-testing", "androidx.fragment", "fragment-testing").versionRef("androidx-fragment")

        library("androidx-multidex", "androidx.multidex", "multidex").version("2.0.1")

        library("androidx-paging-runtime", "androidx.paging", "paging-runtime").version("3.1.1")
        library("androidx-paging-compose", "androidx.paging", "paging-compose").version("1.0.0-alpha17")

        library("androidx-recyclerview", "androidx.recyclerview", "recyclerview").version("1.2.1")
        library("androidx-recyclerview-selection", "androidx.recyclerview", "recyclerview-selection").version("1.1.0")

        library("androidx-startup-runtime", "androidx.startup", "startup-runtime").version("1.1.1")
        library("androidx-swiperefreshlayout", "androidx.swiperefreshlayout", "swiperefreshlayout").version("1.1.0")
        library("androidx-viewpager2", "androidx.viewpager2", "viewpager2").version("1.0.0")
        library("androidx-legacy", "androidx.legacy", "legacy-support-v4").version("1.0.0")
        library("androidx-savedstate-ktx", "androidx.savedstate", "savedstate-ktx").version("1.2.0")

        version("androidx-work", "2.7.1")
        library("androidx-work-runtime-ktx", "androidx.work", "work-runtime-ktx").versionRef("androidx-work")
        library("androidx-work-multiprocess", "androidx.work", "work-multiprocess").versionRef("androidx-work")
        library("androidx-work-testing", "androidx.work", "work-testing").versionRef("androidx-work")
        bundle("androidx-work", listOf(
            "androidx-work-runtime-ktx",
            "androidx-work-multiprocess"
        ))

        // material
        library("material", "com.google.android.material", "material").version("1.5.0")

        // lifecycle
        version("lifecycle", "2.5.1")
        library("lifecycle-viewmodel-ktx", "androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycle")
        library("lifecycle-viewmodel-compose",
            "androidx.lifecycle",
            "lifecycle-viewmodel-compose").versionRef("lifecycle")
        library("lifecycle-livedata-ktx", "androidx.lifecycle", "lifecycle-livedata-ktx").versionRef("lifecycle")
        library("lifecycle-runtime-ktx", "androidx.lifecycle", "lifecycle-runtime-ktx").versionRef("lifecycle")
        library("lifecycle-runtime-testing", "androidx.lifecycle", "lifecycle-runtime-testing").versionRef("lifecycle")
        library("lifecycle-viewmodel-savedstate", "androidx.lifecycle", "lifecycle-viewmodel-savedstate")
            .versionRef("lifecycle")
        library("lifecycle-common-java8", "androidx.lifecycle", "lifecycle-common-java8").versionRef("lifecycle")
        library("lifecycle-service", "androidx.lifecycle", "lifecycle-service").versionRef("lifecycle")
        library("lifecycle-process", "androidx.lifecycle", "lifecycle-process").versionRef("lifecycle")
        library("lifecycle-reactivestreams-ktx", "androidx.lifecycle", "lifecycle-reactivestreams-ktx")
            .versionRef("lifecycle")
        bundle("lifecycle", listOf(
            "lifecycle-viewmodel-ktx",
            "lifecycle-viewmodel-compose",
            "lifecycle-livedata-ktx",
            "lifecycle-runtime-ktx",
            "lifecycle-viewmodel-savedstate",
            "lifecycle-common-java8",
            "lifecycle-service",
            "lifecycle-process",
            "lifecycle-reactivestreams-ktx",
        ))

        // navigation
        version("navigation", "2.5.3")
        plugin("navigation-safeargs", "androidx.navigation.safeargs.kotlin").versionRef("navigation")
        library("navigation-fragment-ktx", "androidx.navigation", "navigation-fragment-ktx")
            .versionRef("navigation")
        library("navigation-ui-ktx", "androidx.navigation", "navigation-ui-ktx")
            .versionRef("navigation")
        library("navigation-compose", "androidx.navigation", "navigation-compose")
            .versionRef("navigation")
        library("navigation-testing", "androidx.navigation", "navigation-testing")
            .versionRef("navigation")
        bundle("navigation", listOf(
            "navigation-fragment-ktx",
            "navigation-ui-ktx",
            "navigation-compose"
        ))

        // coroutines
        version("coroutines", "1.6.4")
        library("kotlinx-coroutines-core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core")
            .versionRef("coroutines")
        library("kotlinx-coroutines-android", "org.jetbrains.kotlinx", "kotlinx-coroutines-android")
            .versionRef("coroutines")
        bundle("coroutines", listOf(
            "kotlinx-coroutines-core",
            "kotlinx-coroutines-android"
        ))

        // room
        version("room", "2.4.3")
        library("room-runtime", "androidx.room", "room-runtime").versionRef("room")
        library("room-ktx", "androidx.room", "room-ktx").versionRef("room")
        library("room-compiler", "androidx.room", "room-compiler").versionRef("room")
        library("room-paging", "androidx.room", "room-paging").versionRef("room")
        library("room-testing", "androidx.room", "room-testing").versionRef("room")
        bundle("room", listOf(
            "room-runtime",
            "room-ktx",
            "room-paging"
        ))

        // coil(https://coil-kt.github.io/coil/getting_started/)
        version("coil", "2.2.2")
        library("coil", "io.coil-kt", "coil").versionRef("coil")
        library("coil-gif", "io.coil-kt", "coil-gif").versionRef("coil")
        library("coil-svg", "io.coil-kt", "coil-svg").versionRef("coil")
        library("coil-video", "io.coil-kt", "coil-video").versionRef("coil")
        library("coil-compose", "io.coil-kt", "coil-compose").versionRef("coil")
        bundle("coil", listOf(
            "coil",
            "coil-gif",
            "coil-svg",
            "coil-video",
            "coil-compose"
        ))

        // dokit(https://github.com/didi/DoraemonKit)
        version("dokit", "3.5.0")
        library("dokit", "io.github.didi.dokit", "dokit").versionRef("dokit")
        library("dokit-ft", "io.github.didi.dokit", "dokitx-ft").versionRef("dokit")
        library("dokit-mc", "io.github.didi.dokit", "dokitx-mc").versionRef("dokit")
        library("dokit-weex", "io.github.didi.dokit", "dokitx-weex").versionRef("dokit")
        library("dokit-no-op", "io.github.didi.dokit", "dokitx-no-op").versionRef("dokit")
        bundle("dokit", listOf(
            "dokit",
            "dokit-ft",
            "dokit-mc",
            "dokit-weex",
            "dokit-no-op"
        ))

        // bugly
        library("bugly", "com.tencent.bugly:crashreport:4.1.9")
        // leakcanary
        library("leakcanary", "com.squareup.leakcanary:leakcanary-android:2.9.1")
        // magicIndicator
        library("magicIndicator", "com.github.hackware1993:MagicIndicator:1.7.0")
        // androidautosize
        library("androidautosize", "com.github.JessYanCoding:AndroidAutoSize:v1.2.1")
        // easyfloat
        library("easyfloat", "com.github.princekin-f:EasyFloat:2.0.4")
        // powerspinner
        library("powerspinner", "com.github.skydoves:powerspinner:1.2.3")
        // photoview
        library("photoview", "com.github.chrisbanes:PhotoView:2.3.0")
        // bannerviewpager
        library("bannerviewpager", "com.github.zhpanvip:bannerviewpager:3.5.7")
        // baserecyclerviewadapterhelper
        library("baserecyclerviewadapterhelper", "io.github.cymchad:BaseRecyclerViewAdapterHelper:4.0.0-beta02")

        // test
        library("test-core-ktx", "androidx.test:core-ktx:1.4.0")
        library("test-junit-junit", "junit:junit:4.13.2")
        library("test-junit-ktx", "androidx.test.ext:junit-ktx:1.1.3")
        library("test-espresso-core", "androidx.test.espresso:espresso-core:3.4.0")
    }
}