package com.anthropic.backends

import com.anthropic.backends.AnthropicBackend.Builder
import com.anthropic.config.ProfileConfigProvider
import com.anthropic.core.ClientOptions
import com.anthropic.core.auth.CachingAccessTokenProvider
import com.anthropic.core.auth.CredentialResult
import com.anthropic.core.auth.IdentityTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.jsonMapper
import com.anthropic.credentials.CredentialResolver
import com.anthropic.errors.NoCredentialsException
import com.anthropic.internal.credentials.WorkloadIdentityCredentials
import com.fasterxml.jackson.databind.json.JsonMapper
import java.util.Optional

/**
 * The Anthropic backend that manages the API key or authorization token credentials and base URL
 * required to access an Anthropic AI model on the Anthropic service.
 *
 * The [apiKey] or the [authToken] should be set (or resolved from the environment). The default
 * value for the base URL is the normal Anthropic API service base URL, so it only needs to be set
 * if a different base URL is required.
 *
 * The credentials can be resolved from environment variables by calling [Builder.fromEnv].
 * Alternatively, they can be supplied directly to the builder.
 */
class AnthropicBackend
private constructor(
    @get:JvmName("apiKey") val apiKey: String?,
    @get:JvmName("authToken") val authToken: String?,
    val baseUrl: String,
    @get:JvmName("jsonMapper") val jsonMapper: JsonMapper,
    private val federationConfig: FederationConfig?,
    private val configurationProvider: ProfileConfigProvider?,
    /**
     * Tracks whether [Builder.fromEnv] was called. This enables the full credential resolution
     * chain (environment variables, profile files, workload identity federation) even when no
     * static API key or auth token was found.
     *
     * Without this flag, we couldn't distinguish between:
     * - `builder().build()` — no credentials configured, don't attempt resolution
     * - `builder().fromEnv().build()` — user explicitly requested env-based config, run the full
     *   credential resolution chain
     */
    private val fromEnvCalled: Boolean,
) : Backend {

    private data class FederationConfig(
        val identityTokenProvider: IdentityTokenProvider,
        val federationRuleId: String,
        val organizationId: String,
        val serviceAccountId: String?,
    )

    fun applyCredentials(httpClient: HttpClient, clientOptionsBuilder: ClientOptions.Builder) {
        resolveCredentials(httpClient)?.let { result ->
            clientOptionsBuilder.credentials(result)
            result.baseUrl?.let { clientOptionsBuilder.baseUrl(it) }
        }
    }

    @JvmSynthetic
    internal fun resolveCredentials(httpClient: HttpClient): CredentialResult? {
        if (apiKey != null || authToken != null) {
            return null
        }

        val config = federationConfig
        if (config != null) {
            val workloadCredentials =
                WorkloadIdentityCredentials(
                    config.identityTokenProvider,
                    config.federationRuleId,
                    config.organizationId,
                    config.serviceAccountId,
                    httpClient,
                    jsonMapper,
                )
            return CredentialResult(CachingAccessTokenProvider(workloadCredentials))
        }

        val provider = configurationProvider
        if (provider != null) {
            return try {
                CredentialResolver.builder()
                    .configurationProvider(provider)
                    .httpClient(httpClient)
                    .build()
                    .resolve()
            } catch (e: NoCredentialsException) {
                null
            }
        }

        if (fromEnvCalled) {
            return try {
                CredentialResolver.fromEnv(httpClient).resolve()
            } catch (e: NoCredentialsException) {
                null
            }
        }

        return null
    }

    companion object {
        private const val PRODUCTION_URL = "https://api.anthropic.com"
        private const val ANTHROPIC_VERSION = "2023-06-01"
        private const val ENV_API_KEY = "ANTHROPIC_API_KEY"
        private const val ENV_AUTH_TOKEN = "ANTHROPIC_AUTH_TOKEN"
        private const val HEADER_API_KEY = "X-Api-Key"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_VERSION = "anthropic-version"

        @JvmStatic fun builder() = Builder()

        @JvmStatic fun fromEnv(): AnthropicBackend = builder().fromEnv().build()
    }

    override fun baseUrl(): String = baseUrl

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        require(!request.headers.names().contains(HEADER_VERSION)) {
            "Request already prepared for Anthropic."
        }

        return request.toBuilder().putHeader(HEADER_VERSION, ANTHROPIC_VERSION).build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        val hasApiKey = request.headers.names().contains(HEADER_API_KEY)
        val hasAuthorization = request.headers.names().contains(HEADER_AUTHORIZATION)

        if (hasApiKey || hasAuthorization) {
            return request
        }

        return request
            .toBuilder()
            .apply {
                apiKey?.let { putHeader(HEADER_API_KEY, it) }
                authToken?.let { putHeader(HEADER_AUTHORIZATION, "Bearer $it") }
            }
            .build()
    }

    override fun close() {}

    /**
     * A builder for a [AnthropicBackend] used to connect an Anthropic client to an Anthropic
     * backend service.
     *
     * The authorization credentials can be extracted from the environment and set on the builder by
     * calling [fromEnv] before calling [build] to create the [AnthropicBackend]. Alternatively, set
     * the credentials explicitly via [apiKey] or [authToken] before calling [build].
     */
    class Builder internal constructor() {
        private var apiKey: String? = null
        private var authToken: String? = null
        private var federationConfig: FederationConfig? = null
        private var configurationProvider: ProfileConfigProvider? = null
        private var fromEnvCalled: Boolean = false
        private var baseUrl: String = PRODUCTION_URL
        private var jsonMapper: JsonMapper? = null

        fun fromEnv() = apply {
            fromEnvCalled = true
            (System.getProperty("anthropic.baseUrl") ?: System.getenv("ANTHROPIC_BASE_URL"))?.let {
                baseUrl = it
            }
            (System.getProperty("anthropic.apiKey") ?: System.getenv(ENV_API_KEY))?.let {
                apiKey(it)
            }
            (System.getProperty("anthropic.authToken") ?: System.getenv(ENV_AUTH_TOKEN))?.let {
                authToken(it)
            }
        }

        fun apiKey(apiKey: String?) = apply { this.apiKey = apiKey }

        fun apiKey(apiKey: Optional<String>) = apiKey(apiKey.orElse(null))

        fun authToken(authToken: String?) = apply { this.authToken = authToken }

        fun authToken(authToken: Optional<String>) = authToken(authToken.orElse(null))

        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl ?: PRODUCTION_URL }

        fun jsonMapper(jsonMapper: JsonMapper?) = apply { this.jsonMapper = jsonMapper }

        @JvmSynthetic
        internal fun federationTokenProvider(
            identityTokenProvider: IdentityTokenProvider,
            federationRuleId: String,
            organizationId: String,
        ) = federationTokenProvider(identityTokenProvider, federationRuleId, organizationId, null)

        @JvmSynthetic
        internal fun federationTokenProvider(
            identityTokenProvider: IdentityTokenProvider,
            federationRuleId: String,
            organizationId: String,
            serviceAccountId: String?,
        ) = apply {
            clearCredentials()
            federationConfig =
                FederationConfig(
                    identityTokenProvider,
                    federationRuleId,
                    organizationId,
                    serviceAccountId,
                )
        }

        fun configurationProvider(provider: ProfileConfigProvider) = apply {
            clearCredentials()
            configurationProvider = provider
        }

        private fun clearCredentials() {
            apiKey = null
            authToken = null
            federationConfig = null
            configurationProvider = null
            fromEnvCalled = false
        }

        fun build(): AnthropicBackend {
            return AnthropicBackend(
                apiKey,
                authToken,
                baseUrl,
                jsonMapper ?: jsonMapper(),
                federationConfig,
                configurationProvider,
                fromEnvCalled,
            )
        }
    }
}
