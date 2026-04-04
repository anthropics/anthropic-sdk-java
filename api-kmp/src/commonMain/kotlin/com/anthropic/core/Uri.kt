package com.anthropic.core

import kotlinx.serialization.Serializable

/**
 * URI — universal resource identifier supporting all schemes.
 *
 * Handles: http://, https://, file://, content://, ws://, wss://, data:, mailto:, etc.
 * Wraps String but provides scheme/path/query parsing.
 * Used directly by Wire models (serializes as string on the wire).
 */
@Serializable
@JvmInline
value class Uri(val value: String) {
    val scheme: String get() = value.substringBefore("://", "")
    val host: String get() = value.substringAfter("://").substringBefore("/").substringBefore(":")
    val port: Int? get() = value.substringAfter("://").substringBefore("/").substringAfter(":", "").toIntOrNull()
    val path: String get() = value.substringAfter("://").substringAfter("/", "").substringBefore("?")
    val query: String? get() = if ("?" in value) value.substringAfter("?") else null
    val fragment: String? get() = if ("#" in value) value.substringAfter("#") else null

    val isHttp: Boolean get() = scheme in listOf("http", "https")
    val isFile: Boolean get() = scheme == "file"
    val isWebSocket: Boolean get() = scheme in listOf("ws", "wss")

    /** Convert to ktor Url (HTTP only) */
    fun toKtorUrl(): io.ktor.http.Url = io.ktor.http.Url(value)

    /** Convert to okio Path (file only) */
    fun toFilePath(): String = if (isFile) path else error("Not a file URI: $value")

    override fun toString(): String = value

    companion object {
        fun http(host: String, path: String = "", port: Int = 443, https: Boolean = true): Uri =
            Uri("${if (https) "https" else "http"}://$host${if (port != 443 && port != 80) ":$port" else ""}/$path")

        fun file(path: String): Uri = Uri("file://$path")
        fun ws(host: String, path: String = "", secure: Boolean = true): Uri =
            Uri("${if (secure) "wss" else "ws"}://$host/$path")
    }
}

/** Email address — validated format. */
@Serializable
@JvmInline
value class Email(val value: String) {
    val local: String get() = value.substringBefore("@")
    val domain: String get() = value.substringAfter("@")
    override fun toString(): String = value
}

/** Password — toString() redacted for security. */
@Serializable
@JvmInline
value class Password(val value: String) {
    override fun toString(): String = "****"
}

/** IP address (v4 or v6). */
@Serializable
@JvmInline
value class IpAddress(val value: String) {
    val isV4: Boolean get() = "." in value && ":" !in value
    val isV6: Boolean get() = ":" in value
    override fun toString(): String = value
}

/** Hostname. */
@Serializable
@JvmInline
value class Hostname(val value: String) {
    override fun toString(): String = value
}
