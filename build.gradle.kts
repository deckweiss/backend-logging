import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val packageVersion = "2.0.0"

plugins {
    id("java")
    kotlin("jvm") version "2.0.0"
    id("com.vanniktech.maven.publish") version "0.28.0"
}

group = "at.deckweiss"
version = packageVersion
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.slf4j:jul-to-slf4j:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.8")

    testImplementation("org.mockito:mockito-junit-jupiter:5.4.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
}

mavenPublishing {
    coordinates(
        groupId = "at.deckweiss",
        artifactId = "logging",
        version = packageVersion
    )

    pom {
        name.set("Deckweiss Logging")
        description.set("Library for enhancing SLF4J logging by explicit parameters in the message")
        url.set("https://git.deckweiss.at/chapters/backend/packages/logging")
        inceptionYear.set("2022")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("klausbetz")
                name.set("Klaus Betz")
                url.set("https://www.deckweiss.at")
            }
        }

        scm {
            url.set("https://git.deckweiss.at/chapters/backend/packages/logging.git")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}
