// File generated from our OpenAPI spec by Stainless.

package com.anthropic.client.okhttp

import com.anthropic.backends.AnthropicBackend
import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClientAsync
import com.anthropic.client.AnthropicClientAsyncImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.Sleeper
import com.anthropic.core.Timeout
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.QueryParams
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import java.net.Proxy
import java.time.Clock
import java.time.Duration
import java.util.Optional
import java.util.concurrent.Executor
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager
import kotlin.jvm.optionals.getOrNull

/**
 * A class that allows building an instance of [AnthropicClientAsync] with [OkHttpClient] as the
 * underlying [HttpClient].
 */
class AnthropicOkHttpClientAsync private constructor() {

    companion object {

        /** Returns a mutable builder for constructing an instance of [AnthropicClientAsync]. */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns a client configured using system properties and environment variables.
         *
         * @see Builder.fromEnv
         */
        @JvmStatic fun fromEnv(): AnthropicClientAsync = builder().fromEnv().build()
    }

    /** A builder for [AnthropicOkHttpClientAsync]. */
    class Builder internal constructor() {

        private var clientOptions: ClientOptions.Builder = ClientOptions.builder()
        private var proxy: Proxy? = null
        private var sslSocketFactory: SSLSocketFactory? = null
        private var trustManager: X509TrustManager? = null
        private var hostnameVerifier: HostnameVerifier? = null
        private var backend: Backend? = null
        private var defaultBackendBuilder: AnthropicBackend.Builder? = null

        fun proxy(proxy: Proxy?) = apply { this.proxy = proxy }

        /** Alias for calling [Builder.proxy] with `proxy.orElse(null)`. */
        fun proxy(proxy: Optional<Proxy>) = proxy(proxy.getOrNull())

        /**
         * The socket factory used to secure HTTPS connections.
         *
         * If this is set, then [trustManager] must also be set.
         *
         * If unset, then the system default is used. Most applications should not call this method,
         * and instead use the system default. The default include special optimizations that can be
         * lost if the implementation is modified.
         */
        fun sslSocketFactory(sslSocketFactory: SSLSocketFactory?) = apply {
            this.sslSocketFactory = sslSocketFactory
        }

        /** Alias for calling [Builder.sslSocketFactory] with `sslSocketFactory.orElse(null)`. */
        fun sslSocketFactory(sslSocketFactory: Optional<SSLSocketFactory>) =
            sslSocketFactory(sslSocketFactory.getOrNull())

        /**
         * The trust manager used to secure HTTPS connections.
         *
         * If this is set, then [sslSocketFactory] must also be set.
         *
         * If unset, then the system default is used. Most applications should not call this method,
         * and instead use the system default. The default include special optimizations that can be
         * lost if the implementation is modified.
         */
        fun trustManager(trustManager: X509TrustManager?) = apply {
            this.trustManager = trustManager
        }

        /** Alias for calling [Builder.trustManager] with `trustManager.orElse(null)`. */
        fun trustManager(trustManager: Optional<X509TrustManager>) =
            trustManager(trustManager.getOrNull())

        /**
         * The verifier used to confirm that response certificates apply to requested hostnames for
         * HTTPS connections.
         *
         * If unset, then a default hostname verifier is used.
         */
        fun hostnameVerifier(hostnameVerifier: HostnameVerifier?) = apply {
            this.hostnameVerifier = hostnameVerifier
        }

        /** Alias for calling [Builder.hostnameVerifier] with `hostnameVerifier.orElse(null)`. */
        fun hostnameVerifier(hostnameVerifier: Optional<HostnameVerifier>) =
            hostnameVerifier(hostnameVerifier.getOrNull())

        /**
         * Whether to throw an exception if any of the Jackson versions detected at runtime are
         * incompatible with the SDK's minimum supported Jackson version (2.13.4).
         *
         * Defaults to true. Use extreme caution when disabling this option. There is no guarantee
         * that the SDK will work correctly when using an incompatible Jackson version.
         */
        fun checkJacksonVersionCompatibility(checkJacksonVersionCompatibility: Boolean) = apply {
            clientOptions.checkJacksonVersionCompatibility(checkJacksonVersionCompatibility)
        }

        /**
         * The Jackson JSON mapper to use for serializing and deserializing JSON.
         *
         * Defaults to [com.anthropic.core.jsonMapper]. The default is usually sufficient and rarely
         * needs to be overridden.
         */
        fun jsonMapper(jsonMapper: JsonMapper) = apply { clientOptions.jsonMapper(jsonMapper) }

        /**
         * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
         *
         * Defaults to a dedicated cached thread pool.
         *
         * This class takes ownership of the executor and shuts it down, if possible, when closed.
         */
        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            clientOptions.streamHandlerExecutor(streamHandlerExecutor)
        }

