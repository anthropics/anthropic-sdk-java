package kotlinx.kmp.util

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.DigestAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.auth.providers.digest
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess

/**
 * CalDAV authentication scheme — maps 1:1 to OpenAPI/AsyncAPI `securitySchemes`.
 *
 * OpenAPI 3.1 securitySchemes → CalDavAuth:
 *   http: basic          → Basic
 *   http: digest         → Digest
 *   http: bearer         → Bearer
 *   oauth2               → OAuth2 (with refresh token flow)
 *   openIdConnect        → Oidc (discovery-based OAuth2)
 *   mutualTLS            → handled via HttpClient's TLS config (pass preconfigured client)
 *
 * AsyncAPI security scheme names map identically.
 *
 * For API-gen: the generator reads the spec's securitySchemes and emits
 * a CalDavAuth instance for the client config — no manual auth wiring.
 */
sealed class CalDavAuth {
    /** RFC 7617 Basic auth. Sent preemptively (safe over TLS only). */
    data class Basic(val username: String, val password: String) : CalDavAuth()

    /** RFC 7616 Digest auth. Plugin handles the challenge/response. */
    data class Digest(val username: String, val password: String) : CalDavAuth()

    /** RFC 6750 Bearer token. For OAuth2/OIDC access tokens. */
    data class Bearer(
        val accessToken: String,
        val refreshToken: String? = null,
        /** Called on 401 to refresh tokens. Returns (access, refresh). */
        val refresh: (suspend () -> Pair<String, String?>)? = null,
    ) : CalDavAuth()

    /**
     * OAuth2 (RFC 6749) — client_credentials or authorization_code flow.
     * Token endpoint + refresh handled by the caller via the refresh lambda.
     */
    data class OAuth2(
        val accessToken: String,
        val refreshToken: String,
        val refresh: suspend () -> Pair<String, String>,
    ) : CalDavAuth()

    /**
     * OIDC — discovers token endpoint from issuer's .well-known/openid-configuration.
     * Implementation: caller resolves the issuer, provides tokens + refresh logic.
     */
    data class Oidc(
        val issuer: String,
        val accessToken: String,
        val refreshToken: String,
        val refresh: suspend () -> Pair<String, String>,
    ) : CalDavAuth()

    /** No auth (public calendars). */
    object None : CalDavAuth()
}

/**
 * CalDAV client (RFC 4791) + ical4j + Bedework integration.
 *
 * Wire ICalEvent is the single source of truth. CalDAV transports RFC 5545
 * iCalendar (.ics) over WebDAV; ical4j parses/serializes .ics text.
 *
 * Supported CalDAV methods:
 *   PROPFIND   — discover calendar collections + properties
 *   REPORT     — calendar-query (range filter), calendar-multiget (batch fetch)
 *   MKCALENDAR — create a new calendar collection
 *   PUT        — create or update a single event (.ics body)
 *   GET        — fetch a single event (.ics body)
 *   DELETE     — remove an event
 *
 * Works against any RFC 4791 server: Bedework, Apple Calendar Server,
 * DAViCal, Radicale, Nextcloud Calendar, SabreDAV, Google (readonly),
 * Fastmail, etc.
 */
