plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    implementation(libs.okhttp)

    testImplementation(kotlin("test"))
    testImplementation(libs.assertj)
    testImplementation(libs.wiremock)
}
