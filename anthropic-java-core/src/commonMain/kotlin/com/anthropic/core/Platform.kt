package com.anthropic.core

/** URL-encode a string for use in URL paths and query parameters. */
internal expect fun urlEncode(value: String): String

/** Platform I/O types bridged via typealias on JVM. */
expect abstract class PlatformInputStream
expect abstract class PlatformOutputStream
expect interface PlatformCloseable {
    fun close()
}

/** Platform async type bridged via typealias on JVM to CompletableFuture. */
expect class PlatformFuture<T>

/** Platform time duration bridged via typealias on JVM to java.time.Duration. */
expect class PlatformDuration : Comparable<PlatformDuration>

expect fun durationOfMinutes(minutes: Long): PlatformDuration
expect fun durationOfSeconds(seconds: Long): PlatformDuration
expect fun durationZero(): PlatformDuration
