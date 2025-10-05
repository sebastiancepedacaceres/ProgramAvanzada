plugins {
    id("java")
    id("jacoco")
}

group = "ar.edu.unrc.dc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Cucumber dependencies
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.14.0")  // For dependency injection
}

tasks.test {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

jacoco {
    toolVersion = "0.8.11"
}


tasks.jacocoTestReport {
    dependsOn(tasks.test) // Corre los tests antes de generar el reporte
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}