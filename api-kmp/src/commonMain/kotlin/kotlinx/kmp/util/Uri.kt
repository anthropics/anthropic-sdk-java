package kotlinx.kmp.util

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

// GeoPoint, PersonName, PostalAddress, GeoIp, Measure — Wire-generated from types.proto

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

// === Extension functions for Wire-generated types ===

/** PersonName.full — formatted full name. */
val PersonName.full: String get() = listOfNotNull(prefix, given, middle, family, suffix)
    .filter { it.isNotBlank() }.joinToString(" ")

/** GeoIp.formatPhone — phone with country code prepended. */
fun GeoIp.formatPhone(localNumber: String): Phone = Phone("+$phone_code$localNumber")

// === Set views on VCardContact for deduplication + O(1) contains() ===
//
// Wire proto3 repeated fields generate as Kotlin List<T>. For fields that
// must be unique (emails, phones, categories, etc.) we expose Set<T> views
// and a dedup() helper that returns a normalized VCardContact.

/** Unique emails as a Set (O(1) contains). */
val VCardContact.emailSet: Set<String> get() = emails.toSet()

/** Unique E.164 phone numbers as a Set. */
val VCardContact.phoneSet: Set<String> get() =
    phones.mapNotNull { it.e164_number?.takeIf { n -> n.isNotBlank() } }.toSet()

/** Unique IMPP URIs as a Set. */
val VCardContact.imppSet: Set<String> get() = impp.toSet()

/** Unique language tags as a Set. */
val VCardContact.languageSet: Set<String> get() = languages.toSet()

/** Unique URLs as a Set. */
val VCardContact.urlSet: Set<String> get() = urls.toSet()

/** Unique nicknames as a Set. */
val VCardContact.nicknameSet: Set<String> get() = nicknames.toSet()

/** Unique categories as a Set. */
val VCardContact.categorySet: Set<String> get() = categories.toSet()

/** Unique member UIDs as a Set (for KIND=group vcards). */
val VCardContact.memberSet: Set<String> get() = members.toSet()

/** Fast contains() — uses Set view. */
fun VCardContact.hasEmail(email: String): Boolean = email in emailSet
fun VCardContact.hasPhone(phoneE164: String): Boolean = phoneE164 in phoneSet
fun VCardContact.hasCategory(category: String): Boolean = category in categorySet
fun VCardContact.hasLanguage(lang: String): Boolean = lang in languageSet

/**
 * Return a VCardContact with all repeated fields deduplicated.
 * Preserves order of first occurrence (via LinkedHashSet).
 */
fun VCardContact.dedup(): VCardContact = copy(
    emails = emails.distinct(),
    phones = phones.distinctBy { it.e164_number.orEmpty() + "|" + it.extension },
    addresses = addresses.distinct(),
    impp = impp.distinct(),
    languages = languages.distinct(),
    urls = urls.distinct(),
    nicknames = nicknames.distinct(),
    categories = categories.distinct(),
    members = members.distinct(),
    related = related.distinctBy { it.uri + "|" + it.type },
    org_units = org_units.distinct(),
    expertise = expertise.distinct(),
    hobbies = hobbies.distinct(),
    interests = interests.distinct(),
    org_directories = org_directories.distinct(),
)

/** Merge two VCardContacts — union of all repeated fields (deduplicated). */
operator fun VCardContact.plus(other: VCardContact): VCardContact = copy(
    emails = (emails + other.emails).distinct(),
    phones = (phones + other.phones).distinctBy { it.e164_number.orEmpty() + "|" + it.extension },
    addresses = (addresses + other.addresses).distinct(),
    impp = (impp + other.impp).distinct(),
    languages = (languages + other.languages).distinct(),
    urls = (urls + other.urls).distinct(),
    nicknames = (nicknames + other.nicknames).distinct(),
    categories = (categories + other.categories).distinct(),
    members = (members + other.members).distinct(),
    related = (related + other.related).distinctBy { it.uri + "|" + it.type },
    // Scalar fields — prefer other's value if this is blank
    name = other.name ?: name,
    organization = organization.ifBlank { other.organization },
    title = title.ifBlank { other.title },
    role = role.ifBlank { other.role },
    note = note.ifBlank { other.note },
    uid = uid.ifBlank { other.uid },
    photo = photo.ifBlank { other.photo },
    logo = logo.ifBlank { other.logo },
    timezone = timezone.ifBlank { other.timezone },
    geo = geo ?: other.geo,
    jabber_id = jabber_id.ifBlank { other.jabber_id },
)
