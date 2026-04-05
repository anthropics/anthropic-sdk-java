package kotlinx.kmp.util.core

/**
 * Multiplatform time abstractions for retry logic.
 */

/** Get current wall-clock time in milliseconds since epoch. */
expect fun currentTimeMillis(): Long

/**
 * Parse a Retry-After header value to a delay in nanoseconds.
 * Handles both seconds (numeric) and HTTP-date (RFC 1123) formats.
 * [nowMillis] is the current wall-clock time for computing date-based delays.
 * Returns null if unparseable.
 */
expect fun parseRetryAfterToDelayNanos(headerValue: String, nowMillis: Long = currentTimeMillis()): Long?
