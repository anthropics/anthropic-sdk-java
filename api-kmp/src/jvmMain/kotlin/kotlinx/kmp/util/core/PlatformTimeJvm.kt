package kotlinx.kmp.util.core

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

actual fun currentTimeMillis(): Long = System.currentTimeMillis()

actual fun parseRetryAfterToDelayNanos(headerValue: String, nowMillis: Long): Long? {
    // Try as seconds (numeric)
    headerValue.trim().toDoubleOrNull()?.let { seconds ->
        return (seconds * 1_000_000_000).toLong()
    }
    // Try as HTTP-date (RFC 1123)
    return try {
        val retryAt = OffsetDateTime.parse(headerValue.trim(), DateTimeFormatter.RFC_1123_DATE_TIME)
        val nowInstant = Instant.ofEpochMilli(nowMillis).atOffset(ZoneOffset.UTC)
        val delayMillis = retryAt.toInstant().toEpochMilli() - nowInstant.toInstant().toEpochMilli()
        if (delayMillis > 0) delayMillis * 1_000_000L else 0L
    } catch (_: DateTimeParseException) {
        null
    }
}
