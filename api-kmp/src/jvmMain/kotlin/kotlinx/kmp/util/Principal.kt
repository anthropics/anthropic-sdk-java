package kotlinx.kmp.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * VCardContact is the user principal.
 *
 * A [Principal] binds together everything that identifies and authenticates
 * a user, all rooted in a single RFC 6350 Wire VCardContact:
 *
 *   ┌──────────────────────────────────────────────────────────┐
 *   │  Wire VCardContact  (commonMain, KMP, single truth)      │
 *   └──────────────────────────────────────────────────────────┘
 *         │         │         │         │         │       │
 *         ▼         ▼         ▼         ▼         ▼       ▼
 *       GeoIp   Phone(E164)  Email   JabberID   OIDC   OTP
 *       (IP→    (libphone    (RFC   (XEP-0054  (RFC   (RFC
 *       CLDR)   number)      5321)   →Smack)   6749)  6238)
 *                                      │         │
 *                                      ▼         ▼
 *                                  XMPP IM   Bearer Token
 *                                 (SSE Flow) (CalDAV, API)
 *
 * Building a principal:
 *   val p = Principals.fromOidc(idToken, clientIp, phoneNumber)
 *
 * Using it:
 *   p.vcard                    — the RFC 6350 truth
 *   p.jid                      — XMPP jabber_id
 *   p.smackClient(password)    — XEP-0054 client (XMPP TCP connection)
 *   p.imStream()               — Flow<ImMessage> via XMPP carbons or SSE
 *   p.calDavClient()           — CalDavClient with OIDC bearer auth
 *   p.requestOtp() / verify()  — RFC 6238 TOTP / SMS OTP
 *   p.authSession.accessToken  — OIDC access token for API calls
 */
