package com.anthropic.client.okhttp

import com.anthropic.backends.Backend
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.http.HttpResponse
import com.anthropic.errors.AnthropicIoException
import java.io.IOException
import java.io.InputStream
import java.net.Proxy
import java.time.Duration
import java.util.concurrent.CompletableFuture
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.BufferedSink

class OkHttpClient private constructor(
    private val okHttpClient: okhttp3.OkHttpClient,
    private val baseUrl: HttpUrl,

    /**
     * [Backend] (optional) that may be required to authenticate and authorize
     * requests to an Anthropic service or adapt requests and responses from
     * different backends to the default Anthropic API.
     */
    private val backend: Backend?)
    : HttpClient {

    override fun execute(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse {
        val preparedRequest = backend?.prepareRequest(request) ?: request
        val signRequest = preparedRequest.resolveUrl()
        val signedRequest = backend?.signRequest(signRequest) ?: signRequest
        val call = newCall(signedRequest, requestOptions)

        return try {
            val response = call.execute().toResponse()

            backend?.prepareResponse(response) ?: response
        } catch (e: IOException) {
            throw AnthropicIoException("Request failed", e)
        } finally {
            signedRequest.body?.close()
        }
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val preparedRequest = backend?.prepareRequest(request) ?: request
        val signRequest = preparedRequest.resolveUrl()
        val signedRequest = backend?.signRequest(signRequest) ?: signRequest
        val future = CompletableFuture<HttpResponse>()

        signedRequest.body?.run { future.whenComplete { _, _ -> close() } }

        newCall(signedRequest, requestOptions)
            .enqueue(
                object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        val httpResponse = response.toResponse()

                        future.complete(
                            backend?.prepareResponse(httpResponse)
                                ?: httpResponse)
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        future.completeExceptionally(AnthropicIoException("Request failed", e))
                    }
                }
            )

        return future
    }

    override fun close() {
        okHttpClient.dispatcher.executorService.shutdown()
        okHttpClient.connectionPool.evictAll()
        okHttpClient.cache?.close()
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

        val timeout = requestOptions.timeout
        if (timeout != null) {
            clientBuilder
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .callTimeout(if (timeout.seconds == 0L) timeout else timeout.plusSeconds(30))
        }

        val client = clientBuilder.build()
        return client.newCall(request.toRequest(client))
    }

    private fun HttpRequest.toRequest(client: okhttp3.OkHttpClient): Request {
        var body: RequestBody? = body?.toRequestBody()
        if (body == null && requiresBody(method)) {
            body = "".toRequestBody()
        }
        val builder = Request.Builder().url(url ?: "").method(method.name, body)
        headers.names().forEach { name ->
            headers.values(name).forEach { builder.header(name, it) }
        }

        if (
            !headers.names().contains("X-Stainless-Read-Timeout") && client.readTimeoutMillis != 0
        ) {
            builder.header(
                "X-Stainless-Read-Timeout",
                Duration.ofMillis(client.readTimeoutMillis.toLong()).seconds.toString()
            )
        }
        if (!headers.names().contains("X-Stainless-Timeout") && client.callTimeoutMillis != 0) {
            builder.header(
                "X-Stainless-Timeout",
                Duration.ofMillis(client.callTimeoutMillis.toLong()).seconds.toString()
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

    /**
     * Creates a new [HttpRequest] with the [HttpRequest.url] property resolved
     * from the base URL, credentials, path segments and query parameters. If
     * the URL is already set, it is not changed.
     *
     * @return The new request instance with the URL property set.
     */
    private fun HttpRequest.resolveUrl(): HttpRequest {
        return toBuilder().url(toUrl()).build()
    }

    private fun HttpRequest.toUrl(): String {
        url?.let {
            return it
        }

        val builder: HttpUrl.Builder =
            backend?.baseUrl()?.toHttpUrl()?.newBuilder()
                ?: baseUrl.newBuilder()

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

    private fun Response.toResponse(): HttpResponse {
        val headers = headers.toHeaders()

        return object : HttpResponse {
            override fun statusCode(): Int = code

            override fun headers(): Headers = headers

            override fun body(): InputStream = body!!.byteStream()

            override fun close() = body!!.close()
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

        private var baseUrl: HttpUrl? = null
        // The default timeout is 10 minutes.
        private var timeout: Duration = Duration.ofSeconds(600)
        private var proxy: Proxy? = null

        /**
         * [Backend] that may be required to authenticate and authorize
         * requests to an Anthropic service running on a different backend.
         */
        private var backend: Backend? = null

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl.toHttpUrl() }

        fun timeout(timeout: Duration) = apply { this.timeout = timeout }

        fun proxy(proxy: Proxy?) = apply { this.proxy = proxy }

        /**
         * Sets the backend to be used to manage credentials, prepare requests
         * and handle responses to an Anthropic model running on an alternative
         * backend service. Implementations of the [Backend] interface can
         * define the required behavior for a specific backend service.
         *
         * @param backend The backend to be used. If connecting to the default
         *     Anthropic backend service, a custom backend is not required.
         */
        fun backend(backend: Backend?) = apply { this.backend = backend }

        fun build(): OkHttpClient =
            OkHttpClient(
                okhttp3.OkHttpClient.Builder()
                    .connectTimeout(timeout)
                    .readTimeout(timeout)
                    .writeTimeout(timeout)
                    .callTimeout(if (timeout.seconds == 0L) timeout else timeout.plusSeconds(30))
                    .proxy(proxy)
                    .build(),
                checkRequired("baseUrl", baseUrl),
                backend,
            )
    }
}
