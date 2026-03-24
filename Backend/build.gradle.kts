import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    kotlin("kapt") version "2.2.21"
    id("org.springframework.boot") version "4.0.4"
    id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
    group = "com"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }

    tasks.withType<BootRun> {
        javaLauncher.set(
            javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
        )
    }

    dependencies {
        val kotestVersion = "5.9.1"

        // Spring, JPA, kotlin
        add("implementation", "org.springframework.boot:spring-boot-starter-web")
        add("implementation", "org.springframework.boot:spring-boot-starter")
        add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
        add("implementation", "org.springframework.boot:spring-boot-starter-validation")
        add("implementation", "org.springframework.boot:spring-boot-starter-data-jpa")
        add("implementation", "com.fasterxml.jackson.module:jackson-module-kotlin")
        add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
        add("testImplementation", "org.jetbrains.kotlin:kotlin-test-junit5")
        add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")

        // kotest
        add("testImplementation", "io.kotest:kotest-runner-junit5:$kotestVersion")
        add("testImplementation", "io.kotest:kotest-assertions-core:$kotestVersion")
        add("testImplementation", "io.kotest:kotest-property:$kotestVersion")

        // mockk
        add("testImplementation", "io.mockk:mockk:1.13.11")
        add("testImplementation", "com.ninja-squad:springmockk:4.0.2")

        // RestAssured
        add("testImplementation", "io.rest-assured:rest-assured:5.5.0")

        // h2
        add("runtimeOnly", "com.h2database:h2")

    }
}
