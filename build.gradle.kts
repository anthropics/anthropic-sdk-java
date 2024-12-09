plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

allprojects {
    group = "com.anthropic"
    version = "0.0.1-alpha.0" // x-release-please-version
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}
