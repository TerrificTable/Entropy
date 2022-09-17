import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "xyz.terrifictable.entropy"
version = "1.0"

repositories {
    mavenCentral()

    maven {
        url = uri("https://maven.rikonardo.com/releases")
    }
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.google.code.gson:gson:2.9.0")


    implementation("dev.virefire.kson:KSON:1.3.1")
    implementation("dev.virefire.viira:Viira:1.0.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}

