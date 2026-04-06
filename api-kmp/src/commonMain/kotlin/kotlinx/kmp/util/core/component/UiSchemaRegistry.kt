package kotlinx.kmp.util.core.component

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Comprehensive OpenAPI type+format → HTML input type + UI widget mapping.
 *
 * Maps every OpenAPI type/format combination to:
 * 1. HTML input type (text, number, email, tel, url, date, datetime-local, etc.)
 * 2. UI widget hint (select, textarea, slider, color-picker, file-upload, etc.)
 * 3. Category for grouping in JSON Forms UI Schema
 * 4. Validation pattern
 *
 * Covers: standard OpenAPI, measure units, currencies, countries, vCard
 * properties/sub-properties, iCalendar properties/sub-properties, and
 * custom extension types — all extending the base string type.
 *
 * Each entry is @Serializable so the mapping itself can be transmitted
 * via any ContentFormat (JSON/CBOR/Proto/MsgPack) as part of the UI Schema.
 */
@Serializable
data class UiFieldMapping(
    /** HTML input type attribute */
    val inputType: String,
    /** UI widget hint (for richer controls beyond HTML input) */
    val widget: String = inputType,
    /** Category for JSON Forms Categorization layout */
    val category: String = "General",
    /** Validation regex pattern (optional) */
    val pattern: String? = null,
    /** Placeholder text */
    val placeholder: String? = null,
    /** Min/max constraints for numeric types */
    val min: Double? = null,
    val max: Double? = null,
    /** Step for numeric inputs */
    val step: Double? = null,
    /** Enum options for select/radio widgets */
    val options: List<String>? = null,
    /** Unit label (for measure types) */
    val unit: String? = null,
    /** Currency code (for monetary types) */
    val currencyCode: String? = null,
    /** Max string length — switches input→textarea when >255 */
    val maxLength: Int? = null,
    /** Min string length */
    val minLength: Int? = null,
    /** Min/max items for array types */
    val minItems: Int? = null,
    val maxItems: Int? = null,
    /** Unique items constraint (array → set semantics) */
    val uniqueItems: Boolean = false,
    /** Whether this field maps to a PG18 jsonb column or SQLite TEXT AS JSON */
    val jsonbColumn: Boolean = false,
    /** SQL column type override for DatabaseEmitter (e.g. "TEXT", "INTEGER", "JSONB") */
    val sqlType: String? = null,
) {
    /** Resolved widget: auto-upgrade based on constraints */
    val resolvedWidget: String get() = when {
        maxLength != null && maxLength > 255 && widget in setOf("text", "input") -> "textarea"
        uniqueItems && widget == "array-editor" -> "set-editor"
        else -> widget
    }

    /** Whether this represents a set (unique items) vs list */
    val isSet: Boolean get() = uniqueItems

    /** Resolved HTML input type: textarea overrides to text (textarea is not an input type) */
    val resolvedInputType: String get() = when {
        resolvedWidget == "textarea" -> "text"
        else -> inputType
    }

    /** SQL type for PG18 / SQLDelight generation */
    val resolvedSqlType: String get() = sqlType ?: when {
        jsonbColumn -> "JSONB"
        inputType == "number" && step == 1.0 -> "INTEGER"
        inputType == "number" -> "REAL"
        inputType == "checkbox" -> "BOOLEAN"
        inputType == "date" || inputType == "datetime-local" -> "TEXT" // ISO 8601
        maxLength != null -> "VARCHAR($maxLength)"
        else -> "TEXT"
    }
}

/**
 * Master registry: OpenAPI type+format → [UiFieldMapping].
 *
 * Usage:
 * ```kotlin
 * val mapping = UiSchemaRegistry.forTypeFormat("string", "email")
 * // → UiFieldMapping(inputType = "email", widget = "email", category = "Contact", ...)
 * ```
 */
object UiSchemaRegistry {

    fun forTypeFormat(
        type: String,
        format: String? = null,
        maxLength: Int? = null,
        minLength: Int? = null,
    ): UiFieldMapping {
        val base = FORMAT_MAP[format ?: type] ?: typeDefault(type)
        return if (maxLength != null || minLength != null) {
            base.copy(maxLength = maxLength ?: base.maxLength, minLength = minLength ?: base.minLength)
        } else base
    }

    private fun typeDefault(type: String): UiFieldMapping = when (type) {
        "string" -> UiFieldMapping("text")
        "integer", "int32", "int64" -> UiFieldMapping("number", step = 1.0)
        "number", "float", "double" -> UiFieldMapping("number", step = 0.01)
        "boolean" -> UiFieldMapping("checkbox", widget = "checkbox")
        "array" -> UiFieldMapping("text", widget = "array-editor")
        "object" -> UiFieldMapping("text", widget = "json-editor")
        else -> UiFieldMapping("text")
    }

