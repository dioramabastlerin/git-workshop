plugins {
    kotlin("jvm") version "1.4.10"
    id("application")
}

allprojects {

    repositories {
        mavenCentral()
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/jetbrains/markdown")
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "application")

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    dependencies {
        compile(kotlin("script-runtime"))
        implementation(kotlin("stdlib"))
    }
    
}

