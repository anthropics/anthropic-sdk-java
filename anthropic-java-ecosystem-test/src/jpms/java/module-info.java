// Requires only the umbrella module and one hand-written module, so everything else `JpmsUsageMain`
// touches must arrive through the SDK's own `requires transitive` directives.
module com.anthropic.ecosystem.jpms {
    requires com.anthropic;
    requires com.anthropic.bedrock;
}
