package com.anthropic.core

/**
 * Multiplatform time abstractions for retry logic.
 * Replaces direct java.time usage in RetryingHttpClient.
 */

/** Get current time in nanoseconds (monotonic). */
internal expect fun currentTimeNanos(): Long

/**
 * Parse a Retry-After header value to a delay in nanoseconds.
 * Handles both seconds (numeric) and HTTP-date (RFC 1123) formats.
 * Returns null if unparseable.
 */
internal expect fun parseRetryAfterToDelayNanos(headerValue: String, nowNanos: Long = currentTimeNanos()): Long?
