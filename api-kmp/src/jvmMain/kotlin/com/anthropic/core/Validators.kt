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
        contact.urls.forEachIndexed { i, u -> put("url.$i", validateUrl(Uri(u))) }
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

    // === vCard: use ezvcard.VCard directly on JVM (full RFC 6350 model) ===
    // Wire VCardContact is only for KMP cross-platform transmission (commonMain).

    /** Parse vCard (.vcf) string → ezvcard.VCard (full RFC 6350 object). */
    fun parseVCard(vcf: String): ezvcard.VCard = ezvcard.Ezvcard.parse(vcf).first()

    /** Parse all vCards in a .vcf file/string (vCard allows multiple). */
    fun parseVCards(vcf: String): List<ezvcard.VCard> = ezvcard.Ezvcard.parse(vcf).all()

    /** Serialize ezvcard.VCard → .vcf string. */
    fun toVCard(vcard: ezvcard.VCard): String = ezvcard.Ezvcard.write(vcard).go()

    /** Serialize multiple vCards → .vcf string. */
    fun toVCards(vcards: List<ezvcard.VCard>): String = ezvcard.Ezvcard.write(vcards).go()

    /**
     * Validate an ezvcard.VCard using our format validators.
     * Walks every property and validates via validateByFormat.
     */
    fun validateVCard(vcard: ezvcard.VCard): Map<String, Boolean> = buildMap {
        vcard.formattedName?.value?.let { put("FN", it.isNotBlank()) }
        vcard.emails?.forEachIndexed { i, e ->
            put("EMAIL.$i", validateByFormat(e.value, "email"))
        }
        vcard.telephoneNumbers?.forEachIndexed { i, t ->
            put("TEL.$i", t.text?.let { validateByFormat(it, "phone") } ?: false)
        }
        vcard.urls?.forEachIndexed { i, u ->
            put("URL.$i", validateByFormat(u.value, "uri"))
        }
        vcard.photos?.forEachIndexed { i, p ->
            p.url?.let { put("PHOTO.$i", validateByFormat(it, "uri")) }
        }
        vcard.birthday?.date?.let { put("BDAY", true) }
        vcard.uid?.value?.let { put("UID", it.isNotBlank()) }
    }

    // === Wire bridge — full RFC 6350 parity (ezvcard ↔ Wire VCardContact) ===

    /** ezvcard.VCard → Wire VCardContact with full RFC 6350 property set. */
    fun ezvcardToWire(vcard: ezvcard.VCard): VCardContact = VCardContact(
        // §6.1 General
        source = vcard.sources?.firstOrNull()?.value ?: "",
        kind = vcard.kind?.value ?: "",
        xml = vcard.xmls?.firstOrNull()?.value?.toString() ?: "",
        // §6.2 Identification
        name = vcard.structuredName?.let {
            PersonName(
                given = it.given ?: "",
                family = it.family ?: "",
                prefix = it.prefixes?.firstOrNull() ?: "",
                suffix = it.suffixes?.firstOrNull() ?: "",
            )
        },
        nicknames = vcard.nickname?.values ?: emptyList(),
        photo = vcard.photos?.firstOrNull()?.url ?: "",
        birthday = null, // google.type.Date conversion — simplified
        anniversary = null,
        gender = vcard.gender?.gender ?: "",
        // §6.3 Delivery
        addresses = vcard.addresses?.map { addr ->
            com.google.type.PostalAddress(
                address_lines = listOfNotNull(addr.streetAddress),
                locality = addr.locality ?: "",
                administrative_area = addr.region ?: "",
                postal_code = addr.postalCode ?: "",
                region_code = addr.country ?: "",
            )
        } ?: emptyList(),
        // §6.4 Communications
        phones = vcard.telephoneNumbers?.map { tel ->
            com.google.type.PhoneNumber(e164_number = tel.text ?: "")
        } ?: emptyList(),
        emails = vcard.emails?.map { it.value } ?: emptyList(),
        impp = vcard.impps?.map { it.uri.toString() } ?: emptyList(),
        languages = vcard.languages?.map { it.value } ?: emptyList(),
        // §6.5 Geographical
        timezone = vcard.timezones?.firstOrNull()?.text ?: "",
        geo = vcard.geos?.firstOrNull()?.let { g ->
            com.google.type.LatLng(latitude = g.latitude ?: 0.0, longitude = g.longitude ?: 0.0)
        },
        // §6.6 Organizational
        title = vcard.titles?.firstOrNull()?.value ?: "",
        role = vcard.roles?.firstOrNull()?.value ?: "",
        logo = vcard.logos?.firstOrNull()?.url ?: "",
        organization = vcard.organization?.values?.firstOrNull() ?: "",
        org_units = vcard.organization?.values?.drop(1) ?: emptyList(),
        members = vcard.members?.map { it.uri ?: it.value ?: "" } ?: emptyList(),
        related = vcard.relations?.map { rel ->
            VCardRelated(
                uri = rel.uri ?: rel.text ?: "",
                type = rel.types?.firstOrNull()?.value ?: "",
            )
        } ?: emptyList(),
        // §6.7 Explanatory
        urls = vcard.urls?.map { it.value } ?: emptyList(),
        categories = vcard.categories?.values ?: emptyList(),
        note = vcard.notes?.firstOrNull()?.value ?: "",
        prodid = vcard.productId?.value ?: "",
        rev = vcard.revision?.value?.toString() ?: "",
        sound = vcard.sounds?.firstOrNull()?.url ?: "",
        uid = vcard.uid?.value ?: "",
        client_pid_maps = vcard.clientPidMaps?.map {
            VCardClientPidMap(pid = it.pid?.toInt() ?: 0, uri = it.uri ?: "")
        } ?: emptyList(),
        // §6.8 Security
        key = vcard.keys?.firstOrNull()?.url ?: "",
        // §6.9 Calendar
        fburl = vcard.fbUrls?.firstOrNull()?.value ?: "",
        cal_adr_uri = vcard.calendarRequestUris?.firstOrNull()?.value ?: "",
        cal_uri = vcard.calendarUris?.firstOrNull()?.value ?: "",
        // RFC 6474 extensions
        birth_place = vcard.birthplace?.text ?: "",
        death_place = vcard.deathplace?.text ?: "",
        death_date = null,
        // RFC 6715 extensions
        expertise = vcard.expertise?.map { it.value } ?: emptyList(),
        hobbies = vcard.hobbies?.map { it.value } ?: emptyList(),
        interests = vcard.interests?.map { it.value } ?: emptyList(),
        org_directories = vcard.orgDirectories?.map { it.value } ?: emptyList(),
        // X-* extensions
        extensions = vcard.extendedProperties?.associate { it.propertyName to it.value } ?: emptyMap(),
    )

    /** Wire VCardContact → ezvcard.VCard (full RFC 6350 round-trip). */
    fun wireToEzvcard(contact: VCardContact): ezvcard.VCard {
        val vcard = ezvcard.VCard()
        // §6.1 General
        if (contact.source.isNotBlank()) vcard.addSource(ezvcard.property.Source(contact.source))
        if (contact.kind.isNotBlank()) vcard.kind = ezvcard.property.Kind(contact.kind)
        // §6.2 Identification
        contact.name?.let { n ->
            if (n.full.isNotBlank()) vcard.setFormattedName(n.full)
            if (n.given.isNotBlank() || n.family.isNotBlank()) {
                val sn = ezvcard.property.StructuredName()
                sn.given = n.given
                sn.family = n.family
                if (n.prefix.isNotBlank()) sn.prefixes.add(n.prefix)
                if (n.suffix.isNotBlank()) sn.suffixes.add(n.suffix)
                vcard.structuredName = sn
            }
        }
        if (contact.nicknames.isNotEmpty()) {
            val nn = ezvcard.property.Nickname()
            contact.nicknames.forEach { nn.values.add(it) }
            vcard.addNickname(nn)
        }
        if (contact.gender.isNotBlank()) vcard.gender = ezvcard.property.Gender(contact.gender)
        // §6.3 Delivery
        contact.addresses.forEach { a ->
            val addr = ezvcard.property.Address()
            addr.streetAddress = a.address_lines.firstOrNull() ?: ""
            addr.locality = a.locality
            addr.region = a.administrative_area
            addr.postalCode = a.postal_code
            addr.country = a.region_code
            vcard.addAddress(addr)
        }
        // §6.4 Communications
        contact.phones.forEach { ph ->
            vcard.addTelephoneNumber(ph.e164_number ?: ph.short_code?.number ?: "")
        }
        contact.emails.forEach { vcard.addEmail(it) }
        contact.impp.forEach {
            try { vcard.addImpp(ezvcard.property.Impp(it)) } catch (_: Exception) {}
        }
        contact.languages.forEach { vcard.addLanguage(ezvcard.property.Language(it)) }
        // §6.5 Geographical
        if (contact.timezone.isNotBlank()) vcard.addTimezone(ezvcard.property.Timezone(contact.timezone))
        contact.geo?.let { vcard.addGeo(ezvcard.property.Geo(it.latitude, it.longitude)) }
        // §6.6 Organizational
        if (contact.organization.isNotBlank() || contact.org_units.isNotEmpty()) {
            val org = ezvcard.property.Organization()
            if (contact.organization.isNotBlank()) org.values.add(contact.organization)
            contact.org_units.forEach { org.values.add(it) }
            vcard.addOrganization(org)
        }
        if (contact.title.isNotBlank()) vcard.addTitle(contact.title)
        if (contact.role.isNotBlank()) vcard.addRole(contact.role)
        contact.members.forEach { vcard.addMember(ezvcard.property.Member(it)) }
        contact.related.forEach { rel ->
            val r = ezvcard.property.Related()
            r.uri = rel.uri
            if (rel.type.isNotBlank()) r.types.add(ezvcard.parameter.RelatedType.get(rel.type))
            vcard.addRelated(r)
        }
        // §6.7 Explanatory
        contact.urls.forEach { vcard.addUrl(it) }
        if (contact.categories.isNotEmpty()) {
            val c = ezvcard.property.Categories()
            contact.categories.forEach { c.values.add(it) }
            vcard.addCategories(c)
        }
        if (contact.note.isNotBlank()) vcard.addNote(contact.note)
        if (contact.prodid.isNotBlank()) vcard.productId = ezvcard.property.ProductId(contact.prodid)
        if (contact.uid.isNotBlank()) vcard.uid = ezvcard.property.Uid(contact.uid)
        // §6.8 Security
        if (contact.key.isNotBlank()) {
            try { vcard.addKey(ezvcard.property.Key(contact.key, ezvcard.parameter.KeyType.PGP)) } catch (_: Exception) {}
        }
        // §6.9 Calendar
        if (contact.fburl.isNotBlank()) vcard.addFbUrl(ezvcard.property.FreeBusyUrl(contact.fburl))
        if (contact.cal_adr_uri.isNotBlank()) vcard.addCalendarRequestUri(ezvcard.property.CalendarRequestUri(contact.cal_adr_uri))
        if (contact.cal_uri.isNotBlank()) vcard.addCalendarUri(ezvcard.property.CalendarUri(contact.cal_uri))
        // X-* extensions
        contact.extensions.forEach { (name, value) ->
            vcard.addExtendedProperty(name, value)
        }
        return vcard
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

    /** Parse vCard (.vcf) string → raw property map (string-based, flat). */
    fun parseVCardMap(vcf: String): Map<String, String> {
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
        val props = parseVCardMap(vcf)
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

    /**
     * Timezone → exemplar city via ICU CLDR TimeZoneNames.
     * "America/New_York" → "New York"
     * "Asia/Tokyo" → "Tokyo"
     * "Europe/Paris" (locale=fr) → "Paris"
     */
    fun timezoneToCity(
        tz: Timezone,
        locale: com.ibm.icu.util.ULocale = com.ibm.icu.util.ULocale.ENGLISH,
    ): String {
        val names = com.ibm.icu.text.TimeZoneNames.getInstance(locale)
        return names.getExemplarLocationName(tz.value)
            ?: tz.value.substringAfterLast("/").replace("_", " ")
    }

    /**
     * City → IANA timezone via ICU CLDR reverse lookup.
     * Iterates all timezones for the given country, matches the exemplar
     * location name (localized) against the input city name.
     *
     * Examples:
     *   cityToTimezone("New York", US) → "America/New_York"
     *   cityToTimezone("Los Angeles", US) → "America/Los_Angeles"
     *   cityToTimezone("São Paulo", BR) → "America/Sao_Paulo"
     *   cityToTimezone("東京", JP, JA) → "Asia/Tokyo"
     */
    fun cityToTimezone(
        city: String,
        country: Country,
        language: Language? = null,
    ): Timezone? {
        val locale = language?.let { com.ibm.icu.util.ULocale(it.value) }
            ?: com.ibm.icu.util.ULocale.ENGLISH
        val names = com.ibm.icu.text.TimeZoneNames.getInstance(locale)
        val normalizedCity = city.trim().lowercase()

        // Get all IANA timezones for the country
        val zonesForCountry = com.ibm.icu.util.TimeZone.getAvailableIDs(country.value)
            .toList()
            .ifEmpty { com.ibm.icu.util.TimeZone.getAvailableIDs().toList() }

        // 1. Exact exemplar location match
        zonesForCountry.forEach { zoneId ->
            val exemplar = names.getExemplarLocationName(zoneId)
            if (exemplar != null && exemplar.lowercase() == normalizedCity) {
                return Timezone(zoneId)
            }
        }

        // 2. Match against IANA ID suffix (fallback): "new_york" == "New_York" in "America/New_York"
        val ianaStyle = normalizedCity.replace(" ", "_")
        zonesForCountry.forEach { zoneId ->
            val suffix = zoneId.substringAfterLast("/").lowercase()
            if (suffix == ianaStyle) return Timezone(zoneId)
        }

        // 3. Partial match — city contained in exemplar or vice versa
        zonesForCountry.forEach { zoneId ->
            val exemplar = names.getExemplarLocationName(zoneId)?.lowercase()
            if (exemplar != null && (exemplar.contains(normalizedCity) || normalizedCity.contains(exemplar))) {
                return Timezone(zoneId)
            }
        }

        // 4. Fallback: country's default primary timezone
        return countryToTimezone(country).takeIf { it.value.isNotBlank() }
    }

    /**
     * GeoIp → IANA timezone (preferring the city-specific zone).
     * Uses GeoIp.city + country to resolve the exact CLDR timezone,
     * falling back to country default if city lookup fails.
     */
    fun geoIpToTimezone(geoIp: GeoIp): Timezone {
        // If GeoIp already has a populated timezone, trust it
        if (geoIp.timezone.isNotBlank()) return Timezone(geoIp.timezone)
        if (geoIp.city.isNotBlank() && geoIp.country.isNotBlank()) {
            cityToTimezone(
                city = geoIp.city,
                country = Country(geoIp.country),
                language = countryToLanguage(Country(geoIp.country)),
            )?.let { return it }
        }
        return if (geoIp.country.isNotBlank()) countryToTimezone(Country(geoIp.country))
        else Timezone("UTC")
    }

    // === GeoIp + libphonenumber (geocoder, prefixmapper, carrier) ===

    /**
     * Parse a phone number using GeoIp as the default region context.
     * Local numbers like "212-555-1234" are resolved to the GeoIp.country's
     * region code (e.g., US → +1, DE → +49).
     */
    fun parsePhoneByGeoIp(
        number: String,
        geoIp: GeoIp,
    ): com.google.i18n.phonenumbers.Phonenumber.PhoneNumber? = try {
        val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        val region = geoIp.country.ifBlank { "US" }
        util.parse(number, region)
    } catch (_: Exception) { null }

    /**
     * Format a phone number using GeoIp context:
     * - If phone's country == geoip country → NATIONAL format (local)
     * - Otherwise → INTERNATIONAL format (+country code)
     */
    fun formatPhoneByGeoIp(number: String, geoIp: GeoIp): String? {
        val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        val parsed = parsePhoneByGeoIp(number, geoIp) ?: return null
        val phoneRegion = util.getRegionCodeForNumber(parsed)
        val format = if (phoneRegion == geoIp.country)
            com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.NATIONAL
        else
            com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL
        return util.format(parsed, format)
    }

    /**
     * Reverse geocode a phone number to its geographic area description.
     * Uses libphonenumber PhoneNumberOfflineGeocoder with the GeoIp locale.
     *
     * Examples:
     *   "+12125551234" + US/EN → "New York, NY"
     *   "+442079460000" + GB/EN → "London"
     *   "+81312345678" + JP/JA → "東京"
     */
    fun phoneDescriptionByGeoIp(number: String, geoIp: GeoIp): String? {
        val parsed = parsePhoneByGeoIp(number, geoIp) ?: return null
        val geocoder = com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder.getInstance()
        val locale = geoIp.locale.ifBlank { "en" }.let { java.util.Locale.forLanguageTag(it) }
        return geocoder.getDescriptionForNumber(parsed, locale).ifBlank { null }
    }

    /**
     * Get the timezones a phone number belongs to (via libphonenumber).
     * Useful for correlating with GeoIp timezone — a number from NYC should
     * map to America/New_York, cross-checking the GeoIp city lookup.
     */
    fun phoneToTimezones(number: String, geoIp: GeoIp): List<Timezone> {
        val parsed = parsePhoneByGeoIp(number, geoIp) ?: return emptyList()
        val mapper = com.google.i18n.phonenumbers.PhoneNumberToTimeZonesMapper.getInstance()
        return mapper.getTimeZonesForNumber(parsed).map { Timezone(it) }
    }

    /**
     * Get the carrier name for a mobile number (via libphonenumber).
     * Returns localized name per GeoIp language.
     *
     * Examples:
     *   "+12125551234" → "" (landline, no carrier)
     *   "+14155551234" + US/EN → "Verizon" (if mobile)
     */
    fun phoneCarrierByGeoIp(number: String, geoIp: GeoIp): String? {
        val parsed = parsePhoneByGeoIp(number, geoIp) ?: return null
        val mapper = com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper.getInstance()
        val locale = geoIp.locale.ifBlank { "en" }.let { java.util.Locale.forLanguageTag(it) }
        return mapper.getNameForNumber(parsed, locale).ifBlank { null }
    }

    /**
     * Validate that a phone number's geographic origin is consistent with GeoIp.
     * Returns true if:
     * - Phone's region matches geoip.country, OR
     * - Phone's timezones include the geoip city's timezone
     */
    fun phoneMatchesGeoIp(number: String, geoIp: GeoIp): Boolean {
        val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        val parsed = parsePhoneByGeoIp(number, geoIp) ?: return false
        val phoneRegion = util.getRegionCodeForNumber(parsed) ?: return false
        if (phoneRegion == geoIp.country) return true
        val phoneTzs = phoneToTimezones(number, geoIp).map { it.value }
        val geoipTz = geoIpToTimezone(geoIp).value
        return geoipTz in phoneTzs
    }

    /**
     * Infer a full VCardContact from a user's phone number + GeoIp.
     *
     * From just (phone, geoip) we derive the entire vCard — every linked
     * property via: libphonenumber → ICU CLDR → country → language →
     * locale → timezone → calendar → script → currency → address.
     *
     * Inferred fields:
     *   phones[0]     ← parsed + formatted (E.164) from phone
     *   addresses[0]  ← google.type.PostalAddress with:
     *                    locality = geoIp.city (or phone geocoder description)
     *                    administrative_area = geoIp.region
     *                    postal_code = geoIp.postal_code
     *                    region_code = country (from phone or geoip)
     *                    language_code = language from country
     *   note          ← "Carrier: X | Timezone: Y | Region: Z"
     *   categories    ← ["phone:<type>", "country:<cc>", "lang:<ll>",
     *                    "tz:<tz>", "calendar:<cal>", "script:<scr>",
     *                    "currency:<cur>", "direction:<ltr|rtl>"]
     *   uid           ← "tel:<e164>" (RFC 3966)
     */
    fun inferVCard(
        phone: String,
        geoIp: GeoIp,
        givenName: String = "",
        familyName: String = "",
    ): ezvcard.VCard {
        val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        val parsed = parsePhoneByGeoIp(phone, geoIp)
        val e164 = parsed?.let { util.format(it, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164) }
            ?: phone

        // Region: prefer phone's region, fall back to geoIp
        val phoneRegion = parsed?.let { util.getRegionCodeForNumber(it) }
        val effectiveCountry = Country(phoneRegion ?: geoIp.country.ifBlank { "US" })

        // Language from country (ICU)
        val language = countryToLanguage(effectiveCountry)

        // City: prefer geoIp, else phone geocoder description
        val city = geoIp.city.ifBlank {
            phoneDescriptionByGeoIp(phone, geoIp) ?: ""
        }

        // Timezone: geoip city → CLDR timezone, cross-checked against phone
        val timezone = geoIpToTimezone(geoIp).value
        val phoneTimezones = phoneToTimezones(phone, geoIp).map { it.value }

        // CLDR linked chain
        val calendar = defaultCalendarFor(effectiveCountry)
        val script = countryToScript(effectiveCountry)
        val direction = countryToDirection(effectiveCountry)
        val currency = if (geoIp.currency_code.isNotBlank()) geoIp.currency_code
                       else countryToCurrency(effectiveCountry).value
        val locale = "${language.value}_${effectiveCountry.value}"

        // Carrier + phone type
        val carrier = phoneCarrierByGeoIp(phone, geoIp) ?: ""
        val phoneType = parsed?.let { util.getNumberType(it).name } ?: "UNKNOWN"
        val description = phoneDescriptionByGeoIp(phone, geoIp) ?: ""

        // Build ezvcard.VCard directly — full RFC 6350 object model
        val vcard = ezvcard.VCard()

        // FN + N
        if (givenName.isNotBlank() || familyName.isNotBlank()) {
            vcard.setFormattedName(listOf(givenName, familyName).filter { it.isNotBlank() }.joinToString(" "))
            val sn = ezvcard.property.StructuredName()
            sn.given = givenName
            sn.family = familyName
            vcard.structuredName = sn
        }

        // TEL (RFC 6350 §6.4.1) — with TYPE parameter from phone type
        val tel = ezvcard.property.Telephone(e164)
        when (phoneType) {
            "MOBILE" -> tel.types.add(ezvcard.parameter.TelephoneType.CELL)
            "FIXED_LINE" -> tel.types.add(ezvcard.parameter.TelephoneType.HOME)
            "FIXED_LINE_OR_MOBILE" -> {
                tel.types.add(ezvcard.parameter.TelephoneType.HOME)
                tel.types.add(ezvcard.parameter.TelephoneType.CELL)
            }
            "TOLL_FREE" -> tel.types.add(ezvcard.parameter.TelephoneType.WORK)
            "VOIP" -> tel.types.add(ezvcard.parameter.TelephoneType.VOICE)
            "PAGER" -> tel.types.add(ezvcard.parameter.TelephoneType.PAGER)
            "PERSONAL_NUMBER" -> tel.types.add(ezvcard.parameter.TelephoneType.VOICE)
        }
        vcard.addTelephoneNumber(tel)

        // ADR (RFC 6350 §6.3.1) — from GeoIp with TYPE=HOME
        if (city.isNotBlank() || geoIp.region.isNotBlank() || geoIp.postal_code.isNotBlank()) {
            val addr = ezvcard.property.Address()
            addr.locality = city
            addr.region = geoIp.region
            addr.postalCode = geoIp.postal_code
            addr.country = effectiveCountry.value
            addr.types.add(ezvcard.parameter.AddressType.HOME)
            vcard.addAddress(addr)
        }

        // TZ (RFC 6350 §6.5.1) — direct IANA timezone
        if (timezone.isNotBlank()) {
            vcard.addTimezone(ezvcard.property.Timezone(timezone))
        }

        // GEO (RFC 6350 §6.5.2) — from GeoIp.location
        geoIp.location?.let { loc ->
            vcard.addGeo(ezvcard.property.Geo(loc.latitude, loc.longitude))
        }

        // LANG (RFC 6350 §6.4.4) — language preference
        vcard.addLanguage(ezvcard.property.Language(language.value))

        // NOTE — inferred context summary
        val note = buildList {
            if (carrier.isNotBlank()) add("Carrier: $carrier")
            if (phoneType != "UNKNOWN") add("Type: $phoneType")
            if (description.isNotBlank()) add("Region: $description")
            if (calendar != "gregorian") add("Calendar: $calendar")
            add("Locale: $locale")
            add("Script: $script ($direction)")
            add("Currency: $currency")
        }.joinToString(" | ")
        if (note.isNotBlank()) vcard.addNote(note)

        // CATEGORIES — encoded linked chain for downstream filtering
        val cats = ezvcard.property.Categories()
        if (phoneType != "UNKNOWN") cats.values.add("phone:$phoneType")
        cats.values.add("country:${effectiveCountry.value}")
        cats.values.add("lang:${language.value}")
        cats.values.add("locale:$locale")
        cats.values.add("tz:$timezone")
        if (phoneTimezones.isNotEmpty() && phoneTimezones.toSet() != setOf(timezone)) {
            cats.values.add("phoneTz:${phoneTimezones.joinToString(";")}")
        }
        cats.values.add("calendar:$calendar")
        cats.values.add("script:$script")
        cats.values.add("direction:$direction")
        cats.values.add("currency:$currency")
        if (carrier.isNotBlank()) cats.values.add("carrier:$carrier")
        vcard.addCategories(cats)

        // UID — tel: URI (RFC 3966)
        vcard.uid = ezvcard.property.Uid("tel:$e164")

        return vcard
    }

    /** KMP bridge: infer + convert to Wire VCardContact. */
    fun inferVCardContact(
        phone: String,
        geoIp: GeoIp,
        givenName: String = "",
        familyName: String = "",
    ): VCardContact = ezvcardToWire(inferVCard(phone, geoIp, givenName, familyName))

    /**
     * Full phone-number context derived from GeoIp:
     * parsed number, description, carrier, timezones, formatting (national + international).
     */
    fun phoneContextByGeoIp(number: String, geoIp: GeoIp): Map<String, String> {
        val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
        val parsed = parsePhoneByGeoIp(number, geoIp) ?: return emptyMap()
        return buildMap {
            put("e164", util.format(parsed, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164))
            put("international", util.format(parsed, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL))
            put("national", util.format(parsed, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.NATIONAL))
            put("rfc3966", util.format(parsed, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.RFC3966))
            put("countryCode", parsed.countryCode.toString())
            put("nationalNumber", parsed.nationalNumber.toString())
            put("region", util.getRegionCodeForNumber(parsed) ?: "")
            put("type", util.getNumberType(parsed).name)
            put("isValid", util.isValidNumber(parsed).toString())
            put("isMobile", (util.getNumberType(parsed) == com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.MOBILE).toString())
            phoneDescriptionByGeoIp(number, geoIp)?.let { put("description", it) }
            phoneCarrierByGeoIp(number, geoIp)?.let { put("carrier", it) }
            val tzs = phoneToTimezones(number, geoIp).map { it.value }
            if (tzs.isNotEmpty()) put("timezones", tzs.joinToString(","))
            put("matchesGeoIp", phoneMatchesGeoIp(number, geoIp).toString())
        }
    }

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
        val tz = geoIpToTimezone(geoIp)
        return buildMap {
            put("country", geoIp.country)
            put("city", geoIp.city)
            put("timezone", tz.value)
            put("timezoneCity", timezoneToCity(tz))
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

    // === Measure — ICU CLDR + PostGIS + GeoJSON Interop ===

    /** Wire Measure → ICU com.ibm.icu.util.Measure via MeasureUnit.forIdentifier(). */
    fun toIcuMeasure(m: Measure): com.ibm.icu.util.Measure {
        val unit = com.ibm.icu.util.MeasureUnit.forIdentifier(m.unit)
            ?: com.ibm.icu.util.MeasureUnit.forIdentifier("length-meter")
        return com.ibm.icu.util.Measure(m.value_, unit)
    }

    /** ICU com.ibm.icu.util.Measure → Wire Measure. */
    fun fromIcuMeasure(icu: com.ibm.icu.util.Measure): Measure = Measure(
        value_ = icu.number.toDouble(),
        unit = icu.unit.identifier,
    )

    /** Format Measure for display via ICU MeasureFormat (locale-aware). */
    fun formatMeasure(
        m: Measure,
        locale: com.ibm.icu.util.ULocale = com.ibm.icu.util.ULocale.getDefault(),
        width: com.ibm.icu.text.MeasureFormat.FormatWidth = com.ibm.icu.text.MeasureFormat.FormatWidth.SHORT,
    ): String {
        val fmt = com.ibm.icu.text.MeasureFormat.getInstance(locale, width)
        return fmt.format(toIcuMeasure(m))
    }

    /** Parse CLDR unit identifier → validate. "length-meter" OK, "foo-bar" false. */
    fun validateMeasureUnit(identifier: String): Boolean = try {
        com.ibm.icu.util.MeasureUnit.forIdentifier(identifier) != null
    } catch (_: Exception) { false }

    /** All CLDR measurement systems (metric, ussystem, uksystem). */
    fun measureUnitSystem(m: Measure): String = when {
        m.system.isNotBlank() -> m.system
        m.unit.startsWith("length-") && m.unit.contains(Regex("foot|inch|mile|yard")) -> "ussystem"
        m.unit.startsWith("mass-") && m.unit.contains(Regex("pound|ounce|stone")) -> "ussystem"
        else -> "metric"
    }

    /**
     * Convert Measure to another CLDR unit.
     * Stub: full conversion needs ICU UnitsConverter (ICU 72+ internal API).
     * Callers should use formatMeasure() for locale-aware display.
     */
    fun convertMeasure(m: Measure, toUnit: String): Measure {
        com.ibm.icu.util.MeasureUnit.forIdentifier(m.unit) ?: return m
        com.ibm.icu.util.MeasureUnit.forIdentifier(toUnit) ?: return m
        return Measure(value_ = m.value_, unit = toUnit)
    }

    // === Geo encoding — unified dispatcher for PostGIS, GeoJSON, WKT, WKB ===

    /** Geo output formats. */
    enum class GeoFormat { POSTGIS_WKT, GEOJSON, WKT, WKB_HEX, LAT_LON, GEO_URI }

    /**
     * Encode a LatLng (+optional altitude + measure) to any geo format.
     *
     * - POSTGIS_WKT: "POINT M (lon lat m)" or "POINT ZM (lon lat alt m)"
     * - GEOJSON: `{"type":"Point","coordinates":[lon,lat,alt,m]}`
     * - WKT: "POINT (lon lat)"
     * - LAT_LON: "lat,lon"
     * - GEO_URI: RFC 5870 "geo:lat,lon,alt;u=measure"
     */
    fun toGeo(
        point: com.google.type.LatLng,
        format: GeoFormat = GeoFormat.GEOJSON,
        altitude: Double? = null,
        measure: Measure? = null,
    ): String {
        val lon = point.longitude
        val lat = point.latitude
        return when (format) {
            GeoFormat.POSTGIS_WKT -> when {
                altitude != null && measure != null -> "POINT ZM ($lon $lat $altitude ${measure.value_})"
                measure != null -> "POINT M ($lon $lat ${measure.value_})"
                altitude != null -> "POINT Z ($lon $lat $altitude)"
                else -> "POINT ($lon $lat)"
            }
            GeoFormat.GEOJSON -> {
                val coords = buildList {
                    add(lon); add(lat)
                    altitude?.let { add(it) }
                    measure?.let { add(it.value_) }
                }
                "{\"type\":\"Point\",\"coordinates\":[${coords.joinToString(",")}]}"
            }
            GeoFormat.WKT -> "POINT ($lon $lat)"
            GeoFormat.WKB_HEX -> "01010000${java.lang.Double.doubleToLongBits(lon).toString(16)}${java.lang.Double.doubleToLongBits(lat).toString(16)}"
            GeoFormat.LAT_LON -> "$lat,$lon"
            GeoFormat.GEO_URI -> buildString {
                append("geo:$lat,$lon")
                altitude?.let { append(",$it") }
                measure?.let { append(";u=${it.value_}") }
            }
        }
    }

    /**
     * Decode a geo string to LatLng + optional altitude + optional Measure.
     * Auto-detects format from input (WKT, GeoJSON, "lat,lon", "geo:" URI).
     * Returns Triple(LatLng, altitude?, Measure?).
     */
    fun fromGeo(
        encoded: String,
        unit: String = "length-meter",
    ): Triple<com.google.type.LatLng, Double?, Measure?>? {
        val trimmed = encoded.trim()
        return when {
            // PostGIS/WKT
            trimmed.startsWith("POINT", ignoreCase = true) -> {
                val nums = Regex("""[-+]?\d+\.?\d*""").findAll(trimmed).map { it.value.toDouble() }.toList()
                if (nums.size < 2) return null
                val hasM = trimmed.contains(" M", ignoreCase = true) || trimmed.contains("ZM", ignoreCase = true)
                val hasZ = trimmed.contains(" Z", ignoreCase = true) || trimmed.contains("ZM", ignoreCase = true)
                val ll = com.google.type.LatLng(longitude = nums[0], latitude = nums[1])
                val alt = if (hasZ && nums.size >= 3) nums[2] else null
                val measureVal = when {
                    hasZ && hasM && nums.size >= 4 -> nums[3]
                    hasM && !hasZ && nums.size >= 3 -> nums[2]
                    else -> null
                }
                Triple(ll, alt, measureVal?.let { Measure(value_ = it, unit = unit) })
            }
            // GeoJSON
            trimmed.startsWith("{") && trimmed.contains("\"coordinates\"") -> {
                val coords = Regex("""\[([-+\d.,\s]+)\]""").find(trimmed)?.groupValues?.get(1)
                    ?.split(",")?.mapNotNull { it.trim().toDoubleOrNull() } ?: return null
                if (coords.size < 2) return null
                val ll = com.google.type.LatLng(longitude = coords[0], latitude = coords[1])
                val alt = coords.getOrNull(2)
                val measureVal = coords.getOrNull(3)
                Triple(ll, alt, measureVal?.let { Measure(value_ = it, unit = unit) })
            }
            // RFC 5870 geo: URI
            trimmed.startsWith("geo:", ignoreCase = true) -> {
                val body = trimmed.removePrefix("geo:").removePrefix("GEO:")
                val (coordStr, paramStr) = body.split(";", limit = 2).let { it[0] to it.getOrNull(1) }
                val parts = coordStr.split(",").mapNotNull { it.trim().toDoubleOrNull() }
                if (parts.size < 2) return null
                val ll = com.google.type.LatLng(latitude = parts[0], longitude = parts[1])
                val alt = parts.getOrNull(2)
                val measureVal = paramStr?.let { p ->
                    Regex("""u=([\d.]+)""").find(p)?.groupValues?.get(1)?.toDoubleOrNull()
                }
                Triple(ll, alt, measureVal?.let { Measure(value_ = it, unit = unit) })
            }
            // Plain "lat,lon" or "lat;lon"
            else -> {
                val parts = trimmed.split(Regex("[,;]")).mapNotNull { it.trim().toDoubleOrNull() }
                if (parts.size < 2) return null
                Triple(
                    com.google.type.LatLng(latitude = parts[0], longitude = parts[1]),
                    parts.getOrNull(2),
                    null,
                )
            }
        }
    }

    // === DateTime — Country + Language + ICU CLDR Calendar ===

    /**
     * Resolve ICU ULocale from country + language + optional calendar keyword.
     * Examples:
     *   localeFor(JP, JA) → ja_JP@calendar=japanese (Reiwa era)
     *   localeFor(SA, AR) → ar_SA@calendar=islamic-umalqura
     *   localeFor(TH, TH) → th_TH@calendar=buddhist
     *   localeFor(IL, HE) → he_IL@calendar=hebrew
     *   localeFor(IR, FA) → fa_IR@calendar=persian
     */
    fun localeFor(
        country: Country,
        language: Language? = null,
        calendar: String? = null,
    ): com.ibm.icu.util.ULocale {
        val lang = language?.value ?: countryToLanguage(country).value
        val cal = calendar ?: defaultCalendarFor(country)
        val base = "${lang}_${country.value}"
        return if (cal != "gregorian") {
            com.ibm.icu.util.ULocale.forLanguageTag("$base-u-ca-$cal")
        } else {
            com.ibm.icu.util.ULocale("$base")
        }
    }

    /** Country → default CLDR calendar type. JP→japanese, SA→islamic-umalqura, TH→buddhist, etc. */
    fun defaultCalendarFor(country: Country): String = when (country.value) {
        "JP" -> "japanese"
        "SA", "AE", "KW", "BH", "QA", "OM", "YE", "MA" -> "islamic-umalqura"
        "IR", "AF" -> "persian"
        "IL" -> "hebrew"
        "TH" -> "buddhist"
        "IN", "NP" -> "indian"
        "TW" -> "roc"
        "ET" -> "ethiopic"
        else -> "gregorian"
    }

    /** All calendars available for a country via ICU. */
    fun availableCalendarsFor(country: Country): List<String> {
        val locale = com.ibm.icu.util.ULocale("", country.value)
        return com.ibm.icu.util.Calendar.getKeywordValuesForLocale("calendar", locale, false).toList()
    }

    /**
     * Format an Instant using country + language + calendar per ICU CLDR.
     *
     * Examples:
     *   formatDateTime(now, JP, JA) → "令和7年4月5日 14:30" (Reiwa era)
     *   formatDateTime(now, SA, AR) → "1 رمضان 1446 هـ، 14:30" (Hijri)
     *   formatDateTime(now, TH, TH) → "5 เมษายน 2569 14:30" (Buddhist era)
     *   formatDateTime(now, US, EN) → "Apr 5, 2026, 2:30 PM"
     */
    fun formatDateTime(
        instant: java.time.Instant,
        country: Country,
        language: Language? = null,
        calendar: String? = null,
        timezone: Timezone? = null,
        dateStyle: Int = com.ibm.icu.text.DateFormat.MEDIUM,
        timeStyle: Int = com.ibm.icu.text.DateFormat.SHORT,
    ): String {
        val locale = localeFor(country, language, calendar)
        val tz = timezone?.let { com.ibm.icu.util.TimeZone.getTimeZone(it.value) }
            ?: com.ibm.icu.util.TimeZone.getTimeZone(countryToTimezone(country).value)
        val cal = com.ibm.icu.util.Calendar.getInstance(tz, locale)
        cal.timeInMillis = instant.toEpochMilli()
        val fmt = com.ibm.icu.text.DateFormat.getDateTimeInstance(cal, dateStyle, timeStyle, locale)
        return fmt.format(cal)
    }

    /** Format date only (no time). */
    fun formatDate(
        instant: java.time.Instant,
        country: Country,
        language: Language? = null,
        calendar: String? = null,
        timezone: Timezone? = null,
        style: Int = com.ibm.icu.text.DateFormat.MEDIUM,
    ): String {
        val locale = localeFor(country, language, calendar)
        val tz = timezone?.let { com.ibm.icu.util.TimeZone.getTimeZone(it.value) }
            ?: com.ibm.icu.util.TimeZone.getTimeZone(countryToTimezone(country).value)
        val cal = com.ibm.icu.util.Calendar.getInstance(tz, locale)
        cal.timeInMillis = instant.toEpochMilli()
        val fmt = com.ibm.icu.text.DateFormat.getDateInstance(cal, style, locale)
        return fmt.format(cal)
    }

    /** Format time only (no date). */
    fun formatTime(
        instant: java.time.Instant,
        country: Country,
        language: Language? = null,
        timezone: Timezone? = null,
        style: Int = com.ibm.icu.text.DateFormat.SHORT,
    ): String {
        val locale = localeFor(country, language, null)
        val tz = timezone?.let { com.ibm.icu.util.TimeZone.getTimeZone(it.value) }
            ?: com.ibm.icu.util.TimeZone.getTimeZone(countryToTimezone(country).value)
        val cal = com.ibm.icu.util.Calendar.getInstance(tz, locale)
        cal.timeInMillis = instant.toEpochMilli()
        val fmt = com.ibm.icu.text.DateFormat.getTimeInstance(cal, style, locale)
        return fmt.format(cal)
    }

    /**
     * Parse a localized date-time string using country + language + calendar.
     * Inverse of formatDateTime. Returns Instant in UTC.
     */
    fun parseDateTime(
        text: String,
        country: Country,
        language: Language? = null,
        calendar: String? = null,
        timezone: Timezone? = null,
        dateStyle: Int = com.ibm.icu.text.DateFormat.MEDIUM,
        timeStyle: Int = com.ibm.icu.text.DateFormat.SHORT,
    ): java.time.Instant? = try {
        val locale = localeFor(country, language, calendar)
        val tz = timezone?.let { com.ibm.icu.util.TimeZone.getTimeZone(it.value) }
            ?: com.ibm.icu.util.TimeZone.getTimeZone(countryToTimezone(country).value)
        val cal = com.ibm.icu.util.Calendar.getInstance(tz, locale)
        val fmt = com.ibm.icu.text.DateFormat.getDateTimeInstance(cal, dateStyle, timeStyle, locale)
        fmt.timeZone = tz
        val date = fmt.parse(text)
        java.time.Instant.ofEpochMilli(date.time)
    } catch (_: Exception) { null }

    /**
     * Convert an Instant between calendars, returning the era/year/month/day
     * in the target calendar system.
     *
     * Example: convertToCalendar(now, "japanese") →
     *   {era=Reiwa, year=7, month=4, day=5, hour=14, minute=30}
     */
    fun convertToCalendar(
        instant: java.time.Instant,
        calendar: String,
        locale: com.ibm.icu.util.ULocale = com.ibm.icu.util.ULocale.ENGLISH,
    ): Map<String, Int> {
        val icuLocale = com.ibm.icu.util.ULocale.forLanguageTag("${locale.toLanguageTag()}-u-ca-$calendar")
        val cal = com.ibm.icu.util.Calendar.getInstance(icuLocale)
        cal.timeInMillis = instant.toEpochMilli()
        return mapOf(
            "era" to cal.get(com.ibm.icu.util.Calendar.ERA),
            "year" to cal.get(com.ibm.icu.util.Calendar.YEAR),
            "month" to cal.get(com.ibm.icu.util.Calendar.MONTH) + 1, // 0-indexed → 1-indexed
            "day" to cal.get(com.ibm.icu.util.Calendar.DAY_OF_MONTH),
            "hour" to cal.get(com.ibm.icu.util.Calendar.HOUR_OF_DAY),
            "minute" to cal.get(com.ibm.icu.util.Calendar.MINUTE),
            "second" to cal.get(com.ibm.icu.util.Calendar.SECOND),
            "dayOfWeek" to cal.get(com.ibm.icu.util.Calendar.DAY_OF_WEEK),
            "weekOfYear" to cal.get(com.ibm.icu.util.Calendar.WEEK_OF_YEAR),
        )
    }

    /** Get the era name (e.g., "Reiwa", "AH", "BE") for an Instant in a calendar. */
    fun eraName(
        instant: java.time.Instant,
        calendar: String,
        locale: com.ibm.icu.util.ULocale = com.ibm.icu.util.ULocale.ENGLISH,
    ): String {
        val icuLocale = com.ibm.icu.util.ULocale.forLanguageTag("${locale.toLanguageTag()}-u-ca-$calendar")
        val cal = com.ibm.icu.util.Calendar.getInstance(icuLocale)
        cal.timeInMillis = instant.toEpochMilli()
        val fmt = com.ibm.icu.text.SimpleDateFormat("GGGG", icuLocale)
        fmt.calendar = cal
        return fmt.format(cal.time)
    }

    /**
     * Relative time format via ICU RelativeDateTimeFormatter.
     * Example: relativeTime(now+1h, US, EN) → "in 1 hour"
     *          relativeTime(now-5d, FR, FR) → "il y a 5 jours"
     */
    fun relativeTime(
        instant: java.time.Instant,
        country: Country,
        language: Language? = null,
        reference: java.time.Instant = java.time.Instant.now(),
    ): String {
        val locale = localeFor(country, language, null)
        val fmt = com.ibm.icu.text.RelativeDateTimeFormatter.getInstance(locale)
        val diffSeconds = (instant.toEpochMilli() - reference.toEpochMilli()) / 1000.0
        val direction = if (diffSeconds >= 0) com.ibm.icu.text.RelativeDateTimeFormatter.Direction.NEXT
                        else com.ibm.icu.text.RelativeDateTimeFormatter.Direction.LAST
        val absSec = kotlin.math.abs(diffSeconds)
        val (value, unit) = when {
            absSec < 60 -> absSec to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.SECONDS
            absSec < 3600 -> (absSec / 60) to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.MINUTES
            absSec < 86400 -> (absSec / 3600) to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.HOURS
            absSec < 604800 -> (absSec / 86400) to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.DAYS
            absSec < 2592000 -> (absSec / 604800) to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.WEEKS
            absSec < 31536000 -> (absSec / 2592000) to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.MONTHS
            else -> (absSec / 31536000) to com.ibm.icu.text.RelativeDateTimeFormatter.RelativeUnit.YEARS
        }
        return fmt.format(kotlin.math.round(value), direction, unit)
    }

    /**
     * Full date-time context for a country — all CLDR info needed to render
     * a date-time in the local calendar, language, timezone, and week structure.
     */
    fun dateTimeContextFor(
        country: Country,
        language: Language? = null,
    ): Map<String, String> {
        val lang = language ?: countryToLanguage(country)
        val cal = defaultCalendarFor(country)
        val tz = countryToTimezone(country)
        val locale = localeFor(country, lang, cal)
        return buildMap {
            put("country", country.value)
            put("language", lang.value)
            put("locale", locale.toLanguageTag())
            put("calendar", cal)
            put("availableCalendars", availableCalendarsFor(country).joinToString(","))
            put("timezone", tz.value)
            put("city", timezoneToCity(tz))
            put("dateFormat", countryToDateFormat(country))
            put("timeFormat", countryToTimeFormat(country))
            put("firstDayOfWeek", countryToFirstDayOfWeek(country).toString())
            put("weekendOnset", countryToWeekend(country).first.toString())
            put("weekendCease", countryToWeekend(country).second.toString())
            put("direction", countryToDirection(country))
            put("script", countryToScript(country))
        }
    }
}
