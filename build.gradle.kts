import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.task"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val aspectJVersion = "1.9.20.1"
val allureVersion = "2.24.0"
// Define configuration for AspectJ agent
val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.seleniumhq.selenium:selenium-java:4.17.0")
    // https://mvnrepository.com/artifact/io.cucumber/cucumber-junit-platform-engine
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.15.0")
//    // https://mvnrepository.com/artifact/io.cucumber/cucumber-core
//    implementation("io.cucumber:cucumber-core:7.15.0")
// https://mvnrepository.com/artifact/org.junit.platform/junit-platform-suite-engine
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.10.1")


    // https://mvnrepository.com/artifact/io.cucumber/cucumber-java
    implementation("io.cucumber:cucumber-java:7.15.0")
    implementation("org.slf4j:slf4j-api:2.0.11")
    // https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
    implementation("io.github.bonigarcia:webdrivermanager:5.6.3")
    // https://mvnrepository.com/artifact/org.testcontainers/selenium
    implementation("org.testcontainers:selenium:1.19.3")
    // https://mvnrepository.com/artifact/org.reflections/reflections
    implementation("org.reflections:reflections:0.10.2")
    // https://mvnrepository.com/artifact/io.qameta.allure/allure-java-commons
    implementation("io.qameta.allure:allure-java-commons:2.25.0")
    // Import allure-bom to ensure correct versions of all the dependencies are used
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    // Add necessary Allure dependencies to dependencies section
    testImplementation("io.qameta.allure:allure-junit5:2.21.0")
    // https://mvnrepository.com/artifact/ru.yandex.qatools.ashot/ashot
    implementation("ru.yandex.qatools.ashot:ashot:1.5.4")
    // https://mvnrepository.com/artifact/io.cucumber/cucumber-picocontainer
    implementation("io.cucumber:cucumber-picocontainer:7.15.0")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    testImplementation("org.slf4j:slf4j-simple:2.0.11")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")


    implementation(kotlin("reflect"))
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
}

// Define the version of AspectJ

tasks.test {
    dependsOn ("cleanTest")
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}

val cucumberRuntime: Configuration by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}