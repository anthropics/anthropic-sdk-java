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
}

