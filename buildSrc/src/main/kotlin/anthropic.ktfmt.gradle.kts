plugins {
    id("anthropic.java")
}

// The module's own sources; the root project covers `buildSrc` (see `Ktfmt.kt`).
configureKtfmt(fileTree("src") { include("**/*.kt") })
