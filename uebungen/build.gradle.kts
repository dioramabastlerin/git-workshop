
group = "de.kapitel26.git"
version = "0.1-SNAPSHOT"

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

    tasks.withType<Test> {
        useJUnitPlatform()
    }


    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }


    dependencies {

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

application {
    mainClassName = "de.kapitel26.gitsamplebuilder.gitworkshop.GitworkshopsamplesKt"
}

task("sandbox", JavaExec::class) {
    group = "Application"
    description = "Run sandbox samples from gitworkshopsandbox.kt."
    main = "de.kapitel26.gitsamplebuilder.gitworkshop.GitworkshopsandboxKt"
    classpath = sourceSets["main"].runtimeClasspath
}

task("distUebungenToSite", JavaExec::class) {
    group = "Distribution"
    description = "Deploy markdown files for exercises, to website dir."
    main = "de.kapitel26.gitsamplebuilder.BuildAndDeployUebungenToWebsiteKt"
    classpath = sourceSets["main"].runtimeClasspath
}