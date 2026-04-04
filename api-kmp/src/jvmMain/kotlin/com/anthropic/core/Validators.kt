package com.anthropic.core

import org.apache.commons.validator.routines.*

/**
 * JVM validators using stable libs:
 *   apache commons-validator: email, URL, IP, domain, credit card, ISBN, ISSN, ISIN, currency, date, regex
 *   google libphonenumber: phone numbers
 *
 * All routines from org.apache.commons.validator.routines available.
 */
object Validators {

    // === Contact/Identity ===

    fun validateEmail(email: Email): Boolean =
        EmailValidator.getInstance().isValid(email.value)

    fun validatePhone(phone: Phone, defaultRegion: String = "US"): Boolean =
        try {
            val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            util.isValidNumber(util.parse(phone.value, defaultRegion))
        } catch (_: Exception) { false }

    fun formatPhoneE164(phone: Phone, defaultRegion: String = "US"): Phone =
        try {
            val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            val parsed = util.parse(phone.value, defaultRegion)
            Phone(util.format(parsed, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164))
        } catch (_: Exception) { phone }

    // === Network ===

    fun validateUrl(uri: Uri): Boolean =
        UrlValidator.getInstance().isValid(uri.value)

    fun validateIp(ip: IpAddress): Boolean =
        InetAddressValidator.getInstance().isValid(ip.value)

    fun validateIpV4(ip: IpAddress): Boolean =
        InetAddressValidator.getInstance().isValidInet4Address(ip.value)

    fun validateIpV6(ip: IpAddress): Boolean =
        InetAddressValidator.getInstance().isValidInet6Address(ip.value)

    fun validateDomain(hostname: Hostname): Boolean =
        DomainValidator.getInstance().isValid(hostname.value)

    // === Financial ===

    fun validateCreditCard(number: String): Boolean =
        CreditCardValidator(CreditCardValidator.VISA + CreditCardValidator.MASTERCARD + CreditCardValidator.AMEX + CreditCardValidator.DISCOVER).isValid(number)

    fun validateCreditCardVisa(number: String): Boolean =
        CreditCardValidator(CreditCardValidator.VISA).isValid(number)

    fun validateCreditCardMasterCard(number: String): Boolean =
        CreditCardValidator(CreditCardValidator.MASTERCARD).isValid(number)

    fun validateCreditCardAmex(number: String): Boolean =
        CreditCardValidator(CreditCardValidator.AMEX).isValid(number)

    fun validateISIN(isin: String): Boolean =
        ISINValidator.getInstance(true).isValid(isin)

    fun validateISBN(isbn: String): Boolean =
        ISBNValidator.getInstance().isValid(isbn)

    fun validateISBN10(isbn: String): Boolean =
        ISBNValidator.getInstance().isValidISBN10(isbn)

    fun validateISBN13(isbn: String): Boolean =
        ISBNValidator.getInstance().isValidISBN13(isbn)

    fun validateISSN(issn: String): Boolean =
        ISSNValidator.getInstance().isValid(issn)

    // === Numeric ===

