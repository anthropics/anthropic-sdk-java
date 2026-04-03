package com.anthropic.core

/** URL-encode a string for use in URL paths and query parameters. */
internal expect fun urlEncode(value: String): String

/** Platform I/O types bridged via typealias on JVM. */
expect abstract class PlatformInputStream
expect abstract class PlatformOutputStream
expect interface PlatformCloseable {
    fun close()
}
