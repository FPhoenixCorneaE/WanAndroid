plugins {
    id(PluginId.application)
    id(PluginId.kotlin)
    id(PluginId.kotlinParcelize)
    id(PluginId.kotlinKapt)
    id(PluginId.navigation)
}

android {
    defaultConfig {
        // 每个 Android 模块都有一个命名空间，此命名空间用作其生成的 R 和 BuildConfig 类的 Java 软件包名称。
        // namespace 属性设置的名称应始终与项目的基础软件包名称匹配，建议您让命名空间与应用 ID 保持一致
        namespace = DefaultConfig.applicationId
        applicationId = DefaultConfig.applicationId
        compileSdk = DefaultConfig.compileSdk
        buildToolsVersion = DefaultConfig.buildToolsVersion
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk
        versionCode = DefaultConfig.versionCode
        versionName = DefaultConfig.versionName
        multiDexEnabled = true
        // 以Proguard的方式手动加入要放到主Dex中的类，必须设置isMinifyEnabled为true时才会起作用
        multiDexKeepProguard = file("multiDexKeep.pro")
        testInstrumentationRunner = DefaultConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildType.release) {
            // 执行proguard混淆
            isMinifyEnabled = false
            // 移除无用的resource文件
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile(BuildType.proguardAndroidOptimize), BuildType.proguardRules)
        }
        getByName(BuildType.debug) {
            // 执行proguard混淆
            isMinifyEnabled = true
            // 移除无用的resource文件
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile(BuildType.proguardAndroidOptimize), BuildType.proguardRules)
        }
    }

    sourceSets {
        val main by getting
        main.java.srcDirs(SourceSet.mainDirs)
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    lint {
        isCheckReleaseBuilds = false
        isAbortOnError = false
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
        create(ProductFlavors.uc) {
            dimension = project.name
            applicationId = ProductFlavors.ApplicationId.uc
        }

        create(ProductFlavors.yyb) {
            dimension = project.name
            applicationId = ProductFlavors.ApplicationId.yyb
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // FPhoenixCorneaE
    addFPhoenixCorneaEDependencies()
    // androidx
    addAndroidXDependencies()
    // bugly
    addBuglyDependencies()
    // leakcanary
    addLeakCanaryDependencies()
    // thirdparty
    addThirdPartyDependencies()
    // test
    addTestDependencies()
}