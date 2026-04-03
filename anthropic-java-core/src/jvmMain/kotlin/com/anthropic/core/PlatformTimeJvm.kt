package com.anthropic.core

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

internal actual fun currentTimeNanos(): Long = System.nanoTime()

internal actual fun parseRetryAfterToDelayNanos(headerValue: String, nowNanos: Long): Long? {
    // Try as seconds (numeric)
    headerValue.trim().toDoubleOrNull()?.let { seconds ->
        return (seconds * 1_000_000_000).toLong()
    }
    // Try as HTTP-date (RFC 1123)
    return try {
        val retryAt = OffsetDateTime.parse(headerValue.trim(), DateTimeFormatter.RFC_1123_DATE_TIME)
        val delayNanos = ChronoUnit.NANOS.between(OffsetDateTime.now(), retryAt)
        if (delayNanos > 0) delayNanos else 0L
    } catch (_: DateTimeParseException) {
        null
    }
}
