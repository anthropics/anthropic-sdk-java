package com.anthropic.client.okhttp

import com.anthropic.backends.Backend
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.Timeout
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.http.Headers
import kotlinx.kmp.util.core.http.HttpClient
import kotlinx.kmp.util.core.http.HttpMethod
import kotlinx.kmp.util.core.http.HttpRequest
import kotlinx.kmp.util.core.http.HttpRequestBody
import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.errors.AnthropicIoException
import java.io.IOException
import java.io.InputStream
import java.net.Proxy
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.time.Duration
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.BufferedSink

class OkHttpClient
internal constructor(
    @JvmSynthetic internal val okHttpClient: okhttp3.OkHttpClient,
    private val backend: Backend,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        val preparedRequest = prepareRequest(request)
        val call = newCall(preparedRequest, requestOptions)

        return try {
            backend.prepareResponse(call.execute().toResponse())
        } catch (e: IOException) {
            throw AnthropicIoException("Request failed", e)
        } finally {
            preparedRequest.body?.close()
        }
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val preparedRequest = prepareRequest(request)
        val future = CompletableFuture<HttpResponse>()

        val call = newCall(preparedRequest, requestOptions)
        call.enqueue(
            object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    future.complete(backend.prepareResponse(response.toResponse()))
                }

                override fun onFailure(call: Call, e: IOException) {
                    future.completeExceptionally(AnthropicIoException("Request failed", e))
                }
            }
        )

        future.whenComplete { _, e ->
            if (e is CancellationException) {
                call.cancel()
            }
            preparedRequest.body?.close()
        }

        return future
    }

    override suspend fun executeSuspend(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse {
        val preparedRequest = prepareRequest(request)
        val call = newCall(preparedRequest, requestOptions)

        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                call.cancel()
                preparedRequest.body?.close()
            }
            call.enqueue(
                object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        preparedRequest.body?.close()
                        continuation.resume(backend.prepareResponse(response.toResponse()))
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        preparedRequest.body?.close()
                        continuation.resumeWithException(AnthropicIoException("Request failed", e))
                    }
                }
            )
        }
    }

    override fun close() {
        backend.close()
        okHttpClient.dispatcher.executorService.shutdown()
        okHttpClient.connectionPool.evictAll()
        okHttpClient.cache?.close()
    }

    private fun prepareRequest(request: HttpRequest): HttpRequest {
        val preparedRequest = backend.prepareRequest(request)
        val resolvedRequest = preparedRequest.resolveUrl()
        val authorizedRequest = backend.authorizeRequest(resolvedRequest)

        return authorizedRequest
    }

    private fun newCall(request: HttpRequest, requestOptions: RequestOptions): Call {
        val clientBuilder = okHttpClient.newBuilder()

        val logLevel =
            when (System.getenv("ANTHROPIC_LOG")?.lowercase()) {
                "info" -> HttpLoggingInterceptor.Level.BASIC
                "debug" -> HttpLoggingInterceptor.Level.BODY
                else -> null
            }
        if (logLevel != null) {
            clientBuilder.addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(logLevel).apply {
                    redactHeader("X-Api-Key")
                    redactHeader("Authorization")
                }
            )
        }

        requestOptions.timeout?.let {
            clientBuilder
                .connectTimeout(it.connect().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                .readTimeout(it.read().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                .writeTimeout(it.write().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                .callTimeout(it.request().inWholeMilliseconds, TimeUnit.MILLISECONDS)
        }

        val client = clientBuilder.build()
        return client.newCall(request.toRequest(client))
    }

    private fun HttpRequest.toRequest(client: okhttp3.OkHttpClient): Request {
        var body: RequestBody? = body?.toRequestBody()
        if (body == null && requiresBody(method)) {
            body = "".toRequestBody()
        }
        val builder = Request.Builder().url(baseUrl ?: "").method(method.value, body)  // .value matches enum(value) and Spring pattern
        headers.names().forEach { name ->
            headers.values(name).forEach { builder.addHeader(name, it) }
        }

        if (
            !headers.names().contains("X-Stainless-Read-Timeout") && client.readTimeoutMillis != 0
        ) {
            builder.addHeader(
                "X-Stainless-Read-Timeout",
                (client.readTimeoutMillis.toLong() / 1000).toString(),
            )
        }
        if (!headers.names().contains("X-Stainless-Timeout") && client.callTimeoutMillis != 0) {
            builder.addHeader(
                "X-Stainless-Timeout",
                (client.callTimeoutMillis.toLong() / 1000).toString(),
            )
        }

        return builder.build()
    }

    /** `OkHttpClient` always requires a request body for some methods. */
    private fun requiresBody(method: HttpMethod): Boolean =
        when (method) {
            HttpMethod.POST,
            HttpMethod.PUT,
            HttpMethod.PATCH -> true
            else -> false
        }

    private fun HttpRequest.resolveUrl(): HttpRequest {
        return toBuilder().baseUrl(toUrl()).build()
    }

    private fun HttpRequest.toUrl(): String {
        val builder = (baseUrl ?: backend.baseUrl()).toHttpUrl().newBuilder()

        pathSegments.forEach(builder::addPathSegment)
        queryParams.keys().forEach { key ->
            queryParams.values(key).forEach { builder.addQueryParameter(key, it) }
        }

        return builder.toString()
    }

    private fun HttpRequestBody.toRequestBody(): RequestBody {
        val mediaType = contentType()?.toMediaType()
        val length = contentLength()

        return object : RequestBody() {
            override fun contentType(): MediaType? = mediaType

            override fun contentLength(): Long = length

            override fun isOneShot(): Boolean = !repeatable()

            override fun writeTo(sink: BufferedSink) = this@toRequestBody.writeTo(sink)
        }
    }

    private fun Response.toResponse(): HttpResponse {
        val headers = headers.toHeaders()
        val statusCode = code
        // Eagerly buffer the body so it can be read multiple times (e.g. error handler + test assertion)
        val bodyBytes = body?.bytes() ?: byteArrayOf()
        body?.close()

        return object : HttpResponse {
            override fun statusCode(): Int = statusCode

            override fun headers(): Headers = headers

            override fun body(): okio.BufferedSource = okio.Buffer().write(bodyBytes)

            override fun close() {}
        }
    }

    private fun okhttp3.Headers.toHeaders(): Headers {
        val headersBuilder = Headers.builder()
        forEach { (name, value) -> headersBuilder.put(name, value) }
        return headersBuilder.build()
    }

    companion object {
        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var timeout: Timeout = Timeout.default()
        private var proxy: Proxy? = null
        private var backend: Backend? = null
        private var maxIdleConnections: Int? = null
        private var keepAliveDuration: Duration? = null
        private var dispatcherExecutorService: ExecutorService? = null
        private var sslSocketFactory: SSLSocketFactory? = null
        private var trustManager: X509TrustManager? = null
        private var hostnameVerifier: HostnameVerifier? = null

        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        fun proxy(proxy: Proxy?) = apply { this.proxy = proxy }

        fun backend(backend: Backend) = apply { this.backend = backend }

        /**
         * Sets the maximum number of idle connections kept by the underlying [ConnectionPool].
         *
         * If this is set, then [keepAliveDuration] must also be set.
         *
         * If unset, then OkHttp's default is used.
         */
        fun maxIdleConnections(maxIdleConnections: Int?) = apply {
            this.maxIdleConnections = maxIdleConnections
        }

        /**
         * Sets the keep-alive duration for idle connections in the underlying [ConnectionPool].
         *
         * If this is set, then [maxIdleConnections] must also be set.
         *
         * If unset, then OkHttp's default is used.
         */
        fun keepAliveDuration(keepAliveDuration: Duration?) = apply {
            this.keepAliveDuration = keepAliveDuration
        }

        fun dispatcherExecutorService(dispatcherExecutorService: ExecutorService?) = apply {
            this.dispatcherExecutorService = dispatcherExecutorService
        }

        fun sslSocketFactory(sslSocketFactory: SSLSocketFactory?) = apply {
            this.sslSocketFactory = sslSocketFactory
        }

        fun trustManager(trustManager: X509TrustManager?) = apply {
            this.trustManager = trustManager
        }

        fun hostnameVerifier(hostnameVerifier: HostnameVerifier?) = apply {
            this.hostnameVerifier = hostnameVerifier
        }

        fun build(): OkHttpClient =
            OkHttpClient(
                okhttp3.OkHttpClient.Builder()
                    // `RetryingHttpClient` handles retries if the user enabled them.
                    .retryOnConnectionFailure(false)
                    .pingInterval(1, TimeUnit.MINUTES)
                    .connectTimeout(timeout.connect().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                    .readTimeout(timeout.read().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                    .writeTimeout(timeout.write().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                    .callTimeout(timeout.request().inWholeMilliseconds, TimeUnit.MILLISECONDS)
                    .proxy(proxy)
                    .apply {
                        dispatcherExecutorService?.let { dispatcher(Dispatcher(it)) }

                        val maxIdleConnections = maxIdleConnections
                        val keepAliveDuration = keepAliveDuration
                        if (maxIdleConnections != null && keepAliveDuration != null) {
                            connectionPool(
                                ConnectionPool(
                                    maxIdleConnections,
                                    keepAliveDuration.inWholeNanoseconds,
                                    TimeUnit.NANOSECONDS,
                                )
                            )
                        } else {
                            check((maxIdleConnections != null) == (keepAliveDuration != null)) {
                                "Both or none of `maxIdleConnections` and `keepAliveDuration` must be set, but only one was set"
                            }
                        }

                        val sslSocketFactory = sslSocketFactory
                        val trustManager = trustManager
                        if (sslSocketFactory != null && trustManager != null) {
                            sslSocketFactory(sslSocketFactory, trustManager)
                        } else {
                            check((sslSocketFactory != null) == (trustManager != null)) {
                                "Both or none of `sslSocketFactory` and `trustManager` must be set, but only one was set"
                            }
                        }

                        hostnameVerifier?.let(::hostnameVerifier)
                    }
                    .build()
                    .apply {
                        // We usually make all our requests to the same host so it makes sense to
                        // raise the per-host limit to the overall limit.
                        dispatcher.maxRequestsPerHost = dispatcher.maxRequests
                    },
                checkRequired("backend", backend),
            )
    }
}
