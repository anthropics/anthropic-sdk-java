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
    api(platform("com.google.auth:google-auth-library-bom:1.32.1"))
    api("com.google.auth:google-auth-library-oauth2-http")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
}
