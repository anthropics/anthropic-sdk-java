// File generated from our OpenAPI spec by Stainless.

package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.http.Headers
import kotlinx.kmp.util.core.http.HttpClient
import kotlinx.kmp.util.core.http.QueryParams
import kotlinx.kmp.util.core.http.withRetry
import kotlin.time.Duration

/** Create a default stream handler executor. JVM: cached thread pool. Other: no-op/null. */
expect fun createDefaultStreamExecutor(): Any?

/** Create a default wrapped sleeper. JVM: PhantomReachableSleeper(DefaultSleeper()). */
expect fun createDefaultSleeper(): Sleeper

/** Shut down the stream handler executor if it supports lifecycle management. */
expect fun shutdownStreamExecutor(executor: Any?)

/** Wrap an HttpClient for phantom-reachable closing. JVM: PhantomReachableClosingHttpClient. */
expect fun wrapHttpClient(client: HttpClient): HttpClient

/** A class representing the SDK client configuration. */
class ClientOptions
private constructor(
    private val originalHttpClient: HttpClient,
    /**
     * The HTTP client to use in the SDK.
     *
     * Use the one published in `anthropic-java-client-okhttp` or implement your own.
     *
     * This class takes ownership of the client and closes it when closed.
     */
    @get:JvmName("httpClient") val httpClient: HttpClient,
    /**
     * Whether to throw an exception if any of the Jackson versions detected at runtime are
     * incompatible with the SDK's minimum supported Jackson version (2.13.4).
     *
     * Defaults to true. Use extreme caution when disabling this option. There is no guarantee that
     * the SDK will work correctly when using an incompatible Jackson version.
     */
    @get:JvmName("checkJsonVersionCompatibility") val checkJsonVersionCompatibility: Boolean,
    /**
     * The Jackson JSON mapper to use for serializing and deserializing JSON.
     *
     * Defaults to [kotlinx.kmp.util.core.jsonMapper]. The default is usually sufficient and rarely
     * needs to be overridden.
     */
    @get:JvmName("jsonMapper") val jsonMapper: JsonMapperType,
    /**
     * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
     *
     * Defaults to a dedicated cached thread pool.
     *
     * This class takes ownership of the executor and shuts it down, if possible, when closed.
     */
    @get:JvmName("streamHandlerExecutor") val streamHandlerExecutor: Any?,
    /**
     * The interface to use for delaying execution, like during retries.
     *
     * This is primarily useful for using fake delays in tests.
     *
     * Defaults to real execution delays.
     *
     * This class takes ownership of the sleeper and closes it when closed.
     */
    @get:JvmName("sleeper") val sleeper: Sleeper,
    /** The time provider to use for operations that require timing, like retries. */
    @get:JvmName("nowMillisProvider") val nowMillisProvider: () -> Long,
    val baseUrl: String?,
    /** Headers to send with the request. */
    @get:JvmName("headers") val headers: Headers,
    /** Query params to send with the request. */
    @get:JvmName("queryParams") val queryParams: QueryParams,
    /**
     * Whether to call `validate` on every response before returning it.
     *
     * Defaults to false, which means the shape of the response will not be validated upfront.
     * Instead, validation will only occur for the parts of the response that are accessed.
     */
    @get:JvmName("responseValidation") val responseValidation: Boolean,
    /**
     * Sets the maximum time allowed for various parts of an HTTP call's lifecycle, excluding
     * retries.
     *
     * Defaults to [Timeout.default].
     */
    @get:JvmName("timeout") val timeout: Timeout,
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
    @get:JvmName("maxRetries") val maxRetries: Int,
    /** Optional header name for per-request idempotency keys used by the retry wrapper. */
    @get:JvmName("idempotencyHeader") val idempotencyHeader: String? = null,
) {

    init {
        if (checkJsonVersionCompatibility) {
            kotlinx.kmp.util.core.checkJsonVersionCompatibility()
        }
    }

    /**
     * The base URL to use for every request.
     *
     * Defaults to the production environment: `https://api.anthropic.com`.
     */
    fun baseUrl(): String? = baseUrl

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ClientOptions].
         *
         * The following fields are required:
         * ```java
         * .httpClient()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ClientOptions]. */
    class Builder internal constructor() {

        private var httpClient: HttpClient? = null
        private var checkJsonVersionCompatibility: Boolean = true
        private var jsonMapper: JsonMapperType = kotlinx.kmp.util.core.jsonMapper()
        private var streamHandlerExecutor: Any? = null
        private var sleeper: Sleeper? = null
        private var nowMillisProvider: () -> Long = ::currentTimeMillis
        private var baseUrl: String? = null
        private var headers: Headers.Builder = Headers.builder()
        private var queryParams: QueryParams.Builder = QueryParams.builder()
        private var responseValidation: Boolean = false
        private var timeout: Timeout = Timeout.default()
        private var maxRetries: Int = 2
        private var idempotencyHeader: String? = null

        @JvmSynthetic fun from(clientOptions: ClientOptions) = apply {
            httpClient = clientOptions.originalHttpClient
            checkJsonVersionCompatibility = clientOptions.checkJsonVersionCompatibility
            jsonMapper = clientOptions.jsonMapper
            streamHandlerExecutor = clientOptions.streamHandlerExecutor
            sleeper = clientOptions.sleeper
            nowMillisProvider = clientOptions.nowMillisProvider
            baseUrl = clientOptions.baseUrl
            headers = clientOptions.headers.toBuilder()
            queryParams = clientOptions.queryParams.toBuilder()
            responseValidation = clientOptions.responseValidation
            timeout = clientOptions.timeout
            maxRetries = clientOptions.maxRetries
            idempotencyHeader = clientOptions.idempotencyHeader
        }

        /**
         * The HTTP client to use in the SDK.
         *
         * Use the one published in `anthropic-java-client-okhttp` or implement your own.
         *
         * This class takes ownership of the client and closes it when closed.
         */
        fun httpClient(httpClient: HttpClient) = apply {
            this.httpClient = wrapHttpClient(httpClient)
        }

        /**
         * Whether to throw an exception if any of the Jackson versions detected at runtime are
         * incompatible with the SDK's minimum supported Jackson version (2.13.4).
         *
         * Defaults to true. Use extreme caution when disabling this option. There is no guarantee
         * that the SDK will work correctly when using an incompatible Jackson version.
         */
        fun checkJsonVersionCompatibility(checkJsonVersionCompatibility: Boolean) = apply {
            this.checkJsonVersionCompatibility = checkJsonVersionCompatibility
        }

        /**
         * The Jackson JSON mapper to use for serializing and deserializing JSON.
         *
         * Defaults to [kotlinx.kmp.util.core.jsonMapper]. The default is usually sufficient and rarely
         * needs to be overridden.
         */
        fun jsonMapper(jsonMapper: JsonMapperType) = apply { this.jsonMapper = jsonMapper }

        /**
         * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
         *
         * Defaults to a dedicated cached thread pool.
         *
         * This class takes ownership of the executor and shuts it down, if possible, when closed.
         */
        fun streamHandlerExecutor(streamHandlerExecutor: Any?) = apply { this.streamHandlerExecutor = streamHandlerExecutor }

        /**
         * The interface to use for delaying execution, like during retries.
         *
         * This is primarily useful for using fake delays in tests.
         *
         * Defaults to real execution delays.
         *
         * This class takes ownership of the sleeper and closes it when closed.
         */
        fun sleeper(sleeper: Sleeper) = apply { this.sleeper = sleeper }

        /** The time provider to use for operations that require timing, like retries. */
        fun nowMillisProvider(nowMillisProvider: () -> Long) = apply { this.nowMillisProvider = nowMillisProvider }

        /**
         * The base URL to use for every request.
         *
         * Defaults to the production environment: `https://api.anthropic.com`.
         */
        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }

        /** Alias for calling [Builder.baseUrl] with `baseUrl.orElse(null)`. */

        /**
         * Whether to call `validate` on every response before returning it.
         *
         * Defaults to false, which means the shape of the response will not be validated upfront.
         * Instead, validation will only occur for the parts of the response that are accessed.
         */
        fun responseValidation(responseValidation: Boolean) = apply {
            this.responseValidation = responseValidation
        }

        /**
         * Sets the maximum time allowed for various parts of an HTTP call's lifecycle, excluding
         * retries.
         *
         * Defaults to [Timeout.default].
         */
        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        /**
         * Sets the maximum time allowed for a complete HTTP call, not including retries.
         *
         * See [Timeout.request] for more details.
         *
         * For fine-grained control, pass a [Timeout] object.
         */
        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

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
        fun maxRetries(maxRetries: Int) = apply { this.maxRetries = maxRetries }

        /**
         * Header name to use for per-request idempotency keys. When set, the retry wrapper
         * injects a unique stainless-retry-&lt;uuid&gt; value under this header on each outbound
         * request so the server can deduplicate retries.
         */
        fun idempotencyHeader(idempotencyHeader: String?) = apply {
            this.idempotencyHeader = idempotencyHeader
        }

        fun headers(headers: Headers) = apply {
            this.headers.clear()
            putAllHeaders(headers)
        }

        fun headers(headers: Map<String, Iterable<String>>) = apply {
            this.headers.clear()
            putAllHeaders(headers)
        }

        fun putHeader(name: String, value: String) = apply { headers.put(name, value) }

        fun putHeaders(name: String, values: Iterable<String>) = apply { headers.put(name, values) }

        fun putAllHeaders(headers: Headers) = apply { this.headers.putAll(headers) }

        fun putAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            this.headers.putAll(headers)
        }

        fun replaceHeaders(name: String, value: String) = apply { headers.replace(name, value) }

        fun replaceHeaders(name: String, values: Iterable<String>) = apply {
            headers.replace(name, values)
        }

        fun replaceAllHeaders(headers: Headers) = apply { this.headers.replaceAll(headers) }

        fun replaceAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            this.headers.replaceAll(headers)
        }

        fun removeHeaders(name: String) = apply { headers.remove(name) }

        fun removeAllHeaders(names: Set<String>) = apply { headers.removeAll(names) }

        fun queryParams(queryParams: QueryParams) = apply {
            this.queryParams.clear()
            putAllQueryParams(queryParams)
        }

        fun queryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.clear()
            putAllQueryParams(queryParams)
        }

        fun putQueryParam(key: String, value: String) = apply { queryParams.put(key, value) }

        fun putQueryParams(key: String, values: Iterable<String>) = apply {
            queryParams.put(key, values)
        }

        fun putAllQueryParams(queryParams: QueryParams) = apply {
            this.queryParams.putAll(queryParams)
        }

        fun putAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.putAll(queryParams)
        }

        fun replaceQueryParams(key: String, value: String) = apply {
            queryParams.replace(key, value)
        }

        fun replaceQueryParams(key: String, values: Iterable<String>) = apply {
            queryParams.replace(key, values)
        }

        fun replaceAllQueryParams(queryParams: QueryParams) = apply {
            this.queryParams.replaceAll(queryParams)
        }

        fun replaceAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.replaceAll(queryParams)
        }

        fun removeQueryParams(key: String) = apply { queryParams.remove(key) }

        fun removeAllQueryParams(keys: Set<String>) = apply { queryParams.removeAll(keys) }

        fun timeout(): Timeout = timeout

        /**
         * Returns an immutable instance of [ClientOptions].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .httpClient()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ClientOptions {
            val httpClient = checkRequired("httpClient", httpClient)
            val streamHandlerExecutor = streamHandlerExecutor ?: createDefaultStreamExecutor()
            val sleeper = sleeper ?: createDefaultSleeper()

            val headers = Headers.builder()
            val queryParams = QueryParams.builder()
            headers.put("X-Stainless-Lang", "java")
            headers.put("X-Stainless-Arch", getOsArch())
            headers.put("X-Stainless-OS", getOsName())
            headers.put("X-Stainless-OS-Version", getOsVersion())
            headers.put("X-Stainless-Package-Version", getPackageVersion())
            headers.put("X-Stainless-Runtime", "JRE")
            headers.put("X-Stainless-Runtime-Version", getJavaVersion())
            headers.put("X-Stainless-Kotlin-Version", KotlinVersion.CURRENT.toString())
            // We replace after all the default headers to allow end-users to overwrite them.
            headers.replaceAll(this.headers.build())
            queryParams.replaceAll(this.queryParams.build())

            return ClientOptions(
                httpClient,
                httpClient.withRetry(maxRetries, sleeper, idempotencyHeader, nowMillisProvider),
                checkJsonVersionCompatibility,
                jsonMapper,
                streamHandlerExecutor,
                sleeper,
                nowMillisProvider,
                baseUrl,
                headers.build(),
                queryParams.build(),
                responseValidation,
                timeout,
                maxRetries,
                idempotencyHeader,
            )
        }
    }

    /**
     * Closes these client options, relinquishing any underlying resources.
     *
     * This is purposefully not inherited from [AutoCloseable] because the client options are
     * long-lived and usually should not be synchronously closed via try-with-resources.
     *
     * It's also usually not necessary to call this method at all. the default client automatically
     * releases threads and connections if they remain idle, but if you are writing an application
     * that needs to aggressively release unused resources, then you may call this method.
     */
    fun close() {
        httpClient.close()
        shutdownStreamExecutor(streamHandlerExecutor)
        sleeper.close()
    }
}
