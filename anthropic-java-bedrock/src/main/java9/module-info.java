// `open` like the other SDK modules, so reflective tools (e.g. mocking libraries) keep working.
open module com.anthropic.bedrock {
    requires transitive com.anthropic.aws;
    requires transitive com.anthropic.core;
    requires transitive kotlin.stdlib;
    requires transitive software.amazon.awssdk.auth;
    requires software.amazon.awssdk.http;
    requires software.amazon.awssdk.http.auth.aws;
    requires software.amazon.awssdk.http.auth.spi;
    requires software.amazon.awssdk.identity.spi;
    requires transitive software.amazon.awssdk.regions;
    requires software.amazon.eventstream;

    exports com.anthropic.bedrock.backends;
}
