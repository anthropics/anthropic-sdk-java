// `open` like the other SDK modules, so reflective tools (e.g. mocking libraries) keep working.
open module com.anthropic.vertex {
    requires transitive com.anthropic.core;
    requires transitive com.google.auth.oauth2;
    requires transitive kotlin.stdlib;

    exports com.anthropic.vertex.backends;
}
