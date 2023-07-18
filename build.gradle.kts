import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
    id("maven-publish")
}

group = "at.deckweiss"
version = "2.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://git.deckweiss.at/api/v4/projects/87/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "Job-Token"
                value = System.getenv("CI_JOB_TOKEN")
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
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
    kotlinOptions.jvmTarget = "11"
}