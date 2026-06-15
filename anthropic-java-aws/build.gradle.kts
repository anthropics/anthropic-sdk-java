plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    // Elements of the Amazon AWS SDK for Java 2.0 providing low-level operations such as
    // credentials resolution and AWS SigV4 request signing. The versions of the AWS SDK modules are
    // defined by the AWS SDK BOM.
    //
    // Declared as "api" dependencies to make them transitive and available for use in code (where
    // necessary) that depends on this Anthropic SDK library module. The BOM must be declared as an
    // "api" dependency if any of its modules are declared as "api" dependencies.
    api(platform(libs.awssdk.bom))
    api(libs.awssdk.auth)
    implementation(libs.awssdk.http.client.spi)

    testImplementation(project(":anthropic-java-client-okhttp"))
    testImplementation(kotlin("test"))
    testImplementation(libs.assertj)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.mockito.core)
}
