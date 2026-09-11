// `open`: Jackson and kotlin-reflect reflect over the models' private members.
open module com.anthropic.client.okhttp {
    requires transitive com.anthropic.core;
    requires transitive kotlin.stdlib;
    requires okhttp3;

    exports com.anthropic.client.okhttp;
}
