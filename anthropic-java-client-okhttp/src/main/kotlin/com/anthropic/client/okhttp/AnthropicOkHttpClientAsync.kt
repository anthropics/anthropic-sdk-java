// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client.okhttp

import com.anthropic.backends.AnthropicBackend
import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClientAsync
import com.anthropic.client.AnthropicClientAsyncImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.Timeout
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.fasterxml.jackson.databind.json.JsonMapper
import java.net.Proxy
import java.time.Clock
import java.time.Duration
import java.util.Optional
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

class AnthropicOkHttpClientAsync private constructor() {

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [AnthropicOkHttpClientAsync].
         */
        @JvmStatic fun builder() = Builder()

        @JvmStatic fun fromEnv(): AnthropicClientAsync = builder().fromEnv().build()
    }

    /** A builder for [AnthropicOkHttpClientAsync]. */
    class Builder internal constructor() {

        private var clientOptions: ClientOptions.Builder = ClientOptions.builder()
        private var timeout: Timeout = Timeout.default()
        private var proxy: Proxy? = null
        private var backend: Backend? = null
        private var defaultBackendBuilder: AnthropicBackend.Builder? = null

        fun baseUrl(baseUrl: String) = apply {
            ensureDefaultBackendBuilder("baseUrl").baseUrl(baseUrl)
        }

        fun jsonMapper(jsonMapper: JsonMapper) = apply { clientOptions.jsonMapper(jsonMapper) }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            clientOptions.streamHandlerExecutor(streamHandlerExecutor)
        }

        fun clock(clock: Clock) = apply { clientOptions.clock(clock) }

        fun headers(headers: Headers) = apply { clientOptions.headers(headers) }

        fun headers(headers: Map<String, Iterable<String>>) = apply {
            clientOptions.headers(headers)
        }

        fun putHeader(name: String, value: String) = apply { clientOptions.putHeader(name, value) }

        fun putHeaders(name: String, values: Iterable<String>) = apply {
            clientOptions.putHeaders(name, values)
        }

        fun putAllHeaders(headers: Headers) = apply { clientOptions.putAllHeaders(headers) }

        fun putAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            clientOptions.putAllHeaders(headers)
        }

        fun replaceHeaders(name: String, value: String) = apply {
            clientOptions.replaceHeaders(name, value)
        }

        fun replaceHeaders(name: String, values: Iterable<String>) = apply {
            clientOptions.replaceHeaders(name, values)
        }

        fun replaceAllHeaders(headers: Headers) = apply { clientOptions.replaceAllHeaders(headers) }

        fun replaceAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            clientOptions.replaceAllHeaders(headers)
        }

        fun removeHeaders(name: String) = apply { clientOptions.removeHeaders(name) }

        fun removeAllHeaders(names: Set<String>) = apply { clientOptions.removeAllHeaders(names) }

        fun queryParams(queryParams: QueryParams) = apply { clientOptions.queryParams(queryParams) }

        fun queryParams(queryParams: Map<String, Iterable<String>>) = apply {
            clientOptions.queryParams(queryParams)
        }

        fun putQueryParam(key: String, value: String) = apply {
            clientOptions.putQueryParam(key, value)
        }

        fun putQueryParams(key: String, values: Iterable<String>) = apply {
            clientOptions.putQueryParams(key, values)
        }

        fun putAllQueryParams(queryParams: QueryParams) = apply {
            clientOptions.putAllQueryParams(queryParams)
        }

        fun putAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            clientOptions.putAllQueryParams(queryParams)
        }

        fun replaceQueryParams(key: String, value: String) = apply {
            clientOptions.replaceQueryParams(key, value)
        }

        fun replaceQueryParams(key: String, values: Iterable<String>) = apply {
            clientOptions.replaceQueryParams(key, values)
        }

        fun replaceAllQueryParams(queryParams: QueryParams) = apply {
            clientOptions.replaceAllQueryParams(queryParams)
        }

        fun replaceAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            clientOptions.replaceAllQueryParams(queryParams)
        }

        fun removeQueryParams(key: String) = apply { clientOptions.removeQueryParams(key) }

        fun removeAllQueryParams(keys: Set<String>) = apply {
            clientOptions.removeAllQueryParams(keys)
        }

        fun timeout(timeout: Timeout) = apply {
            clientOptions.timeout(timeout)
            this.timeout = timeout
        }

        /**
         * Sets the maximum time allowed for a complete HTTP call, not including retries.
         *
         * See [Timeout.request] for more details.
         *
         * For fine-grained control, pass a [Timeout] object.
         */
        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        fun maxRetries(maxRetries: Int) = apply { clientOptions.maxRetries(maxRetries) }

        fun proxy(proxy: Proxy) = apply { this.proxy = proxy }

        fun responseValidation(responseValidation: Boolean) = apply {
            clientOptions.responseValidation(responseValidation)
        }

        fun apiKey(apiKey: String?) = apply { ensureDefaultBackendBuilder("apiKey").apiKey(apiKey) }

        fun apiKey(apiKey: Optional<String>) = apiKey(apiKey.getOrNull())

        fun authToken(authToken: String?) = apply {
            ensureDefaultBackendBuilder("authToken").authToken(authToken)
        }

        fun authToken(authToken: Optional<String>) = authToken(authToken.getOrNull())

        fun backend(backend: Backend) = apply {
            check(defaultBackendBuilder == null) {
                "Default backend already set. Cannot set another backend."
            }
            this.backend = backend
        }

        fun fromEnv() = apply { ensureDefaultBackendBuilder("fromEnv").fromEnv() }

        private fun ensureDefaultBackendBuilder(fromFunction: String): AnthropicBackend.Builder {
            check(backend == null) { "Backend already set. Cannot now call '$fromFunction'." }

            return defaultBackendBuilder
                ?: AnthropicBackend.builder().also { defaultBackendBuilder = it }
        }

        /**
         * Ensures that a backend is available for the creation of the client. If no [backend] was
         * set explicitly, or no default backend was set implicitly by calls to any of [baseUrl],
         * [apiKey], or [authToken], a new default [AnthropicBackend] backend will be returned with
         * the default production base URL, no API key and no auth token.
         */
        private fun ensureBackend(): Backend =
            backend ?: ensureDefaultBackendBuilder("ensureBackend").build()

        fun build(): AnthropicClientAsync =
            AnthropicClientAsyncImpl(
                clientOptions
                    .httpClient(
                        OkHttpClient.builder()
                            .timeout(timeout)
                            .proxy(proxy)
                            .backend(ensureBackend())
                            .build()
                    )
                    .build()
            )
    }
}
