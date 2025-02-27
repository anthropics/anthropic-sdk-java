plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    // Elements of the Amazon AWS SDK for Java 2.0 providing low-level
    // operations such as credentials resolution and AWS SigV4 request signing.
    // The versions of the AWS SDK modules are defined by the AWS SDK BOM.
    //
    // Declared as "api" dependencies to make them transitive and available for
    // use in code (where necessary) that depends on this Anthropic SDK library
    // module. The BOM must be declared as an "api" dependency if any of its
    // modules are declared as "api" dependencies.
    api(platform("software.amazon.awssdk:bom:2.30.11"))
    api("software.amazon.awssdk:auth")
    implementation("software.amazon.awssdk:http-client-spi")

    // Amazon AWS EventStream for parsing Bedrock streaming responses.
    implementation("software.amazon.eventstream:eventstream:1.0.1")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
}
