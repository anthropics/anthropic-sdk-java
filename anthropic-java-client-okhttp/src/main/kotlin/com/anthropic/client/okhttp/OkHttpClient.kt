package com.anthropic.client.okhttp

import com.anthropic.backends.Backend
import com.anthropic.core.RequestOptions
import com.anthropic.core.Timeout
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.ProxyAuthenticator
import com.anthropic.errors.AnthropicIoException
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Proxy
import java.time.Duration
import java.util.Optional
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager
import kotlin.jvm.optionals.getOrNull
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.BufferedSink
import okio.buffer
import okio.sink

class OkHttpClient
private constructor(
    private val timeout: Timeout,
    private val proxy: Proxy?,
    private val backend: Backend,
    private val proxyAuthenticator: ProxyAuthenticator?,
    private val maxIdleConnections: Int?,
    private val keepAliveDuration: Duration?,
    private val dispatcherExecutorService: ExecutorService?,
    private val sslSocketFactory: SSLSocketFactory?,
    private val trustManager: X509TrustManager?,
    private val hostnameVerifier: HostnameVerifier?,
) : HttpClient {

    @get:JvmSynthetic
    internal val okHttpClient: okhttp3.OkHttpClient =
        okhttp3.OkHttpClient.Builder()
            // `RetryingHttpClient` handles retries if the user enabled them.
            .retryOnConnectionFailure(false)
            .pingInterval(Duration.ofMinutes(1))
            .connectTimeout(timeout.connect())
            .readTimeout(timeout.read())
            .writeTimeout(timeout.write())
            .callTimeout(timeout.request())
            .proxy(proxy)
            .apply {
                proxyAuthenticator?.let { auth ->
                    proxyAuthenticator { route, response ->
                        auth
                            .authenticate(
                                route?.proxy ?: Proxy.NO_PROXY,
                                response.request.toHttpRequest(),
                                response.toHttpResponse(call = null),
                            )
                            .getOrNull()
                            ?.toRequest(client = null)
                    }
                }

                dispatcherExecutorService?.let { dispatcher(Dispatcher(it)) }

                if (maxIdleConnections != null && keepAliveDuration != null) {
                    connectionPool(
                        ConnectionPool(
                            maxIdleConnections,
                            keepAliveDuration.toNanos(),
                            TimeUnit.NANOSECONDS,
                        )
                    )
                } else {
                    check((maxIdleConnections != null) == (keepAliveDuration != null)) {
                        "Both or none of `maxIdleConnections` and `keepAliveDuration` must be set, but only one was set"
                    }
                }

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
            }

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        val preparedRequest = prepareRequest(request)
        val call = newCall(preparedRequest, requestOptions)

        return try {
            prepareResponse(call.execute().toHttpResponse(call))
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
                    // OkHttp doesn't call `onFailure` when this throws, so a throw would leave the
                    // future incomplete.
                    val preparedResponse =
                        try {
                            prepareResponse(response.toHttpResponse(call))
                        } catch (e: IOException) {
                            onFailure(call, e)
                            return
                        } catch (e: Throwable) {
                            future.completeExceptionally(e)
                            return
                        }
                    if (!future.complete(preparedResponse)) {
                        // The future is already done, e.g. canceled, so nothing else will close the
                        // response.
                        preparedResponse.close()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    future.completeExceptionally(AnthropicIoException("Request failed", e))
                }
            }
        )

        // Not a user callback; cancellation and body cleanup must not depend on an executor.
        @Suppress("ForbiddenMethodCall")
        future.whenComplete { _, e ->
            if (e is CancellationException) {
                call.cancel()
            }
            preparedRequest.body?.close()
        }

        return future
    }

    override fun close() {
        backend.close()
        okHttpClient.dispatcher.executorService.shutdown()
        okHttpClient.connectionPool.evictAll()
        okHttpClient.cache?.close()
    }

    /** Returns [Backend.prepareResponse] of [response], closing [response] if that throws. */
    private fun prepareResponse(response: HttpResponse): HttpResponse =
        try {
            backend.prepareResponse(response)
        } catch (e: Throwable) {
            try {
                response.close()
            } catch (closeError: Throwable) {
                e.addSuppressed(closeError)
            }
            throw e
        }

    private fun prepareRequest(request: HttpRequest): HttpRequest {
        val preparedRequest = backend.prepareRequest(request)
        val resolvedRequest = preparedRequest.resolveUrl()
        val authorizedRequest = backend.authorizeRequest(resolvedRequest)

        return authorizedRequest
    }

    private fun newCall(request: HttpRequest, requestOptions: RequestOptions): Call {
        val clientBuilder = okHttpClient.newBuilder()

        requestOptions.timeout?.let {
            clientBuilder
                .connectTimeout(it.connect())
                .readTimeout(it.read())
                .writeTimeout(it.write())
                .callTimeout(it.request())
        }

        val client = clientBuilder.build()
        return client.newCall(request.toRequest(client))
    }

    fun toBuilder(): Builder = Builder().from(this)

    private fun HttpRequest.toRequest(client: okhttp3.OkHttpClient): Request {
        var body: RequestBody? = body?.toRequestBody()
        if (body == null && requiresBody(method)) {
            body = "".toRequestBody()
        }
        val builder = Request.Builder().url(baseUrl ?: "").method(method.name, body)
        headers.names().forEach { name ->
            headers.values(name).forEach { builder.addHeader(name, it) }
        }

        if (
            !headers.names().contains("X-Stainless-Read-Timeout") && client.readTimeoutMillis != 0
        ) {
            builder.addHeader(
                "X-Stainless-Read-Timeout",
                Duration.ofMillis(client.readTimeoutMillis.toLong()).seconds.toString(),
            )
        }
        if (!headers.names().contains("X-Stainless-Timeout") && client.callTimeoutMillis != 0) {
            builder.addHeader(
                "X-Stainless-Timeout",
                Duration.ofMillis(client.callTimeoutMillis.toLong()).seconds.toString(),
            )
        }

        return builder.build()
    }

    private fun HttpRequest.resolveUrl(): HttpRequest {
        return toBuilder().baseUrl(toUrl()).build()
    }

    companion object {
        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var timeout: Timeout = Timeout.default()
        private var proxy: Proxy? = null
        private var backend: Backend? = null

        private var proxyAuthenticator: ProxyAuthenticator? = null
        private var maxIdleConnections: Int? = null
        private var keepAliveDuration: Duration? = null
        private var dispatcherExecutorService: ExecutorService? = null
        private var sslSocketFactory: SSLSocketFactory? = null
        private var trustManager: X509TrustManager? = null
        private var hostnameVerifier: HostnameVerifier? = null

        @JvmSynthetic
        internal fun from(okHttpClient: OkHttpClient) = apply {
            timeout = okHttpClient.timeout
            proxy = okHttpClient.proxy
            backend = okHttpClient.backend
            proxyAuthenticator = okHttpClient.proxyAuthenticator
            maxIdleConnections = okHttpClient.maxIdleConnections
            keepAliveDuration = okHttpClient.keepAliveDuration
            dispatcherExecutorService = okHttpClient.dispatcherExecutorService
            sslSocketFactory = okHttpClient.sslSocketFactory
            trustManager = okHttpClient.trustManager
            hostnameVerifier = okHttpClient.hostnameVerifier
        }

        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        fun proxy(proxy: Proxy?) = apply { this.proxy = proxy }

        /** Alias for calling [Builder.proxy] with `proxy.orElse(null)`. */
        fun proxy(proxy: Optional<Proxy>) = proxy(proxy.getOrNull())

        fun backend(backend: Backend) = apply { this.backend = backend }

        fun proxyAuthenticator(proxyAuthenticator: ProxyAuthenticator?) = apply {
            this.proxyAuthenticator = proxyAuthenticator
        }

        /**
         * Alias for calling [Builder.proxyAuthenticator] with `proxyAuthenticator.orElse(null)`.
         */
        fun proxyAuthenticator(proxyAuthenticator: Optional<ProxyAuthenticator>) =
            proxyAuthenticator(proxyAuthenticator.getOrNull())

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
         * Alias for calling [Builder.maxIdleConnections] with `maxIdleConnections.orElse(null)`.
         */
        fun maxIdleConnections(maxIdleConnections: Optional<Int>) =
            maxIdleConnections(maxIdleConnections.getOrNull())

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

        /** Alias for calling [Builder.keepAliveDuration] with `keepAliveDuration.orElse(null)`. */
        fun keepAliveDuration(keepAliveDuration: Optional<Duration>) =
            keepAliveDuration(keepAliveDuration.getOrNull())

        fun dispatcherExecutorService(dispatcherExecutorService: ExecutorService?) = apply {
            this.dispatcherExecutorService = dispatcherExecutorService
        }

        /**
         * Alias for calling [Builder.dispatcherExecutorService] with
         * `dispatcherExecutorService.orElse(null)`.
         */
        fun dispatcherExecutorService(dispatcherExecutorService: Optional<ExecutorService>) =
            dispatcherExecutorService(dispatcherExecutorService.getOrNull())

        fun sslSocketFactory(sslSocketFactory: SSLSocketFactory?) = apply {
            this.sslSocketFactory = sslSocketFactory
        }

        /** Alias for calling [Builder.sslSocketFactory] with `sslSocketFactory.orElse(null)`. */
        fun sslSocketFactory(sslSocketFactory: Optional<SSLSocketFactory>) =
            sslSocketFactory(sslSocketFactory.getOrNull())

        fun trustManager(trustManager: X509TrustManager?) = apply {
            this.trustManager = trustManager
        }

        /** Alias for calling [Builder.trustManager] with `trustManager.orElse(null)`. */
        fun trustManager(trustManager: Optional<X509TrustManager>) =
            trustManager(trustManager.getOrNull())

        fun hostnameVerifier(hostnameVerifier: HostnameVerifier?) = apply {
            this.hostnameVerifier = hostnameVerifier
        }

        /** Alias for calling [Builder.hostnameVerifier] with `hostnameVerifier.orElse(null)`. */
        fun hostnameVerifier(hostnameVerifier: Optional<HostnameVerifier>) =
            hostnameVerifier(hostnameVerifier.getOrNull())

        fun build(): OkHttpClient =
            OkHttpClient(
                timeout,
                proxy,
                checkRequired("backend", backend),
                proxyAuthenticator,
                maxIdleConnections,
                keepAliveDuration,
                dispatcherExecutorService,
                sslSocketFactory,
                trustManager,
                hostnameVerifier,
            )
    }
}

