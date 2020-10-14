import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


group = "de.kapitel26.git"
version = "0.1-SNAPSHOT"


plugins {
    kotlin("jvm") version "1.3.72"
}


allprojects {

    repositories {
        jcenter()
        maven("http://dl.bintray.com/jetbrains/markdown")
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")


    tasks.withType<Test> {
        useJUnitPlatform()
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }


    dependencies {
        api(kotlin("stdlib-jdk8"))


        implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.10")
        implementation("org.jetbrains:markdown:0.1.33")

        testImplementation(kotlin("reflect"))
        testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.10")
        testImplementation("org.slf4j:slf4j-simple:1.7.25")
    }
}


task("stage") {
    dependsOn("build")
}
dependencies {
    compile(kotlin("script-runtime"))
    implementation(kotlin("stdlib"))
}
repositories {
    mavenCentral()
}