class CalDavClient(
    private val baseUrl: String,
    private val auth: CalDavAuth = CalDavAuth.None,
    httpClient: HttpClient? = null,
) {
    /** Convenience: Basic auth shortcut. */
    constructor(baseUrl: String, username: String, password: String) :
        this(baseUrl, CalDavAuth.Basic(username, password))

    /**
     * ktor HttpClient with Auth plugin — full OpenAPI/AsyncAPI securityScheme coverage.
     *
     * - RFC 7617 Basic   (plugin handles 401, credentials never cached in plaintext)
     * - RFC 7616 Digest  (MD5/SHA-256 challenge/response)
     * - RFC 6750 Bearer  (OAuth2/OIDC access token + refresh on 401)
     *
     * For mutualTLS, pass a pre-configured HttpClient with engine TLS config.
     */
    private val http: HttpClient = httpClient ?: HttpClient(io.ktor.client.engine.cio.CIO) {
        install(Auth) {
            when (val a = auth) {
                is CalDavAuth.Basic -> basic {
                    credentials { BasicAuthCredentials(a.username, a.password) }
                    sendWithoutRequest { true }
                }
                is CalDavAuth.Digest -> digest {
                    credentials { DigestAuthCredentials(a.username, a.password) }
                }
                is CalDavAuth.Bearer -> bearer {
                    loadTokens { BearerTokens(a.accessToken, a.refreshToken ?: "") }
                    a.refresh?.let { refresh ->
                        refreshTokens {
                            val (acc, ref) = refresh()
                            BearerTokens(acc, ref ?: "")
                        }
                    }
                    sendWithoutRequest { true }
                }
                is CalDavAuth.OAuth2 -> bearer {
                    loadTokens { BearerTokens(a.accessToken, a.refreshToken) }
                    refreshTokens {
                        val (acc, ref) = a.refresh()
                        BearerTokens(acc, ref)
                    }
                    sendWithoutRequest { true }
                }
                is CalDavAuth.Oidc -> bearer {
                    loadTokens { BearerTokens(a.accessToken, a.refreshToken) }
                    refreshTokens {
                        val (acc, ref) = a.refresh()
                        BearerTokens(acc, ref)
                    }
                    sendWithoutRequest { true }
                }
                CalDavAuth.None -> Unit
            }
        }
    }

    // === Discovery (RFC 6764 + RFC 5397) ===

    /**
     * Discover the user's calendar home set.
     * Returns the URL path for the current user's calendar collection.
     *
     * Flow (RFC 6764): baseUrl → current-user-principal → calendar-home-set
     */
    suspend fun discoverCalendarHome(): String? {
        // Step 1: PROPFIND on baseUrl for DAV:current-user-principal
        val principal = propfindProperty(
            url = baseUrl,
            depth = "0",
            property = "current-user-principal",
            namespace = "DAV:",
        ) ?: return null

        // Step 2: PROPFIND on principal for CALDAV:calendar-home-set
        return propfindProperty(
            url = resolveUrl(principal),
            depth = "0",
            property = "calendar-home-set",
            namespace = "urn:ietf:params:xml:ns:caldav",
        )?.let { resolveUrl(it) }
    }

    /**
     * List all calendar collections under the calendar home.
     * Returns list of (href, displayName) pairs.
     */
    suspend fun listCalendars(homeUrl: String = baseUrl): List<Pair<String, String>> {
        val body = """
            <?xml version="1.0" encoding="utf-8" ?>
            <D:propfind xmlns:D="DAV:" xmlns:C="urn:ietf:params:xml:ns:caldav">
              <D:prop>
                <D:displayname/>
                <D:resourcetype/>
                <C:supported-calendar-component-set/>
              </D:prop>
            </D:propfind>
        """.trimIndent()
        val response = propfind(homeUrl, depth = "1", body = body)
        return parseMultistatus(response)
            .filter { it.isCalendar }
            .map { it.href to it.displayName }
    }

    // === Event Operations ===

    /**
     * Fetch events in a date range via CALDAV:calendar-query REPORT.
     * Returns the parsed Wire ICalEvent list.
     */
    suspend fun getEventsInRange(
        calendarUrl: String,
        start: java.time.Instant,
        end: java.time.Instant,
    ): List<ICalEvent> {
        val startStr = formatCalDavTime(start)
        val endStr = formatCalDavTime(end)
        val body = """
            <?xml version="1.0" encoding="utf-8" ?>
            <C:calendar-query xmlns:D="DAV:" xmlns:C="urn:ietf:params:xml:ns:caldav">
              <D:prop>
                <D:getetag/>
                <C:calendar-data/>
              </D:prop>
              <C:filter>
                <C:comp-filter name="VCALENDAR">
                  <C:comp-filter name="VEVENT">
                    <C:time-range start="$startStr" end="$endStr"/>
                  </C:comp-filter>
                </C:comp-filter>
              </C:filter>
            </C:calendar-query>
        """.trimIndent()
        val response = report(calendarUrl, depth = "1", body = body)
        return extractIcsBlocks(response).flatMap { ics ->
            ICal4jBridge.parseToWire(ics)
        }
    }

    /** Fetch a single event by href (relative or absolute). */
    suspend fun getEvent(href: String): ICalEvent? {
        val response = http.get(resolveUrl(href)) {

            header("Accept", "text/calendar")
        }
        if (!response.status.isSuccess()) return null
        val ics = response.bodyAsText()
        return ICal4jBridge.parseToWire(ics).firstOrNull()
    }

    /**
     * Create or update an event (PUT). The event's UID drives the resource URL
     * unless href is given explicitly.
     */
    suspend fun putEvent(
        calendarUrl: String,
        event: ICalEvent,
        href: String? = null,
        ifMatch: String? = null,  // ETag for optimistic concurrency
    ): String {
        val uid = event.uid.ifBlank { "urn:uuid:${java.util.UUID.randomUUID()}" }
        val eventUrl = href ?: "${calendarUrl.trimEnd('/')}/${uid.substringAfterLast(":")}.ics"
        val ics = ICal4jBridge.wireToIcs(listOf(event.copy(uid = uid)))
        http.put(resolveUrl(eventUrl)) {

            header("Content-Type", "text/calendar; charset=utf-8")
            ifMatch?.let { header("If-Match", it) }
            setBody(ics)
        }
        return eventUrl
    }

    /** Delete an event by href. */
    suspend fun deleteEvent(href: String, ifMatch: String? = null): Boolean {
        val response = http.delete(resolveUrl(href)) {

            ifMatch?.let { header("If-Match", it) }
        }
        return response.status.isSuccess()
    }

    /** Create a new calendar collection (MKCALENDAR). */
    suspend fun createCalendar(
        calendarUrl: String,
        displayName: String,
        description: String = "",
    ): Boolean {
        val body = """
            <?xml version="1.0" encoding="utf-8" ?>
            <C:mkcalendar xmlns:D="DAV:" xmlns:C="urn:ietf:params:xml:ns:caldav">
              <D:set>
                <D:prop>
                  <D:displayname>$displayName</D:displayname>
                  <C:calendar-description>$description</C:calendar-description>
                </D:prop>
              </D:set>
            </C:mkcalendar>
        """.trimIndent()
        val response = http.request(resolveUrl(calendarUrl)) {

            method = HttpMethod.parse("MKCALENDAR")
            header("Content-Type", "application/xml; charset=utf-8")
            setBody(body)
        }
        return response.status.isSuccess()
    }

    // === Low-level WebDAV methods ===

    private suspend fun propfind(url: String, depth: String, body: String): String {
        val response = http.request(resolveUrl(url)) {

            method = HttpMethod.parse("PROPFIND")
            header("Depth", depth)
            header("Content-Type", "application/xml; charset=utf-8")
            setBody(body)
        }
        return response.bodyAsText()
    }

    private suspend fun report(url: String, depth: String, body: String): String {
        val response = http.request(resolveUrl(url)) {

            method = HttpMethod.parse("REPORT")
            header("Depth", depth)
            header("Content-Type", "application/xml; charset=utf-8")
            setBody(body)
        }
        return response.bodyAsText()
    }

    /** PROPFIND + extract a single property value from the multistatus XML. */
    private suspend fun propfindProperty(
        url: String,
        depth: String,
        property: String,
        namespace: String,
    ): String? {
        val body = """
            <?xml version="1.0" encoding="utf-8" ?>
            <D:propfind xmlns:D="DAV:" xmlns:C="$namespace">
              <D:prop>
                <C:$property/>
              </D:prop>
            </D:propfind>
        """.trimIndent()
        val xml = propfind(url, depth, body)
        // Extract <href> inside the property element
        val regex = Regex("<[^>]*$property[^>]*>.*?<(?:\\w+:)?href>([^<]+)</(?:\\w+:)?href>", RegexOption.DOT_MATCHES_ALL)
        return regex.find(xml)?.groupValues?.get(1)
    }

    // === Helpers ===

    private fun resolveUrl(path: String): String {
        if (path.startsWith("http://") || path.startsWith("https://")) return path
        val base = baseUrl.trimEnd('/')
        return if (path.startsWith("/")) {
            // Extract scheme + host from baseUrl
            val schemeEnd = base.indexOf("://")
            if (schemeEnd > 0) {
                val hostEnd = base.indexOf("/", schemeEnd + 3)
                val origin = if (hostEnd > 0) base.substring(0, hostEnd) else base
                "$origin$path"
            } else "$base$path"
        } else "$base/$path"
    }

    private fun formatCalDavTime(instant: java.time.Instant): String {
        val fmt = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'")
            .withZone(java.time.ZoneOffset.UTC)
        return fmt.format(instant)
    }

    /** Parse DAV:multistatus XML → list of resources. */
    private fun parseMultistatus(xml: String): List<CalDavResource> {
        val resources = mutableListOf<CalDavResource>()
        val responseBlocks = Regex(
            "<(?:\\w+:)?response>(.*?)</(?:\\w+:)?response>",
            RegexOption.DOT_MATCHES_ALL,
        ).findAll(xml)
        for (block in responseBlocks) {
            val content = block.groupValues[1]
            val href = Regex("<(?:\\w+:)?href>([^<]+)</(?:\\w+:)?href>")
                .find(content)?.groupValues?.get(1) ?: continue
            val displayName = Regex("<(?:\\w+:)?displayname>([^<]*)</(?:\\w+:)?displayname>")
                .find(content)?.groupValues?.get(1) ?: ""
            val isCalendar = content.contains(Regex("<(?:\\w+:)?calendar\\s*/?>"))
            val etag = Regex("<(?:\\w+:)?getetag>([^<]+)</(?:\\w+:)?getetag>")
                .find(content)?.groupValues?.get(1) ?: ""
            resources.add(CalDavResource(href, displayName, isCalendar, etag))
        }
        return resources
    }

    /** Extract <C:calendar-data> .ics blocks from a multistatus response. */
    private fun extractIcsBlocks(xml: String): List<String> {
        return Regex(
            "<(?:\\w+:)?calendar-data[^>]*>(.*?)</(?:\\w+:)?calendar-data>",
            RegexOption.DOT_MATCHES_ALL,
        ).findAll(xml)
            .map { it.groupValues[1].trim() }
            .map { unescapeXml(it) }
            .filter { it.contains("BEGIN:VCALENDAR") }
            .toList()
    }

    private fun unescapeXml(s: String): String = s
        .replace("&amp;", "&")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&quot;", "\"")
        .replace("&apos;", "'")

    data class CalDavResource(
        val href: String,
        val displayName: String,
        val isCalendar: Boolean,
        val etag: String,
    )
}

