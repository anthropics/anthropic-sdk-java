package kotlinx.kmp.util.core

/**
 * Multiplatform time abstractions for retry logic.
 * Replaces direct java.time usage in RetryingHttpClient.
 */

/** Get current time in nanoseconds (monotonic). */
expect fun currentTimeNanos(): Long

/**
 * Parse a Retry-After header value to a delay in nanoseconds.
 * Handles both seconds (numeric) and HTTP-date (RFC 1123) formats.
 * Returns null if unparseable.
 */
expect fun parseRetryAfterToDelayNanos(headerValue: String, nowNanos: Long = currentTimeNanos()): Long?
