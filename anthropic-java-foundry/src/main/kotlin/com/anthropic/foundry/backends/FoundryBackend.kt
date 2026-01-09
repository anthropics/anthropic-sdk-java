package com.anthropic.foundry.backends

import com.anthropic.backends.Backend
import com.anthropic.core.http.HttpRequest
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.function.Supplier

/**
 * The Microsoft Foundry backend that manages the API key or bearer token supplier (e.g., Entra ID
 * credentials) and other information required to access an Anthropic AI model on Microsoft Foundry
 * and adapts requests to Foundry's requirements.
 *
 * Microsoft Foundry requires authentication credentials issued by Microsoft. These can be provided
 * via environment variables or directly to the [Builder]. The default environment variable API key
 * credentials can be resolved by calling [Builder.fromEnv]. For other sources of the credentials,
 * resolve them separately and provide them to [Builder.apiKey] or [Builder.bearerTokenSupplier].
 */
class FoundryBackend
private constructor(
    // One of `apiKey` _or_ `bearerTokenSupplier` must be non-null, but not both. These data are
    // sensitive, so they are not exposed for reading, other than internally for test purposes.
    @JvmSynthetic internal val apiKey: String?,
    @JvmSynthetic internal val bearerTokenSupplier: Supplier<String>?,
    // One of `resource` _or_ `baseUrl` must be non-null, but not both.
    val resource: String?,
    val baseUrl: String?,
) : Backend {

    companion object {
        private const val ENV_API_KEY = "ANTHROPIC_FOUNDRY_API_KEY"
        private const val ENV_RESOURCE = "ANTHROPIC_FOUNDRY_RESOURCE"
        private const val ENV_BASE_URL = "ANTHROPIC_FOUNDRY_BASE_URL"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_API_KEY = "x-api-key"
        private const val ANTHROPIC_VERSION = "2023-06-01"
        private const val HEADER_VERSION = "anthropic-version"

        @JvmStatic fun builder() = Builder()

        @JvmStatic fun fromEnv(): FoundryBackend = builder().fromEnv().build()
    }

    override fun baseUrl(): String = baseUrl ?: "https://$resource.services.ai.azure.com"

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        val pathSegments = request.pathSegments

        if (pathSegments.isEmpty()) {
            throw AnthropicInvalidDataException("Request missing all path segments.")
        }

        // Path segments in the input will be, e.g., `v1/messages/count_tokens`. For Foundry, a new
        // `anthropic` path segment must be inserted before the `v1` path segment. If `anthropic` is
        // already present, then the request has already been prepared.
        require(pathSegments[0] != "anthropic") { "Request already prepared for Foundry." }

        if (pathSegments[0] != "v1") {
            throw AnthropicInvalidDataException("Expected first 'v1' path segment.")
        }

        if (pathSegments.size <= 1) {
            throw AnthropicInvalidDataException("Missing service name from request URL.")
        }

        when (pathSegments[1]) {
            "messages" -> {
                if (pathSegments.size > 2 && pathSegments[2] == "batches") {
                    throw AnthropicException("Batch API is not supported for Foundry.")
                } // Ignore any other path segments, e.g., `count_tokens` is supported.
            }
            "skills",
            "files" -> {
                // Do nothing special.
            }
            // Services such as `complete`, `experimental`, `organization` and `models` are not
            // supported.
            else ->
                throw AnthropicException(
                    "Service is not supported for Foundry: ${pathSegments[1]}."
                )
        }

        // For Foundry, the `"model"` property remains in the body, unlike for other backends where
        // it is moved to a path segment. The value of the `"model"` property is the name of the
        // Magma deployment, not the name of the Anthropic model. The default name of the Magma
        // deployment is the same as the model name, but it may be changed to an arbitrary value,
        // so do _not_ validate the model name against the enum of known model names.

        return request
            .toBuilder()
            .replaceAllPathSegments("anthropic")
            .apply { pathSegments.forEach { pathSegment -> addPathSegment(pathSegment) } }
            .putHeader(HEADER_VERSION, ANTHROPIC_VERSION)
            .build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        require(
            !request.headers.names().contains(HEADER_AUTHORIZATION) &&
                !request.headers.names().contains(HEADER_API_KEY)
        ) {
            "Request already authorized for Foundry."
        }

        // `Builder.build()` ensures that at exactly one of `apiKey` and `bearerTokenSupplier` will
        // be non-null.
        val requestBuilder = request.toBuilder()

        if (apiKey != null) {
            requestBuilder.putHeader(HEADER_API_KEY, apiKey)
        } else {
            // Call `bearerTokenSupplier.get()` _each_ time to allow the implementation of the
            // supplier to refresh the token as necessary.
            requestBuilder.putHeader(HEADER_AUTHORIZATION, "Bearer ${bearerTokenSupplier!!.get()}")
        }

        return requestBuilder.build()
    }

    override fun close() {}

    /**
     * A builder for a [FoundryBackend] used to connect an Anthropic client to an Anthropic model on
     * the Microsoft Foundry backend service.
     *
     * The configuration (using an API key) can be resolved from the environment and set on the
     * builder by calling [fromEnv] before calling [build] to create the [FoundryBackend].
     * Alternatively, set the API key _or_ the bearer token supplier and the resource _or_ base URL
     * explicitly via [apiKey] or [bearerTokenSupplier] and [resource] or [baseUrl] before calling
     * [build].
     */
    class Builder internal constructor() {
        private var apiKey: String? = null
        private var bearerTokenSupplier: Supplier<String>? = null
        private var resource: String? = null
        private var baseUrl: String? = null

        /**
         * Sets the API key and the resource or base URL from values in environment variables.
         *
         * Use the `ANTHROPIC_FOUNDRY_API_KEY` environment variable to set the API key.
         *
         * Use the `ANTHROPIC_FOUNDRY_RESOURCE` environment variable to set the resource name. This
         * _must_ be set if the base URL is _not_ set (a base URL includes a resource name). If the
         * resource name is set, the default base URL will be used and will include this resource
         * name.
         *
         * Use the `ANTHROPIC_FOUNDRY_BASE_URL` environment variable to set an alternative base URL
         * instead of using the default base URL. The base URL includes the resource name, so this
         * _must_ be set if the resource name is _not_ set.
         *
         * If using Microsoft Entra ID or other credentials that require a bearer token, set the
         * [bearerTokenSupplier] and one of [resource] and [baseUrl] separately, as [fromEnv]
         * requires that an API key environment variable be set.
         *
         * @throws IllegalStateException If the API key environment variable is not set. If both or
         *   neither of the resource or base URL environment variables are set.
         */
        fun fromEnv() = apply {
            apiKey =
                getEnv(ENV_API_KEY)
                    ?: throw IllegalStateException(
                        "No API key set in the $ENV_API_KEY environment variable."
                    )

            resource = getEnv(ENV_RESOURCE)
            baseUrl = getEnv(ENV_BASE_URL)

            if ((resource == null) == (baseUrl == null)) {
                throw IllegalStateException(
                    "One of the $ENV_RESOURCE and $ENV_BASE_URL environment variables must " +
                        "be set, but not both."
                )
            }
        }

        /**
         * Wraps access to system environment variables to allow mocking of environment variables
         * when testing.
         */
        @JvmSynthetic internal fun getEnv(name: String): String? = System.getenv(name)

        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }

        /**
         * Sets a bearer token supplier that will be responsible for supplying authorization tokens
         * when authorizing requests. `Supplier.get()` will be called for each request, allowing the
         * supplier to implement its own token caching/refreshing logic.
         */
        fun bearerTokenSupplier(bearerTokenSupplier: Supplier<String>?) = apply {
            this.bearerTokenSupplier = bearerTokenSupplier
        }

        fun resource(resource: String) = apply { this.resource = resource }

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }

        /**
         * @throws IllegalStateException If both or neither of the API key or bearer token supplier
         *   are set, as one is required and they are mutually exclusive. If both or neither of the
         *   resource or base URL are set, as one is required and they are mutually exclusive.
         */
        fun build(): FoundryBackend {
            check(apiKey != null || bearerTokenSupplier != null) {
                "`apiKey` or `bearerTokenSupplier` must be set."
            }
            check(apiKey == null || bearerTokenSupplier == null) {
                "`apiKey` and `bearerTokenSupplier` cannot both be set."
            }

            check(resource != null || baseUrl != null) { "`resource` or `baseUrl` must be set." }
            check(resource == null || baseUrl == null) {
                "`resource` and `baseUrl` cannot both be set."
            }

            return FoundryBackend(apiKey, bearerTokenSupplier, resource, baseUrl)
        }
    }
}