class Principal(
    /** RFC 6350 vCard — the single source of truth for user identity. */
    val vcard: VCardContact,
    /** OIDC / OAuth2 session with tokens + refresh. */
    val authSession: AuthSession,
    /** Cached GeoIp (provenance of the principal's location-derived fields). */
    val geoIp: GeoIp,
) {
    /** Primary E.164 phone number (libphonenumber validated). */
    val phone: String?
        get() = vcard.phones.firstOrNull()?.e164_number?.takeIf { it.isNotBlank() }

    /** Primary email (RFC 5321). */
    val email: String?
        get() = vcard.emails.firstOrNull()?.takeIf { it.isNotBlank() }

    /** XMPP Jabber ID (XEP-0054 JABBERID) — drives Smack client creation. */
    val jid: String?
        get() = vcard.jabber_id.takeIf { it.isNotBlank() }

    /** Country (ISO 3166-1 alpha-2) from vcard address or geoip. */
    val country: Country
        get() = vcard.addresses.firstOrNull()?.region_code
            ?.takeIf { it.isNotBlank() }?.let { Country(it) }
            ?: Country(geoIp.country.ifBlank { "US" })

    /** Primary language (ISO 639-1). */
    val language: Language
        get() = vcard.languages.firstOrNull()
            ?.takeIf { it.isNotBlank() }?.let { Language(it) }
            ?: Validators.countryToLanguage(country)

    /** Locale (BCP 47). */
    val locale: String
        get() = geoIp.locale.ifBlank { "${language.value}_${country.value}" }

    /** IANA timezone from vcard or geoip (CLDR city-resolved). */
    val timezone: Timezone
        get() = vcard.timezone.takeIf { it.isNotBlank() }?.let { Timezone(it) }
            ?: Validators.geoIpToTimezone(geoIp)

    /** CLDR preferred calendar (japanese/islamic/buddhist/persian/hebrew/gregorian). */
    val calendar: String
        get() = Validators.defaultCalendarFor(country)

    /** UID (RFC 3966 tel: or URN). */
    val uid: String
        get() = vcard.uid.ifBlank { phone?.let { "tel:$it" } ?: "urn:anonymous" }

    /**
     * Create a Smack XMPP client from the principal's JID.
     *
     * The JID is the XMPP bare form `localpart@domain`. The returned
     * connection is NOT connected — caller calls `connect()` + `login()`.
     *
     * When the principal has an OAuth2 session, prefer SASL OAuthBearer:
     * pass `password = authSession.accessToken` and configure the
     * XMPPTCPConnection to use `SASLMechanism.OAUTHBEARER` upstream.
     */
    fun smackClient(password: String): org.jivesoftware.smack.tcp.XMPPTCPConnection {
        val bareJid = requireNotNull(jid) { "Principal has no jabber_id — cannot create XMPP client" }
        val (local, domain) = bareJid.split("@", limit = 2).let {
            require(it.size == 2) { "Invalid JID: $bareJid (expected localpart@domain)" }
            it[0] to it[1]
        }
        val config = org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.builder()
            .setUsernameAndPassword(local, password)
            .setXmppDomain(org.jxmpp.jid.impl.JidCreate.domainBareFrom(domain))
            .build()
        return org.jivesoftware.smack.tcp.XMPPTCPConnection(config)
    }

    /**
     * Create a CalDavClient using the principal's OIDC bearer token.
     * Uses the CalDAV endpoint from vcard.cal_uri, falling back to the
     * standard .well-known/caldav discovery from the OIDC issuer.
     */
    fun calDavClient(caldavUrl: String? = null): CalDavClient {
        val url = caldavUrl
            ?: vcard.cal_uri.takeIf { it.isNotBlank() }
            ?: "${authSession.issuer.trimEnd('/')}/.well-known/caldav"
        return CalDavClient(
            baseUrl = url,
            auth = CalDavAuth.Bearer(
                accessToken = authSession.accessToken,
                refreshToken = authSession.refreshToken,
                refresh = authSession.refresh,
            ),
        )
    }

    // === IM stream (SSE) ===

    private val imBroadcast = MutableSharedFlow<ImMessage>(extraBufferCapacity = 128)

    /**
     * Subscribe to inbound IM messages for this principal.
     *
     * Sources (merged):
     * - XMPP carbons (XEP-0280) via Smack — if jid is set
     * - SSE endpoint — if authSession.issuer supports /messages/sse
     *
     * Emit via [publishIm] from the transport layer (XMPP listener / SSE parser).
     */
    fun imStream(): Flow<ImMessage> = imBroadcast.asSharedFlow()

    /** Internal: transport layer pushes received IMs into the stream. */
    suspend fun publishIm(message: ImMessage) = imBroadcast.emit(message)

    // === OTP (RFC 6238 TOTP / SMS) ===

    /**
     * Request an OTP to be sent to the principal's phone (SMS or voice).
     * Returns the transaction ID to use with [verifyOtp].
     *
     * Caller provides the sender (Twilio, AWS SNS, etc.). This method just
     * generates a TOTP secret + challenge.
     */
    fun generateTotpSecret(): String = java.util.UUID.randomUUID().toString().replace("-", "").take(16).uppercase()

    /**
     * Verify a RFC 6238 TOTP code (6-digit, 30s window, SHA-1 by default).
     * For full TOTP implementation, caller should use a library like
     * `dev.samstevens.totp` or `com.eatthepath:java-otp`.
     */
    fun verifyTotp(secret: String, code: String, windowSeconds: Long = 30): Boolean {
        // Minimal HMAC-based TOTP (RFC 6238) — for production use a library
        return try {
            val timeStep = System.currentTimeMillis() / 1000 / windowSeconds
            val expected = generateTotp(secret, timeStep)
            expected == code
        } catch (_: Exception) { false }
    }

    private fun generateTotp(secret: String, timeStep: Long): String {
        val key = secret.toByteArray()
        val data = ByteArray(8).also {
            var t = timeStep
            for (i in 7 downTo 0) { it[i] = (t and 0xFF).toByte(); t = t shr 8 }
        }
        val mac = javax.crypto.Mac.getInstance("HmacSHA1")
        mac.init(javax.crypto.spec.SecretKeySpec(key, "HmacSHA1"))
        val hash = mac.doFinal(data)
        val offset = hash[hash.size - 1].toInt() and 0xF
        val binary = ((hash[offset].toInt() and 0x7F) shl 24) or
                     ((hash[offset + 1].toInt() and 0xFF) shl 16) or
                     ((hash[offset + 2].toInt() and 0xFF) shl 8) or
                     (hash[offset + 3].toInt() and 0xFF)
        return (binary % 1_000_000).toString().padStart(6, '0')
    }
}

/**
 * Instant message — common shape for XMPP chat messages, SSE notifications,
 * and carbon-copied messages (XEP-0280).
 */
data class ImMessage(
    val from: String,              // JID or tel:/mailto: URI
    val to: String,
    val body: String,
    val type: Type = Type.CHAT,
    val timestamp: java.time.Instant = java.time.Instant.now(),
    val threadId: String? = null,
    /** Optional vCard of the sender (if resolved from roster). */
    val fromVcard: VCardContact? = null,
) {
    enum class Type { CHAT, GROUPCHAT, HEADLINE, NORMAL, ERROR, TYPING, RECEIPT }
}

/**
 * OAuth2 / OIDC session with refresh support.
 * Mirrors the RFC 6749 token endpoint response + RFC 7519 ID token claims.
 */
