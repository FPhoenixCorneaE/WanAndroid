plugins {
    id(deps.plugins.android.application.get().pluginId)
    id(deps.plugins.kotlin.android.get().pluginId)
    id(deps.plugins.kotlin.parcelize.get().pluginId)
    id(deps.plugins.kotlin.kapt.get().pluginId)
    id(deps.plugins.navigation.safeargs.get().pluginId)
}

android {
    defaultConfig {
        // 每个 Android 模块都有一个命名空间，此命名空间用作其生成的 R 和 BuildConfig 类的 Java 软件包名称。
        // namespace 属性设置的名称应始终与项目的基础软件包名称匹配，建议您让命名空间与应用 ID 保持一致
        applicationId = "com.fphoenixcorneae.wanandroid"
        namespace = applicationId
        compileSdk = deps.versions.compileSdk.get().toIntOrNull()
        buildToolsVersion = deps.versions.buildToolsVersion.get()
        minSdk = deps.versions.minSdk.get().toIntOrNull()
        targetSdk = deps.versions.targetSdk.get().toIntOrNull()
        versionCode = 100
        versionName = "1.0.0"
        multiDexEnabled = true
        // 以Proguard的方式手动加入要放到主Dex中的类，必须设置isMinifyEnabled为true时才会起作用
        multiDexKeepProguard = file("multiDexKeep.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            // 执行proguard混淆
            isMinifyEnabled = false
            // 移除无用的resource文件
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            // 执行proguard混淆
            isMinifyEnabled = true
            // 移除无用的resource文件
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        val main by getting
        main.java.srcDirs("src/main/kotlin")
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    /**
     * The productFlavors block is where you can configure multiple product flavors.
     * This allows you to create different versions of your app that can
     * override the defaultConfig block with their own settings. Product flavors
     * are optional, and the build system does not create them by default.
     *
     * This example creates a free and paid product flavor. Each product flavor
     * then specifies its own application ID, so that they can exist on the Google
     * Play Store, or an Android device, simultaneously.
     *
     * If you declare product flavors, you must also declare flavor dimensions
     * and assign each flavor to a flavor dimension.
     */

    flavorDimensions(*flavorDimensionList.toTypedArray(), project.name)
    productFlavors {
        create("uc") {
            dimension = project.name
            applicationIdSuffix = ".uc"
        }

        create("yyb") {
            dimension = project.name
            applicationIdSuffix = ".yyb"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // FPhoenixCorneaE
    implementation(deps.bundles.fphoenixcorneae)
    // androidx
    implementation(deps.androidx.appcompat)
    implementation(deps.androidx.core.ktx)
    implementation(deps.androidx.core.splashscreen)
    implementation(deps.androidx.constraintlayout)
    implementation(deps.androidx.recyclerview)
    implementation(deps.androidx.cardview)
    implementation(deps.androidx.swiperefreshlayout)
    implementation(deps.material)
    // bugly
    implementation(deps.bugly)
    // leakcanary
    implementation(deps.leakcanary)
    // thirdparty
    implementation(deps.bannerviewpager)
    implementation(deps.magicIndicator)
    implementation(deps.baserecyclerviewadapterhelper)
    // test
    testImplementation(deps.test.junit.junit)
    androidTestImplementation(deps.test.junit.ktx)
    androidTestImplementation(deps.test.espresso.core)
}