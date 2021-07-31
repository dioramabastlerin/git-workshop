
dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.10")
    implementation("org.jetbrains:markdown:0.1.33")

    testImplementation(kotlin("reflect"))
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.10")
    testImplementation("org.slf4j:slf4j-simple:1.7.25")
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