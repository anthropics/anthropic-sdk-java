package com.anthropic.credentials

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.internal.config.CachedTokenData
import com.anthropic.internal.config.CredentialsCache
import com.anthropic.internal.credentials.UserOAuthCredentials
import java.io.ByteArrayInputStream
import java.nio.file.Path
import java.time.Instant
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class UserOAuthCredentialsTest {

    // ------- Externally-rotated mode (clientId == null) -------

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun externallyRotated_returnsTokenVerbatim(async: Boolean, @TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        val expiresAt = Instant.now().plusSeconds(3600).epochSecond
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "sidecar-token",
                expiresAt = expiresAt,
                refreshToken = null,
            )
        )

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = null,
                httpClient = null,
                jsonMapper = jsonMapper(),
            )

        val token = creds.get("https://api.anthropic.com", false, async)

        assertThat(token.token).isEqualTo("sidecar-token")
        assertThat(token.expiresAt).isEqualTo(Instant.ofEpochSecond(expiresAt))
    }

    @Test
    fun externallyRotated_returnsExpiredTokenVerbatim(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        val expiresAt = Instant.now().minusSeconds(60).epochSecond
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired-sidecar-token",
                expiresAt = expiresAt,
                refreshToken = null,
            )
        )

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = null,
                httpClient = null,
                jsonMapper = jsonMapper(),
            )

        // Sidecar owns rotation; the SDK must not second-guess an expired value.
        val token = creds.get("https://api.anthropic.com", false)

        assertThat(token.token).isEqualTo("expired-sidecar-token")
    }

    @Test
    fun externallyRotated_neverCallsHttpClient(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "t",
                expiresAt = Instant.now().plusSeconds(60).epochSecond,
                refreshToken = null,
            )
        )
        var called = false
        val nonCallingClient = MockHttpClient { _ ->
            called = true
            error("HTTP client must not be used in externally-rotated mode")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = null,
                httpClient = nonCallingClient,
                jsonMapper = jsonMapper(),
            )

        creds.get("https://api.anthropic.com", false)

        assertThat(called).isFalse()
    }

    @Test
    fun missingFile_throws(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("missing.json")
        val cache = CredentialsCache(credFile, jsonMapper())

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = null,
                httpClient = null,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
            .isInstanceOf(AnthropicException::class.java)
            .hasMessageContaining("missing or unreadable")
    }

    @Test
    fun missingAccessTokenField_throws(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("partial.json")
        credFile.toFile().writeText("""{"type": "access_token"}""")
        val cache = CredentialsCache(credFile, jsonMapper())

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = null,
                httpClient = null,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
            .isInstanceOf(AnthropicException::class.java)
            .hasMessageContaining("missing 'access_token'")
    }

    @Test
    fun clientIdWithoutHttpClient_failsInit(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())

        assertThatThrownBy {
                UserOAuthCredentials(
                    credentialsCache = cache,
                    profileName = "default",
                    clientId = "client_abc",
                    httpClient = null,
                    jsonMapper = jsonMapper(),
                )
            }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("client_id configured but no HttpClient")
    }

    // ------- SDK-refresh mode (clientId set) -------

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun refreshMode_freshTokenReturnsCached_noHttp(async: Boolean, @TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        val expiresAt = Instant.now().plusSeconds(3600).epochSecond
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "fresh",
                expiresAt = expiresAt,
                refreshToken = "rt",
            )
        )
        var called = false
        val client = MockHttpClient { _ ->
            called = true
            error("should not be called")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        val token = creds.get("https://api.anthropic.com", false, async)

        assertThat(token.token).isEqualTo("fresh")
        assertThat(called).isFalse()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun refreshMode_expiredTokenTriggersRefresh(async: Boolean, @TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt-old",
            )
        )
        val client = MockHttpClient { _ ->
            createResponse(
                200,
                """{"access_token": "refreshed", "expires_in": 3600, "refresh_token": "rt-new"}""",
            )
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        val token = creds.get("https://api.anthropic.com", false, async)

        assertThat(token.token).isEqualTo("refreshed")
        assertThat(token.expiresAt).isNotNull
        val roundTrip = cache.readRecord()
        assertThat(roundTrip!!.accessToken).isEqualTo("refreshed")
        assertThat(roundTrip.refreshToken).isEqualTo("rt-new")
    }

    @Test
    fun refreshMode_forceRefreshAlwaysRefreshes(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "still-fresh",
                expiresAt = Instant.now().plusSeconds(3600).epochSecond,
                refreshToken = "rt",
            )
        )
        val client = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "force-refreshed", "expires_in": 3600}""")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        val token = creds.get("https://api.anthropic.com", true)

        assertThat(token.token).isEqualTo("force-refreshed")
    }

    @Test
    fun refreshMode_missingRefreshTokenThrows(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "at",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = null,
            )
        )
        var called = false
        val client = MockHttpClient { _ ->
            called = true
            error("should not be called")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
            .isInstanceOf(AnthropicException::class.java)
            .hasMessageContaining("missing 'refresh_token'")
        assertThat(called).isFalse()
    }

    @Test
    fun refreshMode_serverOmitsRefreshToken_preservesOld(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt-kept",
            )
        )
        val client = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "refreshed", "expires_in": 3600}""")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        creds.get("https://api.anthropic.com", false)

        val roundTrip = cache.readRecord()
        assertThat(roundTrip!!.refreshToken).isEqualTo("rt-kept")
    }

    @Test
    fun refreshMode_serverOmitsExpiresIn_usesDefault(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt",
            )
        )
        val before = Instant.now()
        val client = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "refreshed"}""")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        val token = creds.get("https://api.anthropic.com", false)

        assertThat(token.expiresAt).isNotNull
        assertThat(token.expiresAt!!.epochSecond)
            .isBetween(
                before.plusSeconds(3600).epochSecond,
                Instant.now().plusSeconds(3600).epochSecond,
            )
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun refreshMode_non2xxThrowsUnexpectedStatusCodeException(
        async: Boolean,
        @TempDir tempDir: Path,
    ) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt",
            )
        )
        val client = MockHttpClient { _ -> createResponse(401, """{"error": "invalid_grant"}""") }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        if (async) {
            assertThatThrownBy { creds.getAsync("https://api.anthropic.com", false).get() }
                .hasCauseInstanceOf(com.anthropic.errors.UnexpectedStatusCodeException::class.java)
        } else {
            assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
                .isInstanceOf(com.anthropic.errors.UnexpectedStatusCodeException::class.java)
        }
    }

    @Test
    fun refreshMode_2xxMissingAccessTokenThrows(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt",
            )
        )
        val client = MockHttpClient { _ -> createResponse(200, """{"expires_in": 3600}""") }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
            .isInstanceOf(com.anthropic.errors.UnexpectedStatusCodeException::class.java)
    }

    @Test
    fun refreshMode_2xxWhitespaceAccessTokenThrows(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt",
            )
        )
        val client = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "   ", "expires_in": 3600}""")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
            .isInstanceOf(com.anthropic.errors.UnexpectedStatusCodeException::class.java)
    }

    @Test
    fun refreshMode_httpRequestShape(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val cache = CredentialsCache(credFile, jsonMapper())
        cache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt-abc",
            )
        )
        var captured: HttpRequest? = null
        val client = MockHttpClient { req ->
            captured = req
            createResponse(200, """{"access_token": "x", "expires_in": 3600}""")
        }

        val creds =
            UserOAuthCredentials(
                credentialsCache = cache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        creds.get("https://api.anthropic.com", false)

        assertThat(captured).isNotNull
        assertThat(captured!!.method).isEqualTo(com.anthropic.core.http.HttpMethod.POST)
        assertThat(captured!!.pathSegments).containsExactly("v1", "oauth", "token")
        assertThat(captured!!.headers.values("Content-Type")).contains("application/json")
        val beta = captured!!.headers.values("anthropic-beta").joinToString(",")
        assertThat(beta).isEqualTo("oauth-2025-04-20")
        assertThat(beta).doesNotContain("oidc-federation-2026-04-01")

        val body = extractBody(captured!!)
        val parsed = jsonMapper().readValue(body, Map::class.java)
        assertThat(parsed["grant_type"]).isEqualTo("refresh_token")
        assertThat(parsed["refresh_token"]).isEqualTo("rt-abc")
        assertThat(parsed["client_id"]).isEqualTo("client_abc")
    }

    @Test
    fun refreshMode_writeBackFailurePropagates(@TempDir tempDir: Path) {
        val credFile = tempDir.resolve("creds.json")
        val realCache = CredentialsCache(credFile, jsonMapper())
        realCache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = "expired",
                expiresAt = Instant.now().minusSeconds(60).epochSecond,
                refreshToken = "rt",
            )
        )
        val failingCache =
            object : CredentialsCache(credFile, jsonMapper()) {
                override fun writeRecord(record: CachedTokenData) {
                    throw java.io.IOException("simulated disk failure")
                }
            }

        val client = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "x", "expires_in": 3600}""")
        }
        val creds =
            UserOAuthCredentials(
                credentialsCache = failingCache,
                profileName = "default",
                clientId = "client_abc",
                httpClient = client,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { creds.get("https://api.anthropic.com", false) }
            .isInstanceOf(java.io.IOException::class.java)
            .hasMessageContaining("simulated disk failure")
    }

    // ------- Test helpers -------

    private fun UserOAuthCredentials.get(baseUrl: String, forceRefresh: Boolean, async: Boolean) =
        if (async) getAsync(baseUrl, forceRefresh).get() else get(baseUrl, forceRefresh)

    private fun extractBody(request: HttpRequest): String {
        val body = request.body ?: return ""
        val outputStream = java.io.ByteArrayOutputStream()
        body.writeTo(outputStream)
        return outputStream.toString("UTF-8")
    }

    private fun createResponse(statusCode: Int, body: String): HttpResponse {
        return object : HttpResponse {
            override fun statusCode() = statusCode

            override fun headers() = com.anthropic.core.http.Headers.builder().build()

            override fun body() = ByteArrayInputStream(body.toByteArray())

            override fun close() {}
        }
    }

    private class MockHttpClient(private val syncHandler: (HttpRequest) -> HttpResponse) :
        HttpClient {
        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
            syncHandler(request)

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> = CompletableFuture.completedFuture(syncHandler(request))

        override fun close() {}
    }
}
