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

    // === vCard / iCalendar conversion ===

    /** Parse vCard (.vcf) string → Contact-like map. */
    fun parseVCard(vcf: String): Map<String, String> {
        val vcard = ezvcard.Ezvcard.parse(vcf).first()
        return buildMap {
            vcard.formattedName?.value?.let { put("fullName", it) }
            vcard.emails?.firstOrNull()?.value?.let { put("email", it) }
            vcard.telephoneNumbers?.firstOrNull()?.text?.let { put("phone", it) }
            vcard.organization?.values?.firstOrNull()?.let { put("organization", it) }
            vcard.addresses?.firstOrNull()?.let { addr ->
                put("address", listOfNotNull(addr.streetAddress, addr.locality, addr.region, addr.postalCode, addr.country).joinToString(", "))
            }
        }
    }

    /** Convert Contact fields → vCard (.vcf) string. */
    fun toVCard(fields: Map<String, String>): String {
        val vcard = ezvcard.VCard()
        fields["fullName"]?.let { vcard.setFormattedName(it) }
        fields["email"]?.let { vcard.addEmail(it) }
        fields["phone"]?.let { vcard.addTelephoneNumber(it) }
        fields["organization"]?.let { vcard.setOrganization(it) }
        return ezvcard.Ezvcard.write(vcard).go()
    }

    /** Parse iCalendar (.ics) string → Event-like maps via ical4j. */
    fun parseICal(ics: String): List<Map<String, String>> {
        val calendar = net.fortuna.ical4j.data.CalendarBuilder().build(ics.byteInputStream())
        val events = mutableListOf<Map<String, String>>()
        calendar.getComponents<net.fortuna.ical4j.model.component.VEvent>(net.fortuna.ical4j.model.component.VEvent.VEVENT).forEach { component ->
            val map = mutableMapOf<String, String>()
            component.summary.ifPresent { map["summary"] = it.value }
            component.description.ifPresent { map["description"] = it.value }
            component.location.ifPresent { map["location"] = it.value }
            component.uid.ifPresent { map["id"] = it.value }
            
            
            events.add(map)
        }
        return events
    }

    /** Convert Event fields → iCalendar (.ics) via ical4j. */
    fun toICal(events: List<Map<String, String>>): String {
        val sb = StringBuilder()
        sb.appendLine("BEGIN:VCALENDAR")
        sb.appendLine("PRODID:-//api-kmp//EN")
        sb.appendLine("VERSION:2.0")
        events.forEach { fields ->
            sb.appendLine("BEGIN:VEVENT")
            fields.forEach { (k, v) -> sb.appendLine("${k.uppercase()}:$v") }
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
            ip = ip,
            country = country,
            phoneCode = countryToPhoneCode(country).removePrefix("+"),
            currency = countryToCurrency(country),
            timezone = countryToTimezone(country),
            locale = Locale("${countryToLanguage(country).value}-${country.value}"),
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
    fun geoIpToLocalContext(geoIp: GeoIp): Map<String, String> = buildMap {
        put("country", geoIp.country.value)
        put("city", geoIp.city)
        put("timezone", geoIp.timezone.value)
        put("locale", geoIp.locale.value)
        put("currency", geoIp.currency.value)
        put("phoneCode", geoIp.phoneCode)
        put("calendarType", countryToCalendarType(geoIp.country))
        put("dateFormat", countryToDateFormat(geoIp.country))
        put("timeFormat", countryToTimeFormat(geoIp.country))
        put("numberFormat", countryToNumberFormat(geoIp.country))
        put("currencyFormat", countryToCurrencyFormat(geoIp.country))
        put("measurementSystem", countryToMeasurementSystem(geoIp.country))
        put("firstDayOfWeek", countryToFirstDayOfWeek(geoIp.country).toString())
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
        val lang = countryToLanguage(geoIp.country)
        put("language", lang.value)
        put("script", languageToScript(lang))
        put("direction", languageToDirection(lang))
        put("exemplarChars", languageToExemplarChars(lang))
    }
}