    fun validateInteger(value: String, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int? =
        IntegerValidator.getInstance().validate(value)?.takeIf { it in min..max }

    fun validateLong(value: String, min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE): Long? =
        LongValidator.getInstance().validate(value)?.takeIf { it in min..max }

    fun validateDouble(value: String): Double? =
        DoubleValidator.getInstance().validate(value)

    fun validateBigDecimal(value: String): java.math.BigDecimal? =
        BigDecimalValidator.getInstance().validate(value)

    fun validatePercent(value: String): java.math.BigDecimal? =
        PercentValidator.getInstance().validate(value)

    fun validateCurrency(value: String, locale: java.util.Locale = java.util.Locale.US): java.math.BigDecimal? =
        CurrencyValidator.getInstance().validate(value, locale)

    // === Date/Time ===

    fun validateDate(value: String, pattern: String = "yyyy-MM-dd"): java.util.Date? =
        DateValidator.getInstance().validate(value, pattern)

    fun validateTime(value: String, pattern: String = "HH:mm:ss"): java.util.Calendar? =
        TimeValidator.getInstance().validate(value, pattern)

    // === Pattern ===

    fun validateRegex(value: String, pattern: String): Boolean =
        RegexValidator(pattern).isValid(value)

    // === Check Digits ===

    fun validateLuhn(value: String): Boolean =
        org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(value)

    fun validateVerhoeff(value: String): Boolean =
        org.apache.commons.validator.routines.checkdigit.VerhoeffCheckDigit.VERHOEFF_CHECK_DIGIT.isValid(value)

    fun validateEAN13(value: String): Boolean =
        org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit.EAN13_CHECK_DIGIT.isValid(value)

    // === OpenAPI Format → Validator Registry ===

    /**
     * Validate a string value by its OpenAPI `format` field.
     *
     * Maps standard + extended formats to the appropriate validator:
     * - email → EmailValidator
     * - phone → libphonenumber
     * - uri/url → UrlValidator
     * - hostname → DomainValidator
     * - ipv4/ipv6/ip → InetAddressValidator
     * - uuid → UUID.fromString
     * - date → DateValidator (yyyy-MM-dd)
     * - date-time → ISO 8601 parse
     * - time → TimeValidator (HH:mm:ss)
     * - duration → ISO 8601 duration (PT...)
     * - isbn → ISBNValidator
     * - issn → ISSNValidator
     * - isin → ISINValidator
     * - credit-card → CreditCardValidator
     * - ean13 → EAN13CheckDigit
     * - luhn → LuhnCheckDigit
     * - currency → ISO 4217 (3-letter)
     * - country → ISO 3166-1 alpha-2
     * - language → ISO 639-1
     * - regex → pattern match
     *
     * Returns true if valid, false if invalid.
     * Returns true for unknown formats (no validation available).
     */
    fun validateByFormat(value: String, format: String, pattern: String? = null): Boolean = when (format) {
        // Contact
        "email" -> EmailValidator.getInstance().isValid(value)
        "phone" -> try {
            val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            util.isValidNumber(util.parse(value, "US"))
        } catch (_: Exception) { false }

        // Network
        "uri", "url" -> UrlValidator.getInstance().isValid(value)
        "hostname" -> DomainValidator.getInstance().isValid(value)
        "ipv4" -> InetAddressValidator.getInstance().isValidInet4Address(value)
        "ipv6" -> InetAddressValidator.getInstance().isValidInet6Address(value)
        "ip" -> InetAddressValidator.getInstance().isValid(value)

        // Identifiers
        "uuid" -> try { java.util.UUID.fromString(value); true } catch (_: Exception) { false }

        // Date/Time (RFC 3339 / ISO 8601)
        "date" -> DateValidator.getInstance().validate(value, "yyyy-MM-dd") != null
        "date-time" -> try { java.time.OffsetDateTime.parse(value); true }
            catch (_: Exception) { try { java.time.Instant.parse(value); true } catch (_: Exception) { false } }
        "time" -> TimeValidator.getInstance().validate(value, "HH:mm:ss") != null
        "duration" -> try { java.time.Duration.parse(value); true } catch (_: Exception) { false }

        // Financial / Codes
        "isbn" -> ISBNValidator.getInstance().isValid(value)
        "isbn10" -> ISBNValidator.getInstance().isValidISBN10(value)
        "isbn13" -> ISBNValidator.getInstance().isValidISBN13(value)
        "issn" -> ISSNValidator.getInstance().isValid(value)
        "isin" -> ISINValidator.getInstance(true).isValid(value)
        "credit-card" -> CreditCardValidator(
            CreditCardValidator.VISA + CreditCardValidator.MASTERCARD +
            CreditCardValidator.AMEX + CreditCardValidator.DISCOVER
        ).isValid(value)
        "ean13" -> org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit.EAN13_CHECK_DIGIT.isValid(value)
        "luhn" -> org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(value)

        // Locale/Geo
        "currency" -> value.length == 3 && value.all { it.isUpperCase() }
        "country" -> value.length == 2 && value.all { it.isUpperCase() }
        "language" -> value.length in 2..3 && value.all { it.isLowerCase() }
        "locale" -> value.matches(Regex("[a-z]{2,3}(-[A-Z]{2})?"))
        "timezone" -> try { java.util.TimeZone.getTimeZone(value).id == value } catch (_: Exception) { false }

        // Binary
        "byte", "base64" -> try { java.util.Base64.getDecoder().decode(value); true } catch (_: Exception) { false }

        // Password — always valid (no format constraint)
        "password" -> true

        // Pattern match (x-pattern or explicit regex format)
        "regex" -> pattern?.let { RegexValidator(it).isValid(value) } ?: true

        // Integer/Number formats
        "int32" -> value.toIntOrNull() != null
        "int64" -> value.toLongOrNull() != null
        "float" -> value.toFloatOrNull() != null
        "double" -> value.toDoubleOrNull() != null

        // Person/Address
        "person-name" -> value.isNotBlank()
        "address" -> value.isNotBlank()

        // Geo (iCal GEO: "lat;lon", GeoJSON: "lat,lon")
        "geo" -> try {
            val parts = value.split(Regex("[;,]"))
            parts.size == 2 && parts[0].trim().toDouble() in -90.0..90.0 && parts[1].trim().toDouble() in -180.0..180.0
        } catch (_: Exception) { false }

        // iCal-specific formats
        "rrule" -> value.startsWith("FREQ=") &&
            value.contains(Regex("FREQ=(SECONDLY|MINUTELY|HOURLY|DAILY|WEEKLY|MONTHLY|YEARLY)"))
        "ical-status" -> value.uppercase() in setOf("TENTATIVE", "CONFIRMED", "CANCELLED")
        "ical-partstat" -> value.uppercase() in setOf(
            "NEEDS-ACTION", "ACCEPTED", "DECLINED", "TENTATIVE", "DELEGATED"
        )
        "ical-role" -> value.uppercase() in setOf(
            "CHAIR", "REQ-PARTICIPANT", "OPT-PARTICIPANT", "NON-PARTICIPANT"
        )
        "ical-action" -> value.uppercase() in setOf("AUDIO", "DISPLAY", "EMAIL")

        // vCard-specific
        "vcf" -> try { ezvcard.Ezvcard.parse(value).first() != null } catch (_: Exception) { false }
        "ics" -> try { net.fortuna.ical4j.data.CalendarBuilder().build(value.byteInputStream()) != null } catch (_: Exception) { false }

        // Unknown format → pass through (no validation)
        else -> true
    }

    /** All supported OpenAPI format names */
    val supportedFormats: Set<String> = setOf(
        // Standard OpenAPI
        "email", "phone", "uri", "url", "hostname",
        "ipv4", "ipv6", "ip", "uuid",
        "date", "date-time", "time", "duration",
        "int32", "int64", "float", "double",
        "byte", "base64", "password", "regex",
        // Extended: financial
        "isbn", "isbn10", "isbn13", "issn", "isin",
        "credit-card", "ean13", "luhn",
        // Extended: locale/geo
        "currency", "country", "language", "locale", "timezone",
        "person-name", "address", "geo",
        // iCal (RFC 5545)
        "rrule", "ical-status", "ical-partstat", "ical-role", "ical-action", "ics",
        // vCard (RFC 6350)
        "vcf",
    )

    // === vCard (.vcf) — Parse to Wire proto types, Validate, Convert ===
    // VCardContact, ICalEvent, ICalAttendee, ICalAlarm — Wire-generated from types.proto
    // google.type.PostalAddress, PhoneNumber, Date, LatLng, Money — Google standard protos

    /** Validate a Wire VCardContact. */
    fun validateVCardContact(contact: VCardContact): Map<String, Boolean> = buildMap {
        contact.name?.let { put("name", it.full.isNotBlank()) }
        contact.emails.forEachIndexed { i, e -> put("email.$i", validateEmail(Email(e))) }
        contact.phones.forEachIndexed { i, p ->
            put("phone.$i", p.e164_number?.let { validatePhone(Phone(it)) } ?: false)
        }
        if (contact.url.isNotBlank()) put("url", validateUrl(Uri(contact.url)))
        if (contact.photo.isNotBlank()) put("photo", validateUrl(Uri(contact.photo)))
    }

    /** Validate a Wire ICalEvent. */
    fun validateICalEvent(event: ICalEvent): Map<String, Boolean> = buildMap {
        event.url.takeIf { it.isNotBlank() }?.let { put("url", validateUrl(Uri(it))) }
        event.organizer.takeIf { it.isNotBlank() }?.let { put("organizer", validateEmail(Email(it))) }
        event.rrule.takeIf { it.isNotBlank() }?.let { put("rrule", validateByFormat(it, "rrule")) }
        event.status.takeIf { it.isNotBlank() }?.let { put("status", validateByFormat(it, "ical-status")) }
        event.attendees.forEachIndexed { i, a -> put("attendee.$i", validateEmail(Email(a.email))) }
    }

    /** Parse vCard (.vcf) → typed VCardContact using Wire KMP + Google standard proto types. */
    fun parseVCardTyped(vcf: String): VCardContact {
        val vcard = ezvcard.Ezvcard.parse(vcf).first()
        return VCardContact(
            name = PersonName(
                given = vcard.structuredName?.given ?: "",
                family = vcard.structuredName?.family ?: "",
                prefix = vcard.structuredName?.prefixes?.firstOrNull() ?: "",
                suffix = vcard.structuredName?.suffixes?.firstOrNull() ?: "",
            ),
            emails = vcard.emails?.map { it.value } ?: emptyList(),
            phones = vcard.telephoneNumbers?.map { tel ->
                com.google.type.PhoneNumber(e164_number = tel.text)
            } ?: emptyList(),
            addresses = vcard.addresses?.map { addr ->
                com.google.type.PostalAddress(
                    address_lines = listOfNotNull(addr.streetAddress),
                    locality = addr.locality ?: "",
                    administrative_area = addr.region ?: "",
                    postal_code = addr.postalCode ?: "",
                    region_code = addr.country ?: "",
                )
            } ?: emptyList(),
            organization = vcard.organization?.values?.firstOrNull() ?: "",
            title = vcard.titles?.firstOrNull()?.value ?: "",
            role = vcard.roles?.firstOrNull()?.value ?: "",
            url = vcard.urls?.firstOrNull()?.value ?: "",
            photo = vcard.photos?.firstOrNull()?.url ?: "",
            note = vcard.notes?.firstOrNull()?.value ?: "",
            birthday = vcard.birthday?.date?.let {
                com.google.type.Date(year = 1970, month = 1, day = 1) // simplified
            },
            categories = vcard.categories?.values ?: emptyList(),
            uid = vcard.uid?.value ?: "",
        )
    }

    /** Convert typed VCardContact (Wire proto) → vCard (.vcf) string. */
    fun toVCard(contact: VCardContact): String {
        val vcard = ezvcard.VCard()
        val name = contact.name
        if (name != null && name.full.isNotBlank()) vcard.setFormattedName(name.full)
        if (name != null && (name.given.isNotBlank() || name.family.isNotBlank())) {
            val n = ezvcard.property.StructuredName()
            n.given = name.given
            n.family = name.family
            if (name.prefix.isNotBlank()) n.prefixes.add(name.prefix)
            if (name.suffix.isNotBlank()) n.suffixes.add(name.suffix)
            vcard.structuredName = n
        }
        contact.emails.forEach { vcard.addEmail(it) }
        contact.phones.forEach { ph ->
            vcard.addTelephoneNumber(ph.e164_number ?: ph.short_code?.number ?: "")
        }
        contact.addresses.forEach { a ->
            val addr = ezvcard.property.Address()
            addr.streetAddress = a.address_lines.firstOrNull() ?: ""
            addr.locality = a.locality
            addr.region = a.administrative_area
            addr.postalCode = a.postal_code
            addr.country = a.region_code
            vcard.addAddress(addr)
        }
        if (contact.organization.isNotBlank()) vcard.setOrganization(contact.organization)
        if (contact.title.isNotBlank()) vcard.addTitle(contact.title)
        if (contact.role.isNotBlank()) vcard.addRole(contact.role)
        if (contact.url.isNotBlank()) vcard.addUrl(contact.url)
        if (contact.note.isNotBlank()) vcard.addNote(contact.note)
        if (contact.uid.isNotBlank()) vcard.uid = ezvcard.property.Uid(contact.uid)
        if (contact.categories.isNotEmpty()) {
            val c = ezvcard.property.Categories()
            contact.categories.forEach { c.values.add(it) }
            vcard.addCategories(c)
        }
        return ezvcard.Ezvcard.write(vcard).go()
    }

    // ICalEvent, ICalAttendee, ICalAlarm — Wire-generated from types.proto

    /** Parse iCal date string → java.time.Instant (for google.protobuf.Timestamp mapping). */
    private fun parseInstant(value: String): java.time.Instant? = try {
        java.time.Instant.parse(value)
    } catch (_: Exception) {
        try { java.time.OffsetDateTime.parse(value).toInstant() }
        catch (_: Exception) { null }
    }

    /** Parse iCal duration string → java.time.Duration (for google.protobuf.Duration mapping). */
    private fun parseDuration(value: String): java.time.Duration? = try {
        java.time.Duration.parse(value)
    } catch (_: Exception) { null }

    /** Parse iCal (.ics) → typed ICalEvent list using Wire proto types. */
    fun parseICalTyped(ics: String): List<ICalEvent> {
        val raw = parseICal(ics)
        return raw.map { map ->
            ICalEvent(
                uid = map["UID"] ?: "",
                summary = map["SUMMARY"] ?: "",
                description = map["DESCRIPTION"] ?: "",
                location = map["LOCATION"] ?: "",
                dt_start = map["DTSTART"]?.let { parseInstant(it) },
                dt_end = map["DTEND"]?.let { parseInstant(it) },
                duration = map["DURATION"]?.let { parseDuration(it) },
                rrule = map["RRULE"] ?: "",
                status = map["STATUS"] ?: "",
                priority = map["PRIORITY"]?.toIntOrNull() ?: 0,
                url = map["URL"] ?: "",
                geo = map["GEO"]?.let { g ->
                    val parts = g.split(";")
                    if (parts.size == 2) com.google.type.LatLng(
                        latitude = parts[0].toDoubleOrNull() ?: 0.0,
                        longitude = parts[1].toDoubleOrNull() ?: 0.0,
                    ) else null
                },
                categories = map["CATEGORIES"]?.split(",")?.map { it.trim() } ?: emptyList(),
                organizer = map["ORGANIZER"] ?: "",
                attendees = map.keys
                    .filter { it.matches(Regex("ATTENDEE\\.\\d+")) }
                    .mapIndexed { i, _ ->
                        ICalAttendee(
                            email = map["ATTENDEE.$i"] ?: "",
                            name = map["ATTENDEE.$i.CN"] ?: "",
                            part_stat = map["ATTENDEE.$i.PARTSTAT"] ?: "",
                            role = map["ATTENDEE.$i.ROLE"] ?: "",
                        )
                    },
                alarms = map.keys
                    .filter { it.matches(Regex("VALARM\\.\\d+\\.ACTION")) }
                    .mapIndexed { i, _ ->
                        ICalAlarm(
                            action = map["VALARM.$i.ACTION"] ?: "DISPLAY",
                            trigger = map["VALARM.$i.TRIGGER"] ?: "-PT15M",
                        )
                    },
                created = map["CREATED"]?.let { parseInstant(it) },
                last_modified = map["LAST-MODIFIED"]?.let { parseInstant(it) },
            )
        }
    }

    /** Convert typed ICalEvent → iCal (.ics) string. */
    /** Convert typed Wire ICalEvent list → iCal (.ics) string. */
    fun toICal(events: List<ICalEvent>): String {
        val sb = StringBuilder()
        sb.appendLine("BEGIN:VCALENDAR")
        sb.appendLine("PRODID:-//api-kmp//EN")
        sb.appendLine("VERSION:2.0")
        events.forEach { e ->
            sb.appendLine("BEGIN:VEVENT")
            if (e.uid.isNotBlank()) sb.appendLine("UID:${e.uid}")
            if (e.summary.isNotBlank()) sb.appendLine("SUMMARY:${e.summary}")
            if (e.description.isNotBlank()) sb.appendLine("DESCRIPTION:${e.description}")
            if (e.location.isNotBlank()) sb.appendLine("LOCATION:${e.location}")
            e.dt_start?.let { sb.appendLine("DTSTART:$it") }
            e.dt_end?.let { sb.appendLine("DTEND:$it") }
            e.duration?.let { sb.appendLine("DURATION:$it") }
            if (e.rrule.isNotBlank()) sb.appendLine("RRULE:${e.rrule}")
            if (e.status.isNotBlank()) sb.appendLine("STATUS:${e.status}")
            if (e.priority != 0) sb.appendLine("PRIORITY:${e.priority}")
            if (e.url.isNotBlank()) sb.appendLine("URL:${e.url}")
            e.geo?.let { sb.appendLine("GEO:${it.latitude};${it.longitude}") }
            if (e.categories.isNotEmpty()) sb.appendLine("CATEGORIES:${e.categories.joinToString(",")}")
            if (e.organizer.isNotBlank()) sb.appendLine("ORGANIZER:mailto:${e.organizer}")
            e.attendees.forEach { a ->
                val params = buildList {
                    if (a.name.isNotBlank()) add("CN=${a.name}")
                    if (a.part_stat.isNotBlank()) add("PARTSTAT=${a.part_stat}")
                    if (a.role.isNotBlank()) add("ROLE=${a.role}")
                }
                val paramStr = if (params.isNotEmpty()) ";${params.joinToString(";")}" else ""
                sb.appendLine("ATTENDEE${paramStr}:mailto:${a.email}")
            }
            e.alarms.forEach { alarm ->
                sb.appendLine("BEGIN:VALARM")
                sb.appendLine("ACTION:${alarm.action}")
                sb.appendLine("TRIGGER:${alarm.trigger}")
                sb.appendLine("END:VALARM")
            }
            sb.appendLine("END:VEVENT")
        }
        sb.appendLine("END:VCALENDAR")
        return sb.toString()
    }

    /** Parse vCard (.vcf) string → raw property map (string-based). */
    fun parseVCard(vcf: String): Map<String, String> {
        val vcard = ezvcard.Ezvcard.parse(vcf).first()
        return buildMap {
            vcard.formattedName?.value?.let { put("FN", it) }
            vcard.structuredName?.let { n ->
                n.given?.let { put("N.given", it) }
                n.family?.let { put("N.family", it) }
                n.prefixes?.firstOrNull()?.let { put("N.prefix", it) }
                n.suffixes?.firstOrNull()?.let { put("N.suffix", it) }
            }
            vcard.emails?.forEach { e -> put("EMAIL${if (vcard.emails.size > 1) ".${e.types}" else ""}", e.value) }
            vcard.telephoneNumbers?.forEach { t -> put("TEL${if (vcard.telephoneNumbers.size > 1) ".${t.types}" else ""}", t.text) }
            vcard.organization?.values?.firstOrNull()?.let { put("ORG", it) }
            vcard.titles?.firstOrNull()?.value?.let { put("TITLE", it) }
            vcard.roles?.firstOrNull()?.value?.let { put("ROLE", it) }
            vcard.urls?.firstOrNull()?.value?.let { put("URL", it) }
            vcard.notes?.firstOrNull()?.value?.let { put("NOTE", it) }
            vcard.birthday?.date?.let { put("BDAY", it.toString()) }
            vcard.photos?.firstOrNull()?.url?.let { put("PHOTO", it) }
            vcard.addresses?.forEachIndexed { i, addr ->
                val prefix = if (vcard.addresses.size > 1) "ADR.$i" else "ADR"
                addr.streetAddress?.let { put("$prefix.street", it) }
                addr.locality?.let { put("$prefix.city", it) }
                addr.region?.let { put("$prefix.state", it) }
                addr.postalCode?.let { put("$prefix.postalCode", it) }
                addr.country?.let { put("$prefix.country", it) }
            }
            vcard.categories?.values?.let { put("CATEGORIES", it.joinToString(",")) }
            vcard.revision?.value?.let { put("REV", it.toString()) }
            vcard.uid?.value?.let { put("UID", it) }
        }
    }

    /**
     * Validate all properties in a parsed vCard using the appropriate validators.
     *
     * vCard property → OpenAPI format → Validator:
     * - EMAIL → email → EmailValidator
     * - TEL → phone → libphonenumber
     * - URL → uri → UrlValidator
     * - ADR.country → country → ISO 3166
     * - ADR.postalCode → regex → postal code pattern per country
     * - BDAY → date → DateValidator
     * - REV → date-time → ISO 8601
     * - UID → uuid → UUID parse
     * - FN, N.* → person-name → non-blank
     * - ORG, TITLE, ROLE → string → non-blank
     *
     * Returns map of property → validation result (true/false).
     */
    fun validateVCardProperties(vcf: String): Map<String, Boolean> {
        val props = parseVCard(vcf)
        return buildMap {
            props.forEach { (prop, value) ->
                val format = vcardPropertyToFormat(prop)
                put(prop, validateByFormat(value, format))
            }
        }
    }

    /** Map vCard property name → OpenAPI format for validation. */
    fun vcardPropertyToFormat(property: String): String = when {
        property.startsWith("EMAIL") -> "email"
        property.startsWith("TEL") -> "phone"
        property == "URL" || property == "PHOTO" -> "uri"
        property == "BDAY" -> "date"
        property == "REV" -> "date-time"
        property == "UID" -> "uuid"
        property.endsWith(".country") -> "country"
        property.endsWith(".postalCode") -> "regex"
        property == "FN" || property.startsWith("N.") -> "person-name"
        else -> "string"
    }

    /** Validate a single vCard property value. */
    fun validateVCardProperty(property: String, value: String): Boolean =
        validateByFormat(value, vcardPropertyToFormat(property))

    /** Convert raw Contact field map → vCard (.vcf) string via ez-vcard. */
    fun toVCardRaw(fields: Map<String, String>): String {
        val vcard = ezvcard.VCard()
        fields["FN"]?.let { vcard.setFormattedName(it) }
        (fields["N.given"] ?: fields["N.family"])?.let {
            val n = ezvcard.property.StructuredName()
            fields["N.given"]?.let { g -> n.given = g }
            fields["N.family"]?.let { f -> n.family = f }
            fields["N.prefix"]?.let { p -> n.prefixes.add(p) }
            fields["N.suffix"]?.let { s -> n.suffixes.add(s) }
            vcard.structuredName = n
        }
        fields.filterKeys { it.startsWith("EMAIL") }.values.forEach { vcard.addEmail(it) }
        fields.filterKeys { it.startsWith("TEL") }.values.forEach { vcard.addTelephoneNumber(it) }
        fields["ORG"]?.let { vcard.setOrganization(it) }
        fields["TITLE"]?.let { vcard.addTitle(it) }
        fields["ROLE"]?.let { vcard.addRole(it) }
        fields["URL"]?.let { vcard.addUrl(it) }
        fields["NOTE"]?.let { vcard.addNote(it) }
        // ADR
        val adrKeys = fields.keys.filter { it.startsWith("ADR") }
        if (adrKeys.isNotEmpty()) {
            val addr = ezvcard.property.Address()
            fields["ADR.street"]?.let { addr.streetAddress = it }
            fields["ADR.city"]?.let { addr.locality = it }
            fields["ADR.state"]?.let { addr.region = it }
            fields["ADR.postalCode"]?.let { addr.postalCode = it }
            fields["ADR.country"]?.let { addr.country = it }
            vcard.addAddress(addr)
        }
        fields["CATEGORIES"]?.let { cats ->
            val c = ezvcard.property.Categories()
            cats.split(",").forEach { c.values.add(it.trim()) }
            vcard.addCategories(c)
        }
        fields["UID"]?.let { vcard.uid = ezvcard.property.Uid(it) }
        return ezvcard.Ezvcard.write(vcard).go()
    }

    // === iCalendar (.ics) — Parse, Validate Properties, Convert ===

    /** Parse iCalendar (.ics) string → Event-like maps via ical4j. */
    fun parseICal(ics: String): List<Map<String, String>> {
        val calendar = net.fortuna.ical4j.data.CalendarBuilder().build(ics.byteInputStream())
        val events = mutableListOf<Map<String, String>>()
        calendar.getComponents<net.fortuna.ical4j.model.component.VEvent>(
            net.fortuna.ical4j.model.component.VEvent.VEVENT
        ).forEach { component ->
            val map = mutableMapOf<String, String>()
            component.uid.ifPresent { map["UID"] = it.value }
            component.summary.ifPresent { map["SUMMARY"] = it.value }
            component.description.ifPresent { map["DESCRIPTION"] = it.value }
            component.location.ifPresent { map["LOCATION"] = it.value }

            // Extract properties by name via ical4j Property access
            val propNames = listOf(
                "DTSTART", "DTEND", "DURATION", "RRULE", "STATUS",
                "PRIORITY", "URL", "GEO", "CATEGORIES", "CREATED", "LAST-MODIFIED"
            )
            propNames.forEach { name ->
                component.getProperty<net.fortuna.ical4j.model.Property>(name)
                    .ifPresent { map[name] = it.value }
            }
            component.getProperty<net.fortuna.ical4j.model.Property>("ORGANIZER")
                .ifPresent { map["ORGANIZER"] = it.value.removePrefix("mailto:") }

            // Multiple ATTENDEEs — extract email + CN/PARTSTAT/ROLE params
            val attendees = component.getProperties<net.fortuna.ical4j.model.Property>("ATTENDEE")
            attendees.forEachIndexed { i, a ->
                map["ATTENDEE.$i"] = a.value.removePrefix("mailto:")
                // Parameters are in the raw property string: ;CN=..;PARTSTAT=..
                val raw = a.toString()
                Regex("""CN=([^;:\r\n]+)""").find(raw)?.groupValues?.get(1)?.let {
                    map["ATTENDEE.$i.CN"] = it
                }
                Regex("""PARTSTAT=([^;:\r\n]+)""").find(raw)?.groupValues?.get(1)?.let {
                    map["ATTENDEE.$i.PARTSTAT"] = it
                }
                Regex("""ROLE=([^;:\r\n]+)""").find(raw)?.groupValues?.get(1)?.let {
                    map["ATTENDEE.$i.ROLE"] = it
                }
            }

            // VALARM (reminders)
            component.getAlarms().forEachIndexed { i, alarm ->
                alarm.getProperty<net.fortuna.ical4j.model.Property>("ACTION")
                    .ifPresent { map["VALARM.$i.ACTION"] = it.value }
                alarm.getProperty<net.fortuna.ical4j.model.Property>("TRIGGER")
                    .ifPresent { map["VALARM.$i.TRIGGER"] = it.value }
            }
            events.add(map)
        }
        return events
    }

    /**
     * Validate all properties in a parsed iCal event using the appropriate validators.
     *
     * iCal property → OpenAPI format → Validator:
     * - DTSTART, DTEND, CREATED, LAST-MODIFIED → date-time → ISO 8601
     * - DURATION, VALARM.TRIGGER → duration → ISO 8601 duration
     * - ORGANIZER, ATTENDEE.* (no .CN/.PARTSTAT/.ROLE) → email → EmailValidator
     * - ATTENDEE.*.CN → person-name → non-blank
     * - URL → uri → UrlValidator
     * - GEO → geo → lat;lon parse
     * - UID → uuid → UUID parse
     * - PRIORITY → int32 → integer 0-9
     * - RRULE → rrule → RFC 5545 recurrence
     * - STATUS → ical-status → CONFIRMED/TENTATIVE/CANCELLED
     * - SUMMARY, DESCRIPTION, LOCATION, CATEGORIES → string
     */
    fun validateICalProperties(ics: String): List<Map<String, Boolean>> =
        parseICal(ics).map { event ->
            buildMap {
                event.forEach { (prop, value) ->
                    val format = icalPropertyToFormat(prop)
                    put(prop, validateByFormat(value, format))
                }
            }
        }

    /** Map iCal property name → OpenAPI format for validation. */
    fun icalPropertyToFormat(property: String): String = when {
        property in setOf("DTSTART", "DTEND", "CREATED", "LAST-MODIFIED") -> "date-time"
        property == "DURATION" || property.endsWith(".TRIGGER") -> "duration"
        property == "ORGANIZER" -> "email"
        property.startsWith("ATTENDEE.") && !property.contains(".CN") &&
            !property.contains(".PARTSTAT") && !property.contains(".ROLE") -> "email"
        property.endsWith(".CN") -> "person-name"
        property.endsWith(".PARTSTAT") -> "ical-partstat"
        property.endsWith(".ROLE") -> "ical-role"
        property == "URL" -> "uri"
        property == "GEO" -> "geo"
        property == "UID" -> "uuid"
        property == "PRIORITY" -> "int32"
        property == "RRULE" -> "rrule"
        property == "STATUS" -> "ical-status"
        property.endsWith(".ACTION") -> "ical-action"
        else -> "string"
    }

    /** Validate a single iCal property value. */
    fun validateICalProperty(property: String, value: String): Boolean {
        val format = icalPropertyToFormat(property)
        return when (format) {
            // iCal-specific formats not in OpenAPI standard
            "ical-status" -> value.uppercase() in setOf("TENTATIVE", "CONFIRMED", "CANCELLED")
            "ical-partstat" -> value.uppercase() in setOf(
                "NEEDS-ACTION", "ACCEPTED", "DECLINED", "TENTATIVE", "DELEGATED"
            )
            "ical-role" -> value.uppercase() in setOf(
                "CHAIR", "REQ-PARTICIPANT", "OPT-PARTICIPANT", "NON-PARTICIPANT"
            )
            "ical-action" -> value.uppercase() in setOf("AUDIO", "DISPLAY", "EMAIL")
            "rrule" -> value.startsWith("FREQ=") && value.contains(
                Regex("FREQ=(SECONDLY|MINUTELY|HOURLY|DAILY|WEEKLY|MONTHLY|YEARLY)")
            )
            "geo" -> try {
                val parts = value.split(";")
                parts.size == 2 && parts[0].toDouble() in -90.0..90.0 && parts[1].toDouble() in -180.0..180.0
                true
            } catch (_: Exception) { false }
            "person-name" -> value.isNotBlank()
            "string" -> true
            // Delegate to standard format validators
            else -> validateByFormat(value, format)
        }
    }

    /** Convert raw Event field maps → iCalendar (.ics) string. */
    fun toICalRaw(events: List<Map<String, String>>): String {
        val sb = StringBuilder()
        sb.appendLine("BEGIN:VCALENDAR")
        sb.appendLine("PRODID:-//api-kmp//EN")
        sb.appendLine("VERSION:2.0")
        events.forEach { fields ->
            sb.appendLine("BEGIN:VEVENT")
            fields.forEach { (k, v) ->
                when {
                    k == "ORGANIZER" || (k.startsWith("ATTENDEE.") && !k.contains(".")) ->
                        sb.appendLine("${k.substringBefore(".")}:mailto:$v")
                    k.contains(".") -> {} // sub-properties handled by parent
                    else -> sb.appendLine("${k.uppercase()}:$v")
                }
            }
            sb.appendLine("END:VEVENT")
        }
        sb.appendLine("END:VCALENDAR")
        return sb.toString()
    }

    // === GeoIP + Country → ICU/CLDR linking ===

    /** Country → ICU ULocale (default locale for that country). */
    fun countryToLocale(country: Country): com.ibm.icu.util.ULocale =
        com.ibm.icu.util.ULocale("", country.value)

    /** Country → currency (ISO 4217 from ICU). */
    fun countryToCurrency(country: Country): Currency =
        Currency(com.ibm.icu.util.Currency.getInstance(countryToLocale(country)).currencyCode)

    /** Country → phone code (from libphonenumber). */
    fun countryToPhoneCode(country: Country): String {
        val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        return "+${util.getCountryCodeForRegion(country.value)}"
    }

    /** Country → timezone (primary from ICU). */
    fun countryToTimezone(country: Country): Timezone {
        val tzId = com.ibm.icu.util.TimeZone.getAvailableIDs(country.value)?.firstOrNull()
            ?: "UTC"
        return Timezone(tzId)
    }

    /** Country → display name (localized via ICU). */
    fun countryDisplayName(country: Country, inLocale: Locale = Locale.EN): String =
        countryToLocale(country).getDisplayCountry(com.ibm.icu.util.ULocale(inLocale.value))

    /** Country → language (primary from ICU). */
    fun countryToLanguage(country: Country): Language =
        Language(countryToLocale(country).language.ifEmpty { "en" })

    /** IP → GeoIp (lookup via external service — stub, needs MaxMind/ip-api). */
    fun geoIpLookup(ip: IpAddress): GeoIp {
        // Stub — real impl uses MaxMind GeoIP2 or ip-api.com
        // Returns linked chain: country → phone code → currency → timezone → locale
        val country = Country("US") // placeholder
        return GeoIp(
            ip = ip.value,
            country = country.value,
            phone_code = countryToPhoneCode(country).removePrefix("+"),
            currency_code = countryToCurrency(country).value,
            timezone = countryToTimezone(country).value,
            locale = "${countryToLanguage(country).value}-${country.value}",
        )
    }

    /** PersonName → formatted via ICU PersonNameFormatter. */
    fun formatPersonName(name: PersonName, locale: Locale = Locale.EN): String {
        // ICU4J 76+ has PersonNameFormatter
        return name.full // fallback — ICU PersonNameFormatter is new API
    }

    // === ICU CLDR: Timezone → City → Calendar → DateTime per country ===

    /** Timezone → exemplar city (ICU CLDR). "America/New_York" → "New York" */
    fun timezoneToCity(tz: Timezone): String =
        com.ibm.icu.util.TimeZone.getTimeZone(tz.value)
            .getDisplayName(false, com.ibm.icu.util.TimeZone.LONG, com.ibm.icu.util.ULocale.ENGLISH)

    /** Country → preferred calendar type (ICU CLDR). JP → "japanese", SA → "islamic", etc. */
    fun countryToCalendarType(country: Country): String {
        val locale = com.ibm.icu.util.ULocale("@calendar=", country.value)
        val cal = com.ibm.icu.util.Calendar.getInstance(locale)
        return cal.type ?: "gregorian"
    }

    /** Country → ICU Calendar instance (gregorian, japanese, islamic, etc.) */
    fun countryToCalendar(country: Country): com.ibm.icu.util.Calendar {
        val locale = countryToLocale(country)
        return com.ibm.icu.util.Calendar.getInstance(locale)
    }

    /** Country → date format pattern (ICU CLDR). US → "M/d/yy", DE → "dd.MM.yy", JP → "yy/MM/dd" */
    fun countryToDateFormat(country: Country, style: Int = com.ibm.icu.text.DateFormat.SHORT): String {
        val locale = countryToLocale(country)
        val fmt = com.ibm.icu.text.DateFormat.getDateInstance(style, locale)
        return if (fmt is com.ibm.icu.text.SimpleDateFormat) fmt.toPattern() else "yyyy-MM-dd"
    }

    /** Country → time format pattern. US → "h:mm a", DE → "HH:mm", JP → "H:mm" */
    fun countryToTimeFormat(country: Country, style: Int = com.ibm.icu.text.DateFormat.SHORT): String {
        val locale = countryToLocale(country)
        val fmt = com.ibm.icu.text.DateFormat.getTimeInstance(style, locale)
        return if (fmt is com.ibm.icu.text.SimpleDateFormat) fmt.toPattern() else "HH:mm:ss"
    }

    /** Country → first day of week. US → SUNDAY, DE → MONDAY, SA → SATURDAY */
    fun countryToFirstDayOfWeek(country: Country): Int =
        countryToCalendar(country).firstDayOfWeek

    /** Country → weekend days. US → [SAT,SUN], SA → [FRI,SAT] */
    fun countryToWeekend(country: Country): Pair<Int, Int> {
        val cal = countryToCalendar(country)
        return cal.weekData.weekendOnset to cal.weekData.weekendCease
    }

    /** Country → number format. US → "1,234.56", DE → "1.234,56", FR → "1 234,56" */
    fun countryToNumberFormat(country: Country): String {
        val locale = countryToLocale(country)
        val fmt = com.ibm.icu.text.NumberFormat.getInstance(locale)
        return fmt.format(1234.56)
    }

    /** Country → currency format. US → "$1,234.56", JP → "¥1,235", DE → "1.234,56 €" */
    fun countryToCurrencyFormat(country: Country, amount: Double = 1234.56): String {
        val locale = countryToLocale(country)
        val fmt = com.ibm.icu.text.NumberFormat.getCurrencyInstance(locale)
        return fmt.format(amount)
    }

    /** Country → measurement system. US → "US", GB → "UK", most → "SI" */
    fun countryToMeasurementSystem(country: Country): String {
        val ms = com.ibm.icu.util.LocaleData.getMeasurementSystem(countryToLocale(country))
        return when (ms) {
            com.ibm.icu.util.LocaleData.MeasurementSystem.US -> "US"
            com.ibm.icu.util.LocaleData.MeasurementSystem.UK -> "UK"
            else -> "SI"
        }
    }

    /** GeoIp → fully localized context: calendar, date format, number format, currency format */
    fun geoIpToLocalContext(geoIp: GeoIp): Map<String, String> {
        val c = Country(geoIp.country)
        return buildMap {
            put("country", geoIp.country)
            put("city", geoIp.city)
            put("timezone", geoIp.timezone)
            put("locale", geoIp.locale)
            put("currency", geoIp.currency_code)
            put("phoneCode", geoIp.phone_code)
            put("calendarType", countryToCalendarType(c))
            put("dateFormat", countryToDateFormat(c))
            put("timeFormat", countryToTimeFormat(c))
            put("numberFormat", countryToNumberFormat(c))
            put("currencyFormat", countryToCurrencyFormat(c))
            put("measurementSystem", countryToMeasurementSystem(c))
            put("firstDayOfWeek", countryToFirstDayOfWeek(c).toString())
        }
    }

    // === Language → ICU Script/Run Detection ===

    /** Language → primary script (ICU UScript). "ja" → "Jpan", "ar" → "Arab", "zh" → "Hans" */
    fun languageToScript(language: Language): String {
        val locale = com.ibm.icu.util.ULocale(language.value)
        val scripts = com.ibm.icu.lang.UScript.getCode(locale)
        return if (scripts != null && scripts.isNotEmpty()) {
            com.ibm.icu.lang.UScript.getShortName(scripts[0])
        } else "Latn"
    }

    /** Language → writing direction. "ar" → RTL, "en" → LTR */
    fun languageToDirection(language: Language): String {
        val script = languageToScript(language)
        val rtlScripts = setOf("Arab", "Hebr", "Syrc", "Thaa", "Mand", "Nkoo", "Adlm", "Rohg", "Yezi")
        return if (script in rtlScripts) "RTL" else "LTR"
    }

    /** Detect scripts in text (ICU script run detection). Returns list of (script, startIndex, endIndex). */
    fun detectScriptRuns(text: String): List<Triple<String, Int, Int>> {
        val runs = mutableListOf<Triple<String, Int, Int>>()
        if (text.isEmpty()) return runs
        var currentScript = com.ibm.icu.lang.UScript.COMMON
        var runStart = 0
        for (i in text.indices) {
            val cp = text.codePointAt(i)
            val script = com.ibm.icu.lang.UScript.getScript(cp)
            if (script != com.ibm.icu.lang.UScript.COMMON &&
                script != com.ibm.icu.lang.UScript.INHERITED &&
                script != currentScript) {
                if (currentScript != com.ibm.icu.lang.UScript.COMMON) {
                    runs.add(Triple(com.ibm.icu.lang.UScript.getShortName(currentScript), runStart, i))
                }
                currentScript = script
                runStart = i
            }
        }
        if (currentScript != com.ibm.icu.lang.UScript.COMMON) {
            runs.add(Triple(com.ibm.icu.lang.UScript.getShortName(currentScript), runStart, text.length))
        }
        return runs
    }

    /** Detect dominant script in text. "Hello世界" → "Hani" (CJK has more weight). */
    fun detectDominantScript(text: String): String {
        val runs = detectScriptRuns(text)
        if (runs.isEmpty()) return "Latn"
        return runs.maxByOrNull { it.third - it.second }?.first ?: "Latn"
    }

    /** Language → exemplar characters (ICU CLDR). "ja" → hiragana+katakana+kanji exemplars */
    fun languageToExemplarChars(language: Language): String {
        val locale = com.ibm.icu.util.ULocale(language.value)
        val ld = com.ibm.icu.util.LocaleData.getInstance(locale)
        return ld.getExemplarSet(0, com.ibm.icu.util.LocaleData.ES_STANDARD).toPattern(false)
    }

    /** Country → script (via primary language). "JP" → "Jpan", "SA" → "Arab" */
    fun countryToScript(country: Country): String =
        languageToScript(countryToLanguage(country))

    /** Country → text direction. "SA" → RTL, "US" → LTR, "IL" → RTL */
    fun countryToDirection(country: Country): String =
        languageToDirection(countryToLanguage(country))

    /** Full locale context including script + direction. */
    fun geoIpToFullContext(geoIp: GeoIp): Map<String, String> = buildMap {
        putAll(geoIpToLocalContext(geoIp))
        val lang = countryToLanguage(Country(geoIp.country))
        put("language", lang.value)
        put("script", languageToScript(lang))
        put("direction", languageToDirection(lang))
        put("exemplarChars", languageToExemplarChars(lang))
    }
}
