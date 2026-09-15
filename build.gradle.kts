plugins {
    id("java")
}

group = "com.akage"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.apache.commons:commons-csv:1.14.1")
    implementation("com.microsoft.playwright:playwright:1.62.0")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.akage.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}