data class AuthSession(
    /** OIDC issuer (e.g. https://accounts.google.com). */
    val issuer: String,
    /** OAuth2 access token (Bearer). */
    val accessToken: String,
    /** OAuth2 refresh token. */
    val refreshToken: String,
    /** OIDC ID token (JWT, contains identity claims). */
    val idToken: String? = null,
    /** Token expiry (Instant). */
    val expiresAt: java.time.Instant? = null,
    /** Scopes granted. */
    val scopes: Set<String> = emptySet(),
    /** Refresh lambda — called by ktor Auth plugin on 401. */
    val refresh: suspend () -> Pair<String, String> = { accessToken to refreshToken },
) {
    fun isExpired(): Boolean = expiresAt?.let { java.time.Instant.now().isAfter(it) } ?: false
}

/**
 * Factory for building [Principal]s from various sources.
 */
object Principals {

    /**
     * Build a principal from an OIDC ID token (JWT) + client IP + optional phone.
     *
     * Flow:
     * 1. Decode JWT → extract standard OIDC claims (sub, name, email,
     *    phone_number, locale, zoneinfo, birthdate, picture, address)
     * 2. GeoIp lookup from clientIp → country/city/timezone/currency
     * 3. inferVCardContact(phone, geoIp) populates linked CLDR context
     * 4. Merge JWT claims into the VCardContact
     * 5. Build AuthSession from token response
     */
    fun fromOidc(
        issuer: String,
        accessToken: String,
        refreshToken: String,
        idToken: String,
        clientIp: String,
        phoneNumber: String? = null,
        refresh: (suspend () -> Pair<String, String>)? = null,
    ): Principal {
        val claims = decodeJwtClaims(idToken)
        val geoIp = Validators.geoIpLookup(IpAddress(clientIp))

        // Start from inferred vCard (phone + geoIp → full CLDR context)
        val phone = phoneNumber
            ?: claims["phone_number"]
            ?: ""
        val baseContact = if (phone.isNotBlank()) {
            Validators.inferVCardContact(
                phone = phone,
                geoIp = geoIp,
                givenName = claims["given_name"] ?: "",
                familyName = claims["family_name"] ?: "",
            )
        } else {
            VCardContact(
                name = PersonName(
                    given = claims["given_name"] ?: "",
                    family = claims["family_name"] ?: "",
                ),
            )
        }

        // Merge JWT standard claims (OIDC §5.1) into the vCard
        val vcard = baseContact.copy(
            emails = baseContact.emails + listOfNotNull(claims["email"])
                .filter { it.isNotBlank() }.distinct(),
            jabber_id = claims["jabber_id"] ?: claims["xmpp"] ?: baseContact.jabber_id,
            photo = claims["picture"] ?: baseContact.photo,
            uid = claims["sub"]?.let { "oidc:$issuer#$it" } ?: baseContact.uid,
            languages = if (baseContact.languages.isEmpty() && claims["locale"] != null)
                listOf(claims["locale"]!!.substringBefore("-").substringBefore("_"))
            else baseContact.languages,
            timezone = claims["zoneinfo"]?.takeIf { it.isNotBlank() } ?: baseContact.timezone,
        )

        val session = AuthSession(
            issuer = issuer,
            accessToken = accessToken,
            refreshToken = refreshToken,
            idToken = idToken,
            refresh = refresh ?: { accessToken to refreshToken },
        )

        return Principal(vcard, session, geoIp)
    }

    /**
     * Decode JWT claims (base64url payload) without signature verification.
     * Caller should verify via the issuer's JWKS before trusting claims.
     */
    private fun decodeJwtClaims(jwt: String): Map<String, String> {
        val parts = jwt.split(".")
        if (parts.size < 2) return emptyMap()
        val payload = String(java.util.Base64.getUrlDecoder().decode(parts[1]))
        // Naive JSON → Map<String,String> (values quoted strings only)
        return Regex("""\"([^\"]+)\"\s*:\s*\"([^\"]*)\"""")
            .findAll(payload)
            .associate { it.groupValues[1] to it.groupValues[2] }
    }

    /**
     * Build a principal from phone + clientIp only (no OIDC session).
     * Useful for OTP-based authentication flows — the principal is
     * established by phone verification, then upgraded to OIDC later.
     */
    fun fromPhone(
        phoneNumber: String,
        clientIp: String,
        givenName: String = "",
        familyName: String = "",
    ): Principal {
        val geoIp = Validators.geoIpLookup(IpAddress(clientIp))
        val vcard = Validators.inferVCardContact(phoneNumber, geoIp, givenName, familyName)
        val session = AuthSession(
            issuer = "phone:$phoneNumber",
            accessToken = "",
            refreshToken = "",
        )
        return Principal(vcard, session, geoIp)
    }
}
