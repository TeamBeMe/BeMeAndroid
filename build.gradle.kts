// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply(plugin = "org.jlleitschuh.gradle.ktlint")

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlintVersion}")
        classpath(ClassPathPlugins.googleService)
        classpath(ClassPathPlugins.hilt)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> { debug.set(true) }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
