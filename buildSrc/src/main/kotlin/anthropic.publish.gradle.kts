import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

extra["signingInMemoryKey"] = System.getenv("GPG_SIGNING_KEY")
extra["signingInMemoryKeyId"] = System.getenv("GPG_SIGNING_KEY_ID")
extra["signingInMemoryKeyPassword"] = System.getenv("GPG_SIGNING_PASSWORD")

configure<MavenPublishBaseExtension> {
    signAllPublications()
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    coordinates(project.group.toString(), project.name, project.version.toString())
    configure(
        KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaJavadoc"),
            sourcesJar = true,
        )
    )

    pom {
        name.set("Anthropic API")
        description.set("An SDK library for anthropic")
        url.set("https://docs.anthropic.com/claude/reference/")

        licenses {
            license {
                name.set("MIT")
            }
        }

        developers {
            developer {
                name.set("Anthropic")
                email.set("support@anthropic.com")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/anthropics/anthropic-sdk-java.git")
            developerConnection.set("scm:git:git://github.com/anthropics/anthropic-sdk-java.git")
            url.set("https://github.com/anthropics/anthropic-sdk-java")
        }
    }
}
