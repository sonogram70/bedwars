import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
plugins {
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    `maven-publish`
}

group = "com.github.devil0414.bedwars"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://jitpack.io/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("com.github.noonmaru:tap:3.2.5")
    implementation("com.github.noonmaru:kommand:0.6.3")
    compileOnly("com.github.noonmaru:invfx:1.3.0")
}
tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<ShadowJar> {
        archiveClassifier.set("")
    }
    create<Copy>("distJar") {
        from(shadowJar)
        into("C:\\Users\\yongh\\Desktop\\server\\BedWars\\plugins")
    }
    create<Jar>("sourcesJar") {
        from(sourceSets["main"].allSource)
        archiveClassifier.set("sources")
    }
}
publishing {
    publications {
        create<MavenPublication>("Bed-Wars") {
            artifactId = project.name
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}
