plugins {

    /**
     * You should use `apply false` in the top-level build.gradle file
     * to add a Gradle plugin as a build dependency, but not apply it to the
     * current (root) project. You should not use `apply false` in sub-projects.
     * For more information, see
     * Applying external plugins with same version to subprojects.
     */

    id(PluginId.application) version Version.agpVersion apply false
    id(PluginId.library) version Version.agpVersion apply false
    id(PluginId.kotlin) version Version.kotlinVersion apply false
    id(PluginId.navigation) version Version.navigationVersion apply false
}

tasks.create(name = "clean", type = Delete::class) {
    delete = setOf(rootProject.buildDir)
}