private fun HttpRequest.toRequest(client: okhttp3.OkHttpClient?): Request {
    var body: RequestBody? = body?.toRequestBody()
    if (body == null && requiresBody(method)) {
        body = "".toRequestBody()
    }

    val builder = Request.Builder().url(toUrl()).method(method.name, body)
    headers.names().forEach { name -> headers.values(name).forEach { builder.addHeader(name, it) } }

    if (client != null) {
        if (
            !headers.names().contains("X-Stainless-Read-Timeout") && client.readTimeoutMillis != 0
        ) {
            builder.addHeader(
                "X-Stainless-Read-Timeout",
                Duration.ofMillis(client.readTimeoutMillis.toLong()).seconds.toString(),
            )
        }
        if (!headers.names().contains("X-Stainless-Timeout") && client.callTimeoutMillis != 0) {
            builder.addHeader(
                "X-Stainless-Timeout",
                Duration.ofMillis(client.callTimeoutMillis.toLong()).seconds.toString(),
            )
        }
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

private fun HttpRequest.toUrl(): String {
    val builder = baseUrl!!.toHttpUrl().newBuilder()
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

        override fun writeTo(sink: BufferedSink) = writeTo(sink.outputStream())
    }
}

private fun Request.toHttpRequest(): HttpRequest {
    val builder = HttpRequest.builder().method(HttpMethod.valueOf(method)).baseUrl(url.toBaseUrl())
    url.pathSegments.forEach(builder::addPathSegment)
    url.queryParameterNames.forEach { name ->
        url.queryParameterValues(name).filterNotNull().forEach { builder.putQueryParam(name, it) }
    }
    headers.forEach { (name, value) -> builder.putHeader(name, value) }
    body?.let { builder.body(it.toHttpRequestBody()) }
    return builder.build()
}

private fun HttpUrl.toBaseUrl(): String = buildString {
    append(scheme).append("://").append(host)
    if (port != HttpUrl.defaultPort(scheme)) {
        append(":").append(port)
    }
}

private fun RequestBody.toHttpRequestBody(): HttpRequestBody {
    val mediaType = contentType()?.toString()
    val length = contentLength()
    val isOneShot = isOneShot()
    val source = this
    return object : HttpRequestBody {
        override fun contentType(): String? = mediaType

        override fun contentLength(): Long = length

        override fun repeatable(): Boolean = !isOneShot

        override fun writeTo(outputStream: OutputStream) {
            val sink = outputStream.sink().buffer()
            source.writeTo(sink)
            sink.flush()
        }

        override fun close() {}
    }
}

/**
 * @param call the call to cancel on a close that races a read, or `null` if it can't be canceled.
 */
private fun Response.toHttpResponse(call: Call?): HttpResponse {
    val headers = headers.toHeaders()
    val body = body?.let { ResponseBodyInputStream(it, call) }

    return object : HttpResponse {
        override fun statusCode(): Int = code

        override fun headers(): Headers = headers

        override fun body(): InputStream = checkNotNull(body) { "Response has no body" }

        override fun close() {
            body?.close()
        }
    }
}

/**
 * A response body that one thread can close while another thread reads it.
 *
 * OkHttp's own close drains the rest of the body so the connection can be reused. On HTTP/1.1 that
 * reads the socket source, and okio doesn't allow two threads to read one source at once, so a
 * close that races a read would throw and leave the read blocked. Such a close cancels [call]
 * instead, which fails the read, and the reading thread closes the body once its read unwinds. A
 * close with no read in flight closes the body directly, which keeps the connection reusable.
 */
private class ResponseBodyInputStream(private val body: ResponseBody, private val call: Call?) :
    InputStream() {

    private val delegate = body.byteStream()
    private val lock = Any()
    // Guarded by `lock`. No read starts once `closed` is set, so exactly one of `close` or the last
    // in-flight read closes the body.
    private var closed = false
    private var readsInFlight = 0

    override fun read(): Int = trackRead { delegate.read() }

    override fun read(b: ByteArray, off: Int, len: Int): Int = trackRead {
        delegate.read(b, off, len)
    }

    override fun available(): Int = trackRead { delegate.available() }

    private inline fun <T> trackRead(read: () -> T): T {
        synchronized(lock) {
            if (closed) {
                throw IOException("closed")
            }
            readsInFlight++
        }
        try {
            return read()
        } finally {
            val closeBody = synchronized(lock) { --readsInFlight == 0 && closed }
            if (closeBody) {
                body.close()
            }
        }
    }

    override fun close() {
        val readInFlight =
            synchronized(lock) {
                if (closed) {
                    return
                }
                closed = true
                readsInFlight > 0
            }
        if (readInFlight) {
            call?.cancel()
        } else {
            body.close()
        }
    }
}

private fun okhttp3.Headers.toHeaders(): Headers {
    val headersBuilder = Headers.builder()
    forEach { (name, value) -> headersBuilder.put(name, value) }
    return headersBuilder.build()
}
