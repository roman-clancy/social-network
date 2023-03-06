
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:2.7.4")
    implementation("org.postgresql:postgresql:42.3.7")
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.liquibase:liquibase-core:4.9.1")
    implementation("org.springframework.security:spring-security-crypto:5.7.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.4")
    implementation("javax.validation:validation-api:2.0.1.Final")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    testImplementation("org.springframework.security:spring-security-test:5.7.3")
}

group = "ru.rsavin"
version = "0.0.1"
description = "social-network"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
