// `open`: Jackson and kotlin-reflect reflect over the models' private members.
open module com.anthropic {
    requires transitive com.anthropic.client.okhttp;
    requires transitive kotlin.stdlib;
}
