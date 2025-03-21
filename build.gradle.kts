plugins {
    id("java")
}

group = "de.joshuagleitze"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.17")

    runtimeOnly("org.fusesource.jansi:jansi") {
        version {
            prefer("2.4.1")
        }
    }
    runtimeOnly("ch.qos.logback:logback-classic:1.5.18")

    testImplementation(platform("org.junit:junit-bom:5.12.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    if (project.findProperty("jansi.downgrade") != null) {
        constraints {
            testRuntimeOnly("org.fusesource.jansi:jansi:1.18!!")
        }
    }
}

tasks.test {
    useJUnitPlatform()
    if (project.findProperty("jansi.disable") != null) {
        systemProperty("log.jansi.enabled", "false")
    }
}