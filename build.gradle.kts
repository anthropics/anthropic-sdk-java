allprojects {
    group = "com.anthropic"
    version = "2.57.1" // x-release-please-version
}

// Modules get these from `anthropic.java`; the root project applies no convention, so register
// them here for the build logic tasks below.
tasks.register("format") {
    group = "Verification"
    description = "Formats all source files."
}
tasks.register("lint") {
    group = "Verification"
    description = "Verifies all source files are formatted."
}

// The module conventions only see each module's `src/`, so the shared build logic in `buildSrc`
// is formatted and linted from here (see `buildSrc/src/main/kotlin/Ktfmt.kt`).
configureKtfmt(fileTree("buildSrc/src") { include("**/*.kt") })
