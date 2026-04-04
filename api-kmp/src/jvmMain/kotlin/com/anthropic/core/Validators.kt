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
}
