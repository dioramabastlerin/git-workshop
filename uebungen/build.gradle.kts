val ktorVersion: String by rootProject

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")    

//    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.10")
    implementation("org.jetbrains:markdown:0.1.33")
    testImplementation(kotlin("reflect"))
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.10")
    testImplementation("org.slf4j:slf4j-simple:1.7.25")
}

