package com.anthropic.core

import com.anthropic.backends.Backend
import com.anthropic.core.auth.CredentialResult
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.Interceptor
import com.anthropic.core.http.LoggingHttpClient
import com.anthropic.core.http.PhantomReachableClosingHttpClient
import com.anthropic.core.http.QueryParams
import com.anthropic.core.http.RetryingHttpClient
import com.anthropic.core.http.intercept
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.core.opentelemetry.TracingHttpClient
import com.anthropic.internal.core.http.AuthorizingHttpClient
import com.anthropic.internal.core.http.HeaderAuthHttpClient
import com.fasterxml.jackson.databind.json.JsonMapper
import io.opentelemetry.api.GlobalOpenTelemetry
import java.time.Clock
import java.time.Duration
import java.util.Optional
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicLong
import kotlin.jvm.optionals.getOrNull

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
     * Wraps the HTTP client using the given [interceptors].
     *
     * The interceptors are applied to every request, including retries, in order.
     *
     * Each interceptor receives an HTTP client that delegates to the next interceptor, and the last
     * interceptor receives the underlying HTTP client.
     *
     * Interceptors observe requests in the canonical Anthropic API shape, with the client's default
     * headers and first-party credentials (`Authorization`) already applied, and they observe
     * responses normalized to the Anthropic shape (e.g. Bedrock's binary event stream is decoded to
     * SSE). Backend-specific adaptation — URL rewriting, request signing (e.g. AWS SigV4), and
     * backend credentials — happens below the interceptors and is not visible to them.
     *
     * Because credentials are applied above the interceptors, an interceptor that wants to replace
     * them must use `replaceHeaders`, not `putHeader` (which would append a duplicate header).
     * Interceptors that forward request data to external systems must redact these headers.
     */
    @get:JvmName("interceptors") val interceptors: List<Interceptor>,
    /**
     * Whether to throw an exception if any of the Jackson versions detected at runtime are
     * incompatible with the SDK's minimum supported Jackson version (2.13.4).
     *
     * Defaults to true. Use extreme caution when disabling this option. There is no guarantee that
     * the SDK will work correctly when using an incompatible Jackson version.
     */
    @get:JvmName("checkJacksonVersionCompatibility") val checkJacksonVersionCompatibility: Boolean,
    private val backend: Backend?,
    /**
     * The Jackson JSON mapper to use for serializing and deserializing JSON.
     *
     * Defaults to [com.anthropic.core.jsonMapper]. The default is usually sufficient and rarely
     * needs to be overridden.
     */
    @get:JvmName("jsonMapper") val jsonMapper: JsonMapper,
    /**
     * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
     *
     * Defaults to a dedicated cached thread pool.
     *
     * This class takes ownership of the executor and shuts it down, if possible, when closed.
     */
    @get:JvmName("streamHandlerExecutor") val streamHandlerExecutor: Executor,
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
    /**
     * The clock to use for operations that require timing, like retries.
     *
     * This is primarily useful for using a fake clock in tests.
     *
     * Defaults to [Clock.systemUTC].
     */
    @get:JvmName("clock") val clock: Clock,
    private val baseUrl: String?,
    /** Headers to send with the request. */
    @get:JvmName("headers") val headers: Headers,
    /** Query params to send with the request. */
    @get:JvmName("queryParams") val queryParams: QueryParams,
    /**
     * Whether to call `validate` on every response before returning it.
     *
     * Setting this to `true` is _not_ forwards compatible with new types from the API for existing
     * fields.
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
    /**
     * The level at which to log request and response information.
     *
     * [fromEnv] will set the level from environment variables. See [LogLevel.fromEnv].
     *
     * Defaults to [LogLevel.fromEnv].
     */
    @get:JvmName("logLevel") val logLevel: LogLevel,
    /**
     * OpenTelemetry tracing of this client's API calls. Each call emits a span when the application
     * has registered a tracer provider with [GlobalOpenTelemetry], or has set
     * [OpenTelemetryOptions.tracerProvider].
     *
     * [fromEnv] sets the options that a system property or an environment variable sets. See
     * [OpenTelemetryOptions.fromEnv].
     */
    @get:JvmName("openTelemetry") val openTelemetry: OpenTelemetryOptions,
    /**
     * The `gen_ai.provider.name` attribute of this client's spans. The client builder sets it from
     * its backend. See [com.anthropic.backends.Backend.openTelemetryProviderName].
     */
    @get:JvmName("openTelemetryProviderName") val openTelemetryProviderName: String,
    private val openTelemetryPlatform: String?,
    private val webhookKey: String?,
    private val credentialResult: CredentialResult?,
) {

    init {
        if (checkJacksonVersionCompatibility) {
            checkJacksonVersionCompatibility()
        }
    }

    /**
     * The base URL to use for every request.
     *
     * Defaults to the production environment: `https://api.anthropic.com`.
     */
    fun baseUrl(): String = baseUrl ?: PRODUCTION_URL

    /**
     * The platform in the instrumentation scope of this client's spans. The client of another
     * platform sets the platform's name, and its spans then have the scope
     * `com.anthropic.sdk.java.<platform>`, in place of `com.anthropic.sdk.java`.
     */
    fun openTelemetryPlatform(): Optional<String> = Optional.ofNullable(openTelemetryPlatform)

    fun webhookKey(): Optional<String> = Optional.ofNullable(webhookKey)

    fun toBuilder() = Builder().from(this)

    companion object {

        const val PRODUCTION_URL = "https://api.anthropic.com"

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
        private var interceptors: MutableList<Interceptor> = mutableListOf()
        private var checkJacksonVersionCompatibility: Boolean = true
        private var backend: Backend? = null
        private var jsonMapper: JsonMapper = jsonMapper()
        private var streamHandlerExecutor: Executor? = null
        private var sleeper: Sleeper? = null
        private var clock: Clock = Clock.systemUTC()
        private var baseUrl: String? = null
        private var headers: Headers.Builder = Headers.builder()
        private var queryParams: QueryParams.Builder = QueryParams.builder()
        private var responseValidation: Boolean = false
        private var timeout: Timeout = Timeout.default()
        private var maxRetries: Int = 2
        private var logLevel: LogLevel = LogLevel.fromEnv()
        private var openTelemetry: OpenTelemetryOptions = OpenTelemetryOptions.none()
        private var openTelemetryProviderName: String = "anthropic"
        private var openTelemetryPlatform: String? = null
        private var credentialResult: CredentialResult? = null
        private var webhookKey: String? = null

        @JvmSynthetic
        internal fun from(clientOptions: ClientOptions) = apply {
            httpClient = clientOptions.originalHttpClient
            interceptors = clientOptions.interceptors.toMutableList()
            checkJacksonVersionCompatibility = clientOptions.checkJacksonVersionCompatibility
            backend = clientOptions.backend
            jsonMapper = clientOptions.jsonMapper
            streamHandlerExecutor = clientOptions.streamHandlerExecutor
            sleeper = clientOptions.sleeper
            clock = clientOptions.clock
            baseUrl = clientOptions.baseUrl
            headers = clientOptions.headers.toBuilder()
            queryParams = clientOptions.queryParams.toBuilder()
            responseValidation = clientOptions.responseValidation
            timeout = clientOptions.timeout
            maxRetries = clientOptions.maxRetries
            logLevel = clientOptions.logLevel
            openTelemetry = clientOptions.openTelemetry
            openTelemetryProviderName = clientOptions.openTelemetryProviderName
            openTelemetryPlatform = clientOptions.openTelemetryPlatform
            webhookKey = clientOptions.webhookKey
            credentialResult = clientOptions.credentialResult
        }

        /**
         * The HTTP client to use in the SDK.
         *
         * Use the one published in `anthropic-java-client-okhttp` or implement your own.
         *
         * This class takes ownership of the client and closes it when closed.
         */
        fun httpClient(httpClient: HttpClient) = apply {
            this.httpClient = PhantomReachableClosingHttpClient(httpClient)
        }

        /**
         * Wraps the HTTP client using the given [interceptors].
         *
         * The interceptors are applied to every request, including retries, in order.
         *
         * Each interceptor receives an HTTP client that delegates to the next interceptor, and the
         * last interceptor receives the underlying HTTP client.
         *
         * Interceptors observe requests in the canonical Anthropic API shape, with the client's
         * default headers and first-party credentials (`Authorization`) already applied, and they
         * observe responses normalized to the Anthropic shape (e.g. Bedrock's binary event stream
         * is decoded to SSE). Backend-specific adaptation — URL rewriting, request signing (e.g.
         * AWS SigV4), and backend credentials — happens below the interceptors and is not visible
         * to them.
         *
         * Because credentials are applied above the interceptors, an interceptor that wants to
         * replace them must use `replaceHeaders`, not `putHeader` (which would append a duplicate
         * header). Interceptors that forward request data to external systems must redact these
         * headers.
         */
        fun interceptors(interceptors: List<Interceptor>) = apply {
            this.interceptors = interceptors.toMutableList()
        }

        /** Adds a single [Interceptor] to [interceptors]. */
        fun addInterceptor(interceptor: Interceptor) = apply { interceptors.add(interceptor) }

        /**
         * Whether to throw an exception if any of the Jackson versions detected at runtime are
         * incompatible with the SDK's minimum supported Jackson version (2.13.4).
         *
         * Defaults to true. Use extreme caution when disabling this option. There is no guarantee
         * that the SDK will work correctly when using an incompatible Jackson version.
         */
        fun checkJacksonVersionCompatibility(checkJacksonVersionCompatibility: Boolean) = apply {
            this.checkJacksonVersionCompatibility = checkJacksonVersionCompatibility
        }

        /**
         * The backend that the HTTP client sends through. The client asks it what the service
         * behind it supports, such as [Backend.supportsMessageThreads].
         *
         * A client builder sets it. It does not change where or how requests are sent: that is up
         * to the HTTP client.
         */
        @JvmSynthetic fun backend(backend: Backend) = apply { this.backend = backend }

        /**
         * The Jackson JSON mapper to use for serializing and deserializing JSON.
         *
         * Defaults to [com.anthropic.core.jsonMapper]. The default is usually sufficient and rarely
         * needs to be overridden.
         */
        fun jsonMapper(jsonMapper: JsonMapper) = apply { this.jsonMapper = jsonMapper }

        /**
         * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
         *
         * Defaults to a dedicated cached thread pool.
         *
         * This class takes ownership of the executor and shuts it down, if possible, when closed.
         */
        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor =
                if (streamHandlerExecutor is ExecutorService)
                    PhantomReachableExecutorService(streamHandlerExecutor)
                else streamHandlerExecutor
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
        fun sleeper(sleeper: Sleeper) = apply { this.sleeper = PhantomReachableSleeper(sleeper) }

        /**
         * The clock to use for operations that require timing, like retries.
         *
         * This is primarily useful for using a fake clock in tests.
         *
         * Defaults to [Clock.systemUTC].
         */
        fun clock(clock: Clock) = apply { this.clock = clock }

        /**
         * The base URL to use for every request.
         *
         * Defaults to the production environment: `https://api.anthropic.com`.
         */
        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }

        /** Alias for calling [Builder.baseUrl] with `baseUrl.orElse(null)`. */
        fun baseUrl(baseUrl: Optional<String>) = baseUrl(baseUrl.getOrNull())

        /**
         * Whether to call `validate` on every response before returning it.
         *
         * Setting this to `true` is _not_ forwards compatible with new types from the API for
         * existing fields.
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
         * The level at which to log request and response information.
         *
         * [fromEnv] will set the level from environment variables. See [LogLevel.fromEnv].
         *
         * Defaults to [LogLevel.fromEnv].
         */
        fun logLevel(logLevel: LogLevel) = apply { this.logLevel = logLevel }

        /**
         * OpenTelemetry tracing of this client's API calls. Each call emits a span when the
         * application has registered a tracer provider with [GlobalOpenTelemetry], or has set
         * [OpenTelemetryOptions.tracerProvider].
         *
         * [fromEnv] sets the options that a system property or an environment variable sets. See
         * [OpenTelemetryOptions.fromEnv].
         */
        fun openTelemetry(openTelemetry: OpenTelemetryOptions) = apply {
            this.openTelemetry = openTelemetry
        }

        /**
         * Enables or disables the spans of this client's API calls.
         *
         * A short form of [openTelemetry] with [OpenTelemetryOptions.ofEnabled].
         */
        fun openTelemetry(enabled: Boolean) = openTelemetry(OpenTelemetryOptions.ofEnabled(enabled))

        /**
         * The `gen_ai.provider.name` attribute of this client's spans. The client builder sets it
         * from its backend. See [com.anthropic.backends.Backend.openTelemetryProviderName].
         */
        fun openTelemetryProviderName(openTelemetryProviderName: String) = apply {
            this.openTelemetryProviderName = openTelemetryProviderName
        }

        /**
         * The platform in the instrumentation scope of this client's spans. The client of another
         * platform sets the platform's name, and its spans then have the scope
         * `com.anthropic.sdk.java.<platform>`, in place of `com.anthropic.sdk.java`.
         */
        fun openTelemetryPlatform(openTelemetryPlatform: String?) = apply {
            this.openTelemetryPlatform = openTelemetryPlatform
        }

        /**
         * Alias for calling [Builder.openTelemetryPlatform] with
         * `openTelemetryPlatform.orElse(null)`.
         */
        fun openTelemetryPlatform(openTelemetryPlatform: Optional<String>) =
            openTelemetryPlatform(openTelemetryPlatform.getOrNull())

        @JvmSynthetic
        internal fun credentials(credentials: CredentialResult?) = apply {
            this.credentialResult = credentials
        }

        fun webhookKey(webhookKey: String?) = apply { this.webhookKey = webhookKey }

        /** Alias for calling [Builder.webhookKey] with `webhookKey.orElse(null)`. */
        fun webhookKey(webhookKey: Optional<String>) = webhookKey(webhookKey.getOrNull())

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
         * Updates configuration using system properties and environment variables.
         *
         * See this table for the available options:
         *
         * |Setter      |System property              |Environment variable           |Required|Default value|
         * |------------|-----------------------------|-------------------------------|--------|-------------|
         * |`webhookKey`|`anthropic.webhookSigningKey`|`ANTHROPIC_WEBHOOK_SIGNING_KEY`|false   |-            |
         *
         * System properties take precedence over environment variables.
         *
         * Also sets the options of `openTelemetry` that a system property or an environment
         * variable sets, and leaves its other options as they are. See
         * [OpenTelemetryOptions.fromEnv].
         */
        fun fromEnv() = apply {
            logLevel(LogLevel.fromEnv())
            openTelemetry(openTelemetry.withEnv())
            (System.getProperty("anthropic.webhookSigningKey")
                    ?: System.getenv("ANTHROPIC_WEBHOOK_SIGNING_KEY"))
                ?.let { webhookKey(it) }
            System.getenv("ANTHROPIC_CUSTOM_HEADERS")?.let { customHeadersEnv ->
                for (line in customHeadersEnv.split("\n")) {
                    val colon = line.indexOf(':')
                    if (colon >= 0) {
                        putHeader(line.substring(0, colon).trim(), line.substring(colon + 1).trim())
                    }
                }
            }
        }

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
            val rawHttpClient = checkRequired("httpClient", httpClient)
            val streamHandlerExecutor =
                streamHandlerExecutor
                    ?: PhantomReachableExecutorService(
                        Executors.newCachedThreadPool(
                            object : ThreadFactory {

                                private val threadFactory: ThreadFactory =
                                    Executors.defaultThreadFactory()
                                private val count = AtomicLong(0)

                                override fun newThread(runnable: Runnable): Thread =
                                    threadFactory.newThread(runnable).also {
                                        it.name =
                                            "anthropic-stream-handler-thread-${count.getAndIncrement()}"
                                    }
                            }
                        )
                    )
            val sleeper = sleeper ?: PhantomReachableSleeper(DefaultSleeper())

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

            // The 1p auth layer wraps the interceptors so that they observe the logical
            // credentials, while 3p backend adaptation (URL rewriting, request signing, response
            // normalization) stays below them in the transport.
            val interceptedHttpClient =
                interceptors.intercept(
                    LoggingHttpClient.builder()
                        // The tracing client is innermost: on every attempt it restores the call's
                        // context, which async steps above it lose, and adds the `traceparent`
                        // header before the transport signs the request.
                        .httpClient(
                            // A performance optimization: with spans off, the tracing client would
                            // pass every request through unchanged.
                            if (openTelemetry.traces().enabled()) TracingHttpClient(rawHttpClient)
                            else rawHttpClient
                        )
                        .clock(clock)
                        .level(logLevel)
                        .build()
                )
            val credentialResult = credentialResult
            val authorizedHttpClient =
                when (credentialResult) {
                    null -> interceptedHttpClient
                    is CredentialResult.Token ->
                        AuthorizingHttpClient.builder()
                            .httpClient(interceptedHttpClient)
                            .tokenProvider(credentialResult.provider)
                            .workspaceId(credentialResult.workspaceId)
                            .build()
                    is CredentialResult.Static ->
                        HeaderAuthHttpClient(interceptedHttpClient, credentialResult.credential)
                }

            return ClientOptions(
                rawHttpClient,
                RetryingHttpClient.builder()
                    .httpClient(authorizedHttpClient)
                    .sleeper(sleeper)
                    .clock(clock)
                    .maxRetries(maxRetries)
                    .build(),
                interceptors.toImmutable(),
                checkJacksonVersionCompatibility,
                backend,
                jsonMapper,
                streamHandlerExecutor,
                sleeper,
                clock,
                baseUrl,
                headers.build(),
                queryParams.build(),
                responseValidation,
                timeout,
                maxRetries,
                logLevel,
                openTelemetry,
                openTelemetryProviderName,
                openTelemetryPlatform,
                webhookKey,
                credentialResult,
            )
        }
    }

    /**
     * Whether the service that the HTTP client sends to may be sent the `thread` field of the
     * Messages API. Only a backend says no.
     */
    @JvmSynthetic
    internal fun supportsMessageThreads(): Boolean = backend?.supportsMessageThreads() ?: true

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
        (streamHandlerExecutor as? ExecutorService)?.shutdown()
        sleeper.close()
    }
}
