plugins {

    /**
     * You should use `apply false` in the top-level build.gradle file
     * to add a Gradle plugin as a build dependency, but not apply it to the
     * current (root) project. You should not use `apply false` in sub-projects.
     * For more information, see
     * Applying external plugins with same version to subprojects.
     */

    alias(deps.plugins.android.application) apply false
    alias(deps.plugins.android.library) apply false
    alias(deps.plugins.kotlin.android) apply false
    alias(deps.plugins.kotlin.jvm) apply false
    alias(deps.plugins.navigation.safeargs) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        val aspectjtools = deps.plugins.aspectj.tools.get()
        classpath("${aspectjtools.pluginId}:${aspectjtools.version}")
        val aspectjplugin = deps.plugins.fphoenixcorneae.aspectj.plugin.get()
        classpath("${aspectjplugin.pluginId}:${aspectjplugin.version}")
    }
}

tasks.create(name = "clean", type = Delete::class) {
    delete = setOf(rootProject.buildDir)
}