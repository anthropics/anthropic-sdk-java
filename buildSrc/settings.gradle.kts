// Share the root version catalog with `buildSrc` so plugin and dependency versions live in one
// place.
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