/**
 * ical4j ↔ Wire ICalEvent bridge. Wire is the source of truth; ical4j is
 * used for RFC 5545 text I/O (.ics parsing/serializing).
 */
object ICal4jBridge {

    /** Parse an .ics string (possibly multi-VEVENT) → Wire ICalEvent list. */
    @JvmStatic
    fun parseToWire(ics: String): List<ICalEvent> {
        // Reuse Validators.parseICal which returns List<Map<String,String>>,
        // then promote each map to a Wire ICalEvent via parseICalTyped (already in Validators.kt).
        return Validators.parseICalTyped(ics)
    }

    /** Serialize Wire ICalEvent list → .ics string via Validators.toICal (ical4j). */
    @JvmStatic
    fun wireToIcs(events: List<ICalEvent>): String = Validators.toICal(events)

    /** Convert a single ical4j VEvent → Wire ICalEvent via .ics round-trip. */
    @JvmStatic
    fun vEventToWire(vevent: net.fortuna.ical4j.model.component.VEvent): ICalEvent? {
        val cal = net.fortuna.ical4j.model.Calendar(
            net.fortuna.ical4j.model.ComponentList(listOf(vevent))
        )
        return parseToWire(cal.toString()).firstOrNull()
    }

    /** Convert Wire ICalEvent → ical4j VEvent via .ics round-trip. */
    @JvmStatic
    fun wireToVEvent(event: ICalEvent): net.fortuna.ical4j.model.component.VEvent? {
        val ics = wireToIcs(listOf(event))
        val cal = net.fortuna.ical4j.data.CalendarBuilder().build(ics.byteInputStream())
        return cal.getComponents<net.fortuna.ical4j.model.component.VEvent>(
            net.fortuna.ical4j.model.component.VEvent.VEVENT,
        ).firstOrNull()
    }
}

