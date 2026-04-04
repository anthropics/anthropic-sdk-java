package com.anthropic.core

import org.apache.commons.validator.routines.EmailValidator
import org.apache.commons.validator.routines.InetAddressValidator
import org.apache.commons.validator.routines.UrlValidator

/**
 * JVM validators using stable libs:
 *   apache commons-validator: email, URL, IP
 *   google libphonenumber: phone numbers
 */
object Validators {

    /** Validate email via apache commons-validator. */
    fun validateEmail(email: Email): Boolean =
        EmailValidator.getInstance().isValid(email.value)

    /** Validate URL via apache commons-validator. */
    fun validateUrl(uri: Uri): Boolean =
        UrlValidator.getInstance().isValid(uri.value)

    /** Validate IP via apache commons-validator. */
    fun validateIp(ip: IpAddress): Boolean =
        InetAddressValidator.getInstance().isValid(ip.value)

    /** Validate phone via google libphonenumber. */
    fun validatePhone(phone: Phone, defaultRegion: String = "US"): Boolean =
        try {
            val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            val parsed = util.parse(phone.value, defaultRegion)
            util.isValidNumber(parsed)
        } catch (_: Exception) { false }

    /** Format phone to E.164 via google libphonenumber. */
    fun formatPhoneE164(phone: Phone, defaultRegion: String = "US"): Phone =
        try {
            val util = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            val parsed = util.parse(phone.value, defaultRegion)
            Phone(util.format(parsed, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164))
        } catch (_: Exception) { phone }
}
