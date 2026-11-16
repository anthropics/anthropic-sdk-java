plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    // Google Auth Library for Oauth 2.0 authentication. The versions of the
    // library modules are defined by the bill of materials (BOM). Declared as
    // "api" dependencies to make them transitive and available for use in code
    // that depends on this Anthropic SDK library module. The BOM must be
    // declared as an "api" dependency if any of its modules are declared as
    // "api" dependencies.
    api(platform(libs.google.auth.bom))
    api(libs.google.auth.oauth2.http)

    testImplementation(project(":anthropic-java-client-okhttp"))
    testImplementation(kotlin("test"))
    testImplementation(libs.assertj)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
}

tasks.test {
    // `VertexBackend.fromEnv()` reads the process environment, which a test cannot set.
    environment("GOOGLE_APPLICATION_CREDENTIALS", temporaryDir.resolve("service-account.json"))
    environment("CLOUD_ML_REGION", "us-central1")
    environment("ANTHROPIC_VERTEX_PROJECT_ID", "vertex-project-12345-b6")
}
