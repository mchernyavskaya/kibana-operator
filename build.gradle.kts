import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

ktlint {
    version.set("1.4.1")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    // https://mvnrepository.com/artifact/io.javaoperatorsdk/operator-framework-spring-boot-starter
    implementation("io.javaoperatorsdk:operator-framework-spring-boot-starter:5.4.3")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.apache.logging.log4j", module = "log4j-slf4j2-impl")
    }
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
    // https://mvnrepository.com/artifact/io.javaoperatorsdk/operator-framework-spring-boot-starter-test
    testImplementation("io.javaoperatorsdk:operator-framework-spring-boot-starter-test:5.4.3")

    // https://mvnrepository.com/artifact/io.fabric8/crd-generator-apt
    kapt("io.fabric8:crd-generator-apt:6.11.0")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xjsr305=strict"))
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}