    private val FORMAT_MAP: Map<String, UiFieldMapping> = buildMap {
        // === Standard OpenAPI string formats ===
        put("email", UiFieldMapping("email", category = "Contact", pattern = "[^@]+@[^@]+\\.[^@]+", placeholder = "user@example.com"))
        put("phone", UiFieldMapping("tel", category = "Contact", placeholder = "+1-234-567-8900"))
        put("uri", UiFieldMapping("url", category = "Link", placeholder = "https://"))
        put("url", UiFieldMapping("url", category = "Link", placeholder = "https://"))
        put("hostname", UiFieldMapping("text", category = "Network", placeholder = "example.com"))
        put("ipv4", UiFieldMapping("text", category = "Network", pattern = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", placeholder = "192.168.1.1"))
        put("ipv6", UiFieldMapping("text", category = "Network", placeholder = "::1"))
        put("uuid", UiFieldMapping("text", category = "Identity", pattern = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"))
        put("date", UiFieldMapping("date", category = "DateTime"))
        put("date-time", UiFieldMapping("datetime-local", category = "DateTime"))
        put("time", UiFieldMapping("time", category = "DateTime"))
        put("duration", UiFieldMapping("text", category = "DateTime", placeholder = "PT1H30M", pattern = "P.*"))
        put("password", UiFieldMapping("password", category = "Security"))
        put("byte", UiFieldMapping("text", widget = "base64-editor", category = "Binary"))
        put("base64", UiFieldMapping("text", widget = "base64-editor", category = "Binary"))
        put("binary", UiFieldMapping("file", widget = "file-upload", category = "Binary"))
        put("regex", UiFieldMapping("text", category = "Validation", placeholder = "^[a-z]+$"))

        // === Numeric formats ===
        put("int32", UiFieldMapping("number", category = "Numeric", step = 1.0, min = -2147483648.0, max = 2147483647.0))
        put("int64", UiFieldMapping("number", category = "Numeric", step = 1.0))
        put("float", UiFieldMapping("number", category = "Numeric", step = 0.001))
        put("double", UiFieldMapping("number", category = "Numeric", step = 0.000001))

        // === Identity/Financial formats ===
        put("isbn", UiFieldMapping("text", category = "Identity", pattern = "[0-9X-]+"))
        put("isbn10", UiFieldMapping("text", category = "Identity", pattern = "[0-9X]{10}"))
        put("isbn13", UiFieldMapping("text", category = "Identity", pattern = "[0-9]{13}"))
        put("issn", UiFieldMapping("text", category = "Identity"))
        put("isin", UiFieldMapping("text", category = "Financial"))
        put("credit-card", UiFieldMapping("text", category = "Financial", pattern = "[0-9 -]+", placeholder = "4111 1111 1111 1111"))
        put("ean13", UiFieldMapping("text", category = "Identity", pattern = "[0-9]{13}"))
        put("luhn", UiFieldMapping("text", category = "Financial"))

        // === Currency / Country / Language / Locale ===
        put("currency", UiFieldMapping("text", widget = "select", category = "Locale",
            options = listOf("USD", "EUR", "GBP", "JPY", "CHF", "CAD", "AUD", "CNY", "INR", "BRL"),
            pattern = "[A-Z]{3}", placeholder = "USD"))
        put("country", UiFieldMapping("text", widget = "select", category = "Locale",
            options = listOf("US", "GB", "DE", "FR", "JP", "CN", "IN", "BR", "CA", "AU"),
            pattern = "[A-Z]{2}", placeholder = "US"))
        put("language", UiFieldMapping("text", widget = "select", category = "Locale",
            options = listOf("en", "es", "fr", "de", "ja", "zh", "hi", "pt", "ar", "ru"),
            pattern = "[a-z]{2,3}", placeholder = "en"))
        put("locale", UiFieldMapping("text", widget = "select", category = "Locale",
            pattern = "[a-z]{2,3}(-[A-Z]{2})?", placeholder = "en-US"))
        put("timezone", UiFieldMapping("text", widget = "select", category = "Locale",
            placeholder = "America/New_York"))

        // === Measure / Unit types ===
        put("measure-length", UiFieldMapping("number", widget = "measure", category = "Measure", unit = "m", step = 0.01))
        put("measure-mass", UiFieldMapping("number", widget = "measure", category = "Measure", unit = "kg", step = 0.001))
        put("measure-temperature", UiFieldMapping("number", widget = "measure", category = "Measure", unit = "°C", step = 0.1))
        put("measure-duration", UiFieldMapping("text", widget = "duration-picker", category = "Measure", placeholder = "PT1H"))
        put("measure-area", UiFieldMapping("number", widget = "measure", category = "Measure", unit = "m²", step = 0.01))
        put("measure-volume", UiFieldMapping("number", widget = "measure", category = "Measure", unit = "L", step = 0.001))
        put("measure-speed", UiFieldMapping("number", widget = "measure", category = "Measure", unit = "m/s", step = 0.01))
        put("measure-currency", UiFieldMapping("number", widget = "currency-input", category = "Financial", step = 0.01))

        // === vCard property types (RFC 6350) ===
        put("vcard-fn", UiFieldMapping("text", category = "vCard", placeholder = "Full Name"))
        put("vcard-n", UiFieldMapping("text", widget = "name-editor", category = "vCard"))
        put("vcard-tel", UiFieldMapping("tel", category = "vCard", placeholder = "+1-234-567-8900"))
        put("vcard-email", UiFieldMapping("email", category = "vCard"))
        put("vcard-adr", UiFieldMapping("text", widget = "address-editor", category = "vCard"))
        put("vcard-org", UiFieldMapping("text", category = "vCard", placeholder = "Organization"))
        put("vcard-title", UiFieldMapping("text", category = "vCard", placeholder = "Job Title"))
        put("vcard-role", UiFieldMapping("text", category = "vCard", placeholder = "Role"))
        put("vcard-url", UiFieldMapping("url", category = "vCard"))
        put("vcard-photo", UiFieldMapping("file", widget = "image-upload", category = "vCard"))
        put("vcard-bday", UiFieldMapping("date", category = "vCard"))
        put("vcard-note", UiFieldMapping("text", widget = "textarea", category = "vCard", maxLength = 4000))
        put("vcard-geo", UiFieldMapping("text", widget = "geo-picker", category = "vCard", placeholder = "lat,lng"))
        put("vcard-impp", UiFieldMapping("text", category = "vCard", placeholder = "xmpp:user@example.com"))
        put("vcard-x-socialprofile", UiFieldMapping("url", category = "vCard", placeholder = "https://twitter.com/user"))
        put("vcf", UiFieldMapping("text", widget = "textarea", category = "vCard", jsonbColumn = true))

        // === iCalendar property types (RFC 5545) ===
        put("ical-dtstart", UiFieldMapping("datetime-local", category = "iCalendar"))
        put("ical-dtend", UiFieldMapping("datetime-local", category = "iCalendar"))
        put("ical-summary", UiFieldMapping("text", category = "iCalendar", placeholder = "Event title"))
        put("ical-description", UiFieldMapping("text", widget = "textarea", category = "iCalendar", maxLength = 4000))
        put("ical-location", UiFieldMapping("text", widget = "location-picker", category = "iCalendar"))
        put("ical-status", UiFieldMapping("text", widget = "select", category = "iCalendar",
            options = listOf("TENTATIVE", "CONFIRMED", "CANCELLED")))
        put("ical-partstat", UiFieldMapping("text", widget = "select", category = "iCalendar",
            options = listOf("NEEDS-ACTION", "ACCEPTED", "DECLINED", "TENTATIVE", "DELEGATED")))
        put("ical-role", UiFieldMapping("text", widget = "select", category = "iCalendar",
            options = listOf("CHAIR", "REQ-PARTICIPANT", "OPT-PARTICIPANT", "NON-PARTICIPANT")))
        put("ical-action", UiFieldMapping("text", widget = "select", category = "iCalendar",
            options = listOf("AUDIO", "DISPLAY", "EMAIL")))
        put("ical-rrule", UiFieldMapping("text", widget = "rrule-editor", category = "iCalendar", placeholder = "FREQ=WEEKLY;BYDAY=MO"))
        put("ical-attendee", UiFieldMapping("email", widget = "attendee-editor", category = "iCalendar"))
        put("ical-organizer", UiFieldMapping("email", category = "iCalendar"))
        put("ical-priority", UiFieldMapping("number", widget = "slider", category = "iCalendar", min = 0.0, max = 9.0, step = 1.0))
        put("ics", UiFieldMapping("text", widget = "textarea", category = "iCalendar", jsonbColumn = true))

        // === Geo types ===
        put("geo", UiFieldMapping("text", widget = "geo-picker", category = "Geography", placeholder = "48.8566,2.3522"))
        put("geo-lat", UiFieldMapping("number", category = "Geography", min = -90.0, max = 90.0, step = 0.000001))
        put("geo-lng", UiFieldMapping("number", category = "Geography", min = -180.0, max = 180.0, step = 0.000001))
        put("geojson", UiFieldMapping("text", widget = "geojson-editor", category = "Geography", jsonbColumn = true))

        // === Person / Address ===
        put("person-name", UiFieldMapping("text", widget = "name-editor", category = "Person"))
        put("address", UiFieldMapping("text", widget = "address-editor", category = "Person"))

        // === Custom / Extension ===
        put("color", UiFieldMapping("color", category = "Custom"))
        put("markdown", UiFieldMapping("text", widget = "markdown-editor", category = "Content", maxLength = 65535))
        put("html", UiFieldMapping("text", widget = "rich-text-editor", category = "Content", maxLength = 65535))
        put("json", UiFieldMapping("text", widget = "json-editor", category = "Data", jsonbColumn = true))
        put("yaml", UiFieldMapping("text", widget = "code-editor", category = "Data"))
        put("xml", UiFieldMapping("text", widget = "code-editor", category = "Data"))
        put("cron", UiFieldMapping("text", widget = "cron-editor", category = "Scheduling", placeholder = "0 */5 * * *"))
    }

    /** All registered format names. */
    val allFormats: Set<String> get() = FORMAT_MAP.keys

    /** All categories. */
    val allCategories: Set<String> get() = FORMAT_MAP.values.map { it.category }.toSet()
}
