plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    // Elements of the Amazon AWS SDK for Java 2.0. These dependencies provide
    // low-level operations such as credentials resolution and AWS SigV4 request
    // signing.
    implementation(platform("software.amazon.awssdk:bom:2.30.11"))
    implementation("software.amazon.awssdk:auth")
    implementation("software.amazon.awssdk:http-client-spi")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
}