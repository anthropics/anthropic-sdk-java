import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SourcesJar
import org.gradle.plugins.signing.SigningExtension

plugins {
    // Publishing needs the Dokka javadoc JAR (see `JavadocJar.Dokka` below), so every publishable
    // module gets Dokka configured here.
    id("anthropic.dokka")
    id("com.vanniktech.maven.publish")
}

publishing {
  repositories {
      if (project.hasProperty("publishLocal")) {
          maven {
              name = "LocalFileSystem"
              url = uri("${rootProject.layout.buildDirectory.get()}/local-maven-repo")
          }
      }
  }
}

// JPMS module names for the published jars, kept in one place so the namespace stays visible and
// collision-free. Each name is the root package of the module carrying it, so a future
// `module-info.java` can declare the very same name; `anthropic-java-core` therefore takes
// `com.anthropic` outright, since it owns every package under it. `anthropic-java` ships no classes
// of its own — it only re-exports `anthropic-java-core` and `anthropic-java-client-okhttp` — so it
// has no root package to name it after and sits beside them instead.
val automaticModuleNames = mapOf(
    "anthropic-java" to "com.anthropic.all",
    "anthropic-java-aws" to "com.anthropic.aws",
    "anthropic-java-bedrock" to "com.anthropic.bedrock",
    "anthropic-java-client-okhttp" to "com.anthropic.client.okhttp",
    "anthropic-java-core" to "com.anthropic",
    "anthropic-java-foundry" to "com.anthropic.foundry",
    "anthropic-java-google-cloud" to "com.anthropic.googlecloud",
    "anthropic-java-mcp" to "com.anthropic.mcp",
    "anthropic-java-vertex" to "com.anthropic.vertex",
)

// Without this attribute a consumer on the module path gets a module name derived from the jar's
// file name, which is neither stable nor ours to choose. A published module missing from the map
// above fails the build rather than falling back to a derived name: the name is a compatibility
// promise that can't be changed after a release, so it has to be picked deliberately, once, before
// the module's first publish.
plugins.withType<JavaPlugin> {
    val automaticModuleName =
        automaticModuleNames[project.name]
            ?: error(
                "No Automatic-Module-Name is declared for published module '${project.name}'. " +
                    "Add one to `automaticModuleNames` in `anthropic.publish.gradle.kts`."
            )

    tasks.named<Jar>("jar") {
        manifest {
            attributes(mapOf("Automatic-Module-Name" to automaticModuleName))
        }
    }
}

val gpgSigningKey: Provider<String> = providers.environmentVariable("GPG_SIGNING_KEY")
val gpgSigningKeyId: Provider<String> = providers.environmentVariable("GPG_SIGNING_KEY_ID")
val gpgSigningPassword: Provider<String> = providers.environmentVariable("GPG_SIGNING_PASSWORD")

configure<MavenPublishBaseExtension> {
    if (!project.hasProperty("publishLocal")) {
        signAllPublications()
        publishToMavenCentral()
    }

    coordinates(project.group.toString(), project.name, project.version.toString())
    configure(
        KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaGeneratePublicationJavadoc"),
            sourcesJar = SourcesJar.Sources(),
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

// `signAllPublications()` only auto-configures an in-memory signatory from the `signingInMemoryKey`
// Gradle property; CI passes these via `GPG_SIGNING_*` env vars instead, so wire them in explicitly.
plugins.withType<SigningPlugin> {
    if (gpgSigningKey.isPresent) {
        configure<SigningExtension> {
            useInMemoryPgpKeys(gpgSigningKeyId.orNull, gpgSigningKey.get(), gpgSigningPassword.getOrElse(""))
        }
    }
}

tasks.withType<Zip>().configureEach {
    isZip64 = true
}
