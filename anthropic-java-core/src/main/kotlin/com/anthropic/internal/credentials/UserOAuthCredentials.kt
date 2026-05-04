package com.anthropic.internal.credentials

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.http.HttpResponse
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.UnexpectedStatusCodeException
import com.anthropic.internal.config.CachedTokenData
import com.anthropic.internal.config.CredentialsCache
import com.fasterxml.jackson.databind.json.JsonMapper
import java.io.OutputStream
import java.time.Instant
import java.util.concurrent.CompletableFuture

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
internal data class RefreshResponse(
    @com.fasterxml.jackson.annotation.JsonProperty("access_token") val accessToken: String?,
    @com.fasterxml.jackson.annotation.JsonProperty("expires_in") val expiresIn: Long?,
    @com.fasterxml.jackson.annotation.JsonProperty("refresh_token") val refreshToken: String?,
)

/**
 * [AccessTokenProvider] backed by a user OAuth credentials cache.
 *
 * If a [clientId] is configured, this provider is capable of refreshing the access token itself:
 * when the cached token is missing, expired, or a refresh is forced, it exchanges the stored
 * refresh token for a new access token and writes the result back to the [CredentialsCache].
 *
 * If no [clientId] is configured, this provider is read-only: it returns whatever access token is
 * currently in the cache and relies on an external program (e.g. a CLI or daemon) to keep that
 * cache fresh.
 */
