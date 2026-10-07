# Specify the entrypoint where ProGuard starts to determine what's reachable. Nothing else in the package is kept so
# that the classes the test deserializes aren't also instantiated by the other consumers, which build them.
-keep class com.anthropic.ecosystem.EcosystemCompatibilityTest* { *; }

# For the testing framework.
-keep class org.junit.** { *; }

# Many warnings don't apply for our testing purposes.
-dontnote
-dontwarn