        /**
         * The interface to use for delaying execution, like during retries.
         *
         * This is primarily useful for using fake delays in tests.
         *
         * Defaults to real execution delays.
         *
         * This class takes ownership of the sleeper and closes it when closed.
         */
        fun sleeper(sleeper: Sleeper) = apply { clientOptions.sleeper(sleeper) }

        /**
         * The clock to use for operations that require timing, like retries.
         *
         * This is primarily useful for using a fake clock in tests.
         *
         * Defaults to [Clock.systemUTC].
         */
        fun clock(clock: Clock) = apply { clientOptions.clock(clock) }

        /**
         * The base URL to use for every request.
         *
         * Defaults to the production environment: `https://api.anthropic.com`.
         */
        fun baseUrl(baseUrl: String?) = apply {
            ensureDefaultBackendBuilder("baseUrl").baseUrl(baseUrl)
        }

        /** Alias for calling [Builder.baseUrl] with `baseUrl.orElse(null)`. */
        fun baseUrl(baseUrl: Optional<String>) = baseUrl(baseUrl.getOrNull())

        /**
         * Whether to call `validate` on every response before returning it.
         *
         * Defaults to false, which means the shape of the response will not be validated upfront.
         * Instead, validation will only occur for the parts of the response that are accessed.
         */
        fun responseValidation(responseValidation: Boolean) = apply {
            clientOptions.responseValidation(responseValidation)
        }

        /**
         * Sets the maximum time allowed for various parts of an HTTP call's lifecycle, excluding
         * retries.
         *
         * Defaults to [Timeout.default].
         */
        fun timeout(timeout: Timeout) = apply { clientOptions.timeout(timeout) }

        /**
         * Sets the maximum time allowed for a complete HTTP call, not including retries.
         *
         * See [Timeout.request] for more details.
         *
         * For fine-grained control, pass a [Timeout] object.
         */
        fun timeout(timeout: Duration) = apply { clientOptions.timeout(timeout) }

        /**
         * The maximum number of times to retry failed requests, with a short exponential backoff
         * between requests.
         *
         * Only the following error types are retried:
         * - Connection errors (for example, due to a network connectivity problem)
         * - 408 Request Timeout
         * - 409 Conflict
         * - 429 Rate Limit
         * - 5xx Internal
         *
         * The API may also explicitly instruct the SDK to retry or not retry a request.
         *
         * Defaults to 2.
         */
        fun maxRetries(maxRetries: Int) = apply { clientOptions.maxRetries(maxRetries) }

        fun apiKey(apiKey: String?) = apply { ensureDefaultBackendBuilder("apiKey").apiKey(apiKey) }

        /** Alias for calling [Builder.apiKey] with `apiKey.orElse(null)`. */
        fun apiKey(apiKey: Optional<String>) = apiKey(apiKey.getOrNull())

        fun authToken(authToken: String?) = apply {
            ensureDefaultBackendBuilder("authToken").authToken(authToken)
        }

        /** Alias for calling [Builder.authToken] with `authToken.orElse(null)`. */
        fun authToken(authToken: Optional<String>) = authToken(authToken.getOrNull())

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

        /**
         * Updates configuration using system properties and environment variables.
         *
         * See this table for the available options:
         *
         * |Setter     |System property      |Environment variable  |Required|Default value                |
         * |-----------|---------------------|----------------------|--------|-----------------------------|
         * |`apiKey`   |`anthropic.apiKey`   |`ANTHROPIC_API_KEY`   |false   |-                            |
         * |`authToken`|`anthropic.authToken`|`ANTHROPIC_AUTH_TOKEN`|false   |-                            |
         * |`baseUrl`  |`anthropic.baseUrl`  |`ANTHROPIC_BASE_URL`  |true    |`"https://api.anthropic.com"`|
         *
         * System properties take precedence over environment variables.
         */
        fun fromEnv() = apply { ensureDefaultBackendBuilder("fromEnv").fromEnv() }

        fun backend(backend: Backend) = apply {
            check(defaultBackendBuilder == null) {
                "Default backend already set. Cannot set another backend."
            }
            this.backend = backend
        }

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

        /**
         * Returns an immutable instance of [AnthropicClientAsync].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): AnthropicClientAsync =
            AnthropicClientAsyncImpl(
                clientOptions
                    .httpClient(
                        OkHttpClient.builder()
                            .timeout(clientOptions.timeout())
                            .proxy(proxy)
                            .sslSocketFactory(sslSocketFactory)
                            .trustManager(trustManager)
                            .hostnameVerifier(hostnameVerifier)
                            .backend(ensureBackend())
                            .build()
                    )
                    .build()
            )
    }
}
