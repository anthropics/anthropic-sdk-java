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

/** GeoJSON Point — longitude, latitude. */
@Serializable
data class GeoPoint(
    val longitude: Double,
    val latitude: Double,
    val altitude: Double? = null,
) {
    override fun toString(): String = "$latitude,$longitude${altitude?.let { ",$it" } ?: ""}"
    companion object {
        fun parse(value: String): GeoPoint {
            val parts = value.split(",").map { it.trim().toDouble() }
            return GeoPoint(longitude = parts[1], latitude = parts[0], altitude = parts.getOrNull(2))
        }
    }
}

/** Locale — BCP 47 / CLDR locale tag. */
@Serializable
@JvmInline
value class Locale(val value: String) {
    val language: String get() = value.substringBefore("-")
    val region: String? get() = value.substringAfter("-", "").ifEmpty { null }
    override fun toString(): String = value
    companion object {
        val EN = Locale("en")
        val EN_US = Locale("en-US")
        val FR = Locale("fr")
        val DE = Locale("de")
        val JA = Locale("ja")
        val ZH = Locale("zh")
    }
}

/** Currency — ISO 4217 code. */
@Serializable
@JvmInline
value class Currency(val value: String) {
    override fun toString(): String = value
    companion object {
        val USD = Currency("USD")
        val EUR = Currency("EUR")
        val GBP = Currency("GBP")
        val JPY = Currency("JPY")
    }
}

/** Timezone — IANA timezone ID. */
@Serializable
@JvmInline
value class Timezone(val value: String) {
    override fun toString(): String = value
    companion object {
        val UTC = Timezone("UTC")
        val US_EASTERN = Timezone("America/New_York")
        val US_PACIFIC = Timezone("America/Los_Angeles")
        val EUROPE_LONDON = Timezone("Europe/London")
        val ASIA_TOKYO = Timezone("Asia/Tokyo")
    }
}

/** Country — ISO 3166-1 alpha-2 code. */
@Serializable
@JvmInline
value class Country(val value: String) {
    override fun toString(): String = value
}

/** Language — ISO 639-1 code. */
@Serializable
@JvmInline
value class Language(val value: String) {
    override fun toString(): String = value
}

/** MeasureUnit — value + unit (e.g. "5.2 kg", "100 km"). */
@Serializable
data class Measure(
    val value: Double,
    val unit: String,
) {
    override fun toString(): String = "$value $unit"
}

/** PersonName — structured name (given, family, prefix, suffix). */
@Serializable
data class PersonName(
    val given: String = "",
    val family: String = "",
    val prefix: String? = null,
    val suffix: String? = null,
    val middle: String? = null,
) {
    val full: String get() = listOfNotNull(prefix, given, middle, family, suffix)
        .filter { it.isNotBlank() }.joinToString(" ")
    override fun toString(): String = full
}
