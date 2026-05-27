package com.anthropic.core;

/**
 * Java fixtures used to verify nullable schema generation for cases that cannot be expressed in
 * Kotlin test sources. In particular, {@code TYPE_USE}-targeted annotations (such as the Checker
 * Framework's {@code @Nullable}) are emitted as runtime-visible type annotations by {@code javac},
 * whereas the Kotlin compiler does not surface them through reflection.
 */
public final class NullableJavaFixtures {
    private NullableJavaFixtures() {}

    /** A field whose type is annotated with the Checker Framework's {@code TYPE_USE} @Nullable. */
    public static final class CheckerTypeUseNullable {
        public @org.checkerframework.checker.nullness.qual.Nullable String s;
    }

    /** A field annotated with the JSR-305 (RUNTIME) declaration-site @Nullable. */
    public static final class Jsr305Nullable {
        @javax.annotation.Nullable
        public String s;
    }

    /** A field annotated with the JetBrains (CLASS retention) @Nullable, which is not detectable. */
    public static final class JetBrainsNullable {
        @org.jetbrains.annotations.Nullable
        public String s;
    }
}
