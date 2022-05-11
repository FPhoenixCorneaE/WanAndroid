import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * 添加 FPhoenixCorneaE 依赖库
 */
fun DependencyHandler.addFPhoenixCorneaEDependencies() {
    Deps.FPhoenixCorneaE.dependencies().forEach {
        add("implementation", it)
    }
}

/**
 * 添加 AndroidX 依赖库
 */
fun DependencyHandler.addAndroidXDependencies() {
    Deps.AndroidX.dependencies().forEach {
        add("implementation", it)
    }
}

/**
 * 添加 Bugly 依赖库
 */
fun DependencyHandler.addBuglyDependencies() {
    Deps.Bugly.dependencies().forEach {
        add("implementation", it)
    }
}

/**
 * 添加 LeakCanary 依赖库
 */
fun DependencyHandler.addLeakCanaryDependencies() {
    Deps.LeakCanary.dependencies().forEach {
        add("debugImplementation", it)
    }
}

/**
 * 添加 Test 依赖库
 */
fun DependencyHandler.addTestDependencies() {
    Deps.Test.testDependencies().forEach {
        add("testImplementation", it)
    }
    Deps.Test.androidTestDependencies().forEach {
        add("androidTestImplementation", it)
    }
}
