import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.util.Locale

plugins {
    `java-library`
    id("com.diffplug.spotless")
}

repositories {
    mavenCentral()
}

configure<JavaPluginExtension> {
    withJavadocJar()
    withSourcesJar()
}

configure<SpotlessExtension> {
    java {
        importOrder()
        removeUnusedImports()
        palantirJavaFormat()
        toggleOffOn()
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Werror")
    options.release.set(8)
}

tasks.named<Jar>("javadocJar") {
    setZip64(true)
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        ))
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}
