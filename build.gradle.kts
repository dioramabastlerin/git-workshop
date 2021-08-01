plugins {
    kotlin("jvm") version "1.4.10"
    id("application")
}

apply(plugin = "application")


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
        compile(kotlin("script-runtime"))
        implementation(kotlin("stdlib"))
    }
    
}

dependencies {
    implementation(project(":uebungen"))
}


application {
    mainClassName = "de.kapitel26.gitsamplebuilder.gitworkshop.GitworkshopsamplesKt"
}

task("distUebungenMarkdown", JavaExec::class) {
    group = "Distribution"
    description = "Deploy markdown files for exercises, to website dir."
    dependsOn("run")

    main = "de.kapitel26.gitsamplebuilder.BuildAndDeployUebungenToWebsiteKt"
    classpath = sourceSets["main"].runtimeClasspath
}

task("distUebungenZip", JavaExec::class) {
    group = "Distribution"/* \ */
    description = "Create a new zip file."
    dependsOn("run")

    main = "de.kapitel26.gitsamplebuilder.BuildAndDeployUebungenToZipKt"
    classpath = sourceSets["main"].runtimeClasspath
}

task("sandbox", JavaExec::class) {
    group = "Application"
    description = "Run sandbox samples from gitworkshopsandbox.kt."
    main = "de.kapitel26.gitsamplebuilder.gitworkshop.GitworkshopsandboxKt"
    classpath = sourceSets["main"].runtimeClasspath
}

