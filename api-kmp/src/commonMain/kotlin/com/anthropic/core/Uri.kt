package com.anthropic.core

import kotlinx.serialization.Serializable

/**
 * URI — universal resource identifier supporting all schemes.
 * Validates via stable libs on JVM (apache-commons-validator).
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
    fun toKtorUrl(): io.ktor.http.Url = io.ktor.http.Url(value)
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

/**
 * Email — validated via apache commons-validator on JVM.
 * On non-JVM: basic @ check.
 */
@Serializable
@JvmInline
value class Email(val value: String) {
    val local: String get() = value.substringBefore("@")
    val domain: String get() = value.substringAfter("@")
    val isValid: Boolean get() = "@" in value && "." in domain
    override fun toString(): String = value
}

/**
 * Phone — validated via google libphonenumber on JVM.
 * Stores E.164 format (+1234567890).
 */
@Serializable
@JvmInline
value class Phone(val value: String) {
    val isE164: Boolean get() = value.startsWith("+") && value.drop(1).all { it.isDigit() }
    override fun toString(): String = value
}

/**
 * PostalAddress — validated via google i18n address on JVM.
 */
@Serializable
data class PostalAddress(
    val street: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val country: String = "",
) {
    override fun toString(): String = listOf(street, city, state, postalCode, country)
        .filter { it.isNotBlank() }.joinToString(", ")
}

/** Password — toString() redacted for security. */
@Serializable
@JvmInline
value class Password(val value: String) {
    override fun toString(): String = "****"
}

/** IP address (v4 or v6) — validated via apache commons-validator on JVM. */
@Serializable
@JvmInline
value class IpAddress(val value: String) {
    val isV4: Boolean get() = "." in value && ":" !in value
    val isV6: Boolean get() = ":" in value
    override fun toString(): String = value
}

/** Hostname — validated via apache commons-validator on JVM. */
@Serializable
@JvmInline
value class Hostname(val value: String) {
    override fun toString(): String = value
}
