package kotlinx.kmp.util.core.annotations

/** Marks a resource that must be closed after use. Replaces errorprone @MustBeClosed. */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class MustBeClosed

/** Marks an API as experimental/beta. */
@RequiresOptIn(
    message = "This API is experimental and may change without notice.",
    level = RequiresOptIn.Level.WARNING
)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class ExperimentalAnthropicApi