internal class UserOAuthCredentials(
    private val credentialsCache: CredentialsCache,
    private val profileName: String,
    private val clientId: String?,
    private val httpClient: HttpClient?,
    private val jsonMapper: JsonMapper,
) : AccessTokenProvider {

    init {
        require(clientId == null || httpClient != null) {
            "UserOAuthCredentials for profile '$profileName' has client_id configured " +
                "but no HttpClient; refresh requires an HttpClient."
        }
    }

    override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
        val record =
            credentialsCache.readRecord()
                ?: throw AnthropicException(
                    "Credentials file for profile '$profileName' is missing or unreadable."
                )

        val accessToken =
            record.accessToken
                ?: throw AnthropicException(
                    "Credentials file for profile '$profileName' is missing 'access_token'."
                )

        val expiresAt = record.expiresAt?.let { Instant.ofEpochSecond(it) }

        if (clientId == null) {
            return AccessToken(accessToken, expiresAt)
        }

        val refreshToken =
            record.refreshToken
                ?: throw AnthropicException(
                    "Credentials file for profile '$profileName' has client_id configured " +
                        "but is missing 'refresh_token'."
                )

        if (!forceRefresh && expiresAt != null && Instant.now().isBefore(expiresAt)) {
            return AccessToken(accessToken, expiresAt)
        }

        val refreshed = performRefresh(baseUrl, refreshToken, clientId!!)

        val newExpiresAt =
            Instant.now().plusSeconds(refreshed.expiresIn ?: DEFAULT_EXPIRES_IN_SECONDS)
        val newRefreshToken = refreshed.refreshToken ?: refreshToken

        credentialsCache.writeRecord(
            CachedTokenData(
                type = "access_token",
                accessToken = refreshed.accessToken,
                expiresAt = newExpiresAt.epochSecond,
                refreshToken = newRefreshToken,
            )
        )

        return AccessToken(refreshed.accessToken!!, newExpiresAt)
    }

    override fun getAsync(baseUrl: String, forceRefresh: Boolean): CompletableFuture<AccessToken> {
        val record =
            credentialsCache.readRecord()
                ?: return failedFuture(
                    AnthropicException(
                        "Credentials file for profile '$profileName' is missing or unreadable."
                    )
                )

        val accessToken =
            record.accessToken
                ?: return failedFuture(
                    AnthropicException(
                        "Credentials file for profile '$profileName' is missing 'access_token'."
                    )
                )

        val expiresAt = record.expiresAt?.let { Instant.ofEpochSecond(it) }

        if (clientId == null) {
            return CompletableFuture.completedFuture(AccessToken(accessToken, expiresAt))
        }

        val refreshToken =
            record.refreshToken
                ?: return failedFuture(
                    AnthropicException(
                        "Credentials file for profile '$profileName' has client_id configured " +
                            "but is missing 'refresh_token'."
                    )
                )

        if (!forceRefresh && expiresAt != null && Instant.now().isBefore(expiresAt)) {
            return CompletableFuture.completedFuture(AccessToken(accessToken, expiresAt))
        }

        val request = buildRefreshRequest(baseUrl, refreshToken, clientId)
        return httpClient!!.executeAsync(request, RequestOptions.none()).thenApplyAsync { response
            ->
            val refreshed = parseRefreshResponse(response)
            val newExpiresAt =
                Instant.now().plusSeconds(refreshed.expiresIn ?: DEFAULT_EXPIRES_IN_SECONDS)
            val newRefreshToken = refreshed.refreshToken ?: refreshToken
            credentialsCache.writeRecord(
                CachedTokenData(
                    type = "access_token",
                    accessToken = refreshed.accessToken,
                    expiresAt = newExpiresAt.epochSecond,
                    refreshToken = newRefreshToken,
                )
            )
            AccessToken(refreshed.accessToken!!, newExpiresAt)
        }
    }

    private fun <T> failedFuture(e: Throwable): CompletableFuture<T> {
        val future = CompletableFuture<T>()
        future.completeExceptionally(e)
        return future
    }

    private fun performRefresh(
        baseUrl: String,
        refreshToken: String,
        clientId: String,
    ): RefreshResponse {
        val request = buildRefreshRequest(baseUrl, refreshToken, clientId)
        val response = httpClient!!.execute(request, RequestOptions.none())
        return parseRefreshResponse(response)
    }

    private fun buildRefreshRequest(
        baseUrl: String,
        refreshToken: String,
        clientId: String,
    ): HttpRequest {
        val bodyJson =
            jsonMapper.writeValueAsBytes(
                mapOf(
                    "grant_type" to "refresh_token",
                    "refresh_token" to refreshToken,
                    "client_id" to clientId,
                )
            )

        return HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl(baseUrl)
            .addPathSegments("v1", "oauth", "token")
            .putHeader("Content-Type", "application/json")
            .putHeader("anthropic-beta", "oauth-2025-04-20")
            .body(JsonBody(bodyJson))
            .build()
    }

    private fun parseRefreshResponse(response: HttpResponse): RefreshResponse {
        response.use { res ->
            val statusCode = res.statusCode()
            if (statusCode !in 200..299) {
                val bodyText = res.body().bufferedReader().readText()
                throw UnexpectedStatusCodeException.builder()
                    .statusCode(statusCode)
                    .headers(res.headers())
                    .body(tryParseJson(bodyText))
                    .build()
            }
            val parsed = jsonMapper.readValue(res.body(), RefreshResponse::class.java)
            if (parsed.accessToken.isNullOrBlank()) {
                throw UnexpectedStatusCodeException.builder()
                    .statusCode(statusCode)
                    .headers(res.headers())
                    .body(JsonMissing.of())
                    .build()
            }
            return parsed
        }
    }

    private fun tryParseJson(text: String): JsonValue =
        try {
            jsonMapper.readValue(text, JsonValue::class.java)
        } catch (_: Exception) {
            JsonMissing.of()
        }

    private class JsonBody(private val bytes: ByteArray) : HttpRequestBody {
        override fun writeTo(outputStream: OutputStream) {
            outputStream.write(bytes)
        }

        override fun contentType(): String = "application/json"

        override fun contentLength(): Long = bytes.size.toLong()

        override fun repeatable(): Boolean = true

        override fun close() {}
    }

    companion object {
        private const val DEFAULT_EXPIRES_IN_SECONDS = 3600L
    }
}