/**
 * Bedework-flavored CalDAV client — adds discovery helpers for Bedework
 * server paths (/ucaldav/, /bedework/).
 *
 * Bedework is an RFC 4791 server, so all CalDavClient operations work.
 * This class just adds convenience endpoints matching Bedework's URL scheme.
 */
class BedeworkCalDavClient(
    private val serverUrl: String,
    private val auth: CalDavAuth,
) {
    /** Convenience: Basic auth shortcut. */
    constructor(serverUrl: String, username: String, password: String) :
        this(serverUrl, CalDavAuth.Basic(username, password))

    private val rootUrl: String = if (serverUrl.contains("/ucaldav") || serverUrl.contains("/bedework"))
        serverUrl.trimEnd('/') else "${serverUrl.trimEnd('/')}/ucaldav"

    val client: CalDavClient = CalDavClient(baseUrl = rootUrl, auth = auth)

    private val username: String = when (auth) {
        is CalDavAuth.Basic -> auth.username
        is CalDavAuth.Digest -> auth.username
        else -> ""
    }

    /** Bedework principal URL for the authenticated user. */
    val principalUrl: String get() = "$rootUrl/principals/users/$username"

    /** Bedework calendar home for the user (tries discovery, falls back to conventional path). */
    suspend fun calendarHome(): String =
        client.discoverCalendarHome() ?: "$rootUrl/user/$username/calendar"
}
