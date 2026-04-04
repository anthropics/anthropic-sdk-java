package com.anthropic.backends

import com.anthropic.backends.AnthropicBackend.Builder
import com.anthropic.core.http.HttpRequest
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
) : Backend {

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
        require(
            !request.headers.names().contains(HEADER_API_KEY) &&
                !request.headers.names().contains(HEADER_AUTHORIZATION)
        ) {
            "Request already authorized for Anthropic."
        }

        return request
            .toBuilder()
            .apply {
                // It is possible for both or neither of these credentials to be
                // set, so zero, one or two headers may be added.
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
        private var baseUrl: String = PRODUCTION_URL

        fun fromEnv() = apply {
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

        fun build(): AnthropicBackend {
            return AnthropicBackend(apiKey, authToken, baseUrl)
        }
    }
}
