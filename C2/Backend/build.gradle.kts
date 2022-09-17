import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.7.10"
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

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}




application {
    mainClass.set("xyz.terrifictable.entropy.MainKt")
}

