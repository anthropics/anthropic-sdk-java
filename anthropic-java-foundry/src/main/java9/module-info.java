// `open` like the other SDK modules, so reflective tools (e.g. mocking libraries) keep working.
open module com.anthropic.foundry {
    requires transitive com.anthropic.core;
    requires transitive kotlin.stdlib;

    exports com.anthropic.foundry.backends;
}
