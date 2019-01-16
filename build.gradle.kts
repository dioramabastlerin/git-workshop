import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


group = "de.kapitel26.git"
version = "0.1-SNAPSHOT"


plugins {
    kotlin("jvm") version "1.3.11"
}


allprojects {

    repositories {
        mavenCentral()
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

        testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.10")
        testImplementation(kotlin("reflect"))
    }
}


task("stage") {
    dependsOn("build")
}
