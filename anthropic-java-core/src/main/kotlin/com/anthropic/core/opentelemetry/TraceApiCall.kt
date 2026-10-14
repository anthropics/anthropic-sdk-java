@file:JvmName("TraceApiCall")

package com.anthropic.core.opentelemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonField
import com.anthropic.core.LogLevel
import com.anthropic.core.Params
import com.anthropic.core.RequestOptions
import com.anthropic.core.closeWhenPhantomReachable
import com.anthropic.core.getPackageVersion
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.parseable
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicIoException
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.internal.telemetry.logTelemetryFailure
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.api.trace.TracerProvider
import io.opentelemetry.context.Context
import io.opentelemetry.context.ContextKey
import io.opentelemetry.context.propagation.TextMapPropagator
import java.io.IOException
import java.io.InputStream
import java.io.InterruptedIOException
import java.net.URI
import java.net.URISyntaxException
import java.util.Spliterator
import java.util.Spliterators
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.ExecutionException
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream
import java.util.stream.StreamSupport
import kotlin.jvm.optionals.getOrNull

/**
 * Runs [send], which sends the method's request with the client it gets and handles the response,
 * in a span when the client emits spans. The span starts with the span data of [params], as [send]
 * sends the request. It ends once the body is parsed (a streamed body: read or closed), the
 * response is closed unparsed, or the call fails.
 */
@JvmSynthetic
internal fun <T> traceApiCall(
    clientOptions: ClientOptions,
    params: Params,
    send: (ApiCallClient) -> HttpResponseFor<T>,
): HttpResponseFor<T> {
    val tracer = tracer(clientOptions) ?: return send(ApiCallClient(clientOptions))
    return traceApiCall(ApiCallClient(clientOptions, tracer, params::_spanData), send)
}

/**
 * [traceApiCall] for a method that started the span itself ([startApiCall]). [call] is `null` when
 * the client emits no span.
 */
@JvmSynthetic
internal fun <T> traceApiCall(
    clientOptions: ClientOptions,
    call: ApiCall?,
    send: (ApiCallClient) -> HttpResponseFor<T>,
): HttpResponseFor<T> {
    if (call == null) {
        return send(ApiCallClient(clientOptions))
    }
    return traceApiCall(ApiCallClient(clientOptions, call = call), send)
}

private fun <T> traceApiCall(
    client: ApiCallClient,
    send: (ApiCallClient) -> HttpResponseFor<T>,
): HttpResponseFor<T> {
    val response =
        try {
            send(client)
        } catch (e: Throwable) {
            client.call?.let { endApiCall(it, ApiCallOutcome.Failed(e)) }
            throw e
        }
    val call = client.call ?: return response
    return TracedHttpResponseFor(response, call)
}

/** [traceApiCall] for a method that returns the body unread: the span ends when [send] returns. */
@JvmSynthetic
internal fun traceRawApiCall(
    clientOptions: ClientOptions,
    params: Params,
    send: (ApiCallClient) -> HttpResponse,
): HttpResponse =
    traceApiCall(clientOptions, params) { client ->
            val response = send(client)
            response.parseable { response }
        }
        .parse()

/**
 * [traceApiCall] for an asynchronous method, whose span starts once the request is prepared and
 * [send] sends it. Cancelling the returned future ends the span as cancelled.
 */
@JvmSynthetic
internal fun <T> traceApiCallAsync(
    clientOptions: ClientOptions,
    params: Params,
    send: (ApiCallClient) -> CompletableFuture<HttpResponseFor<T>>,
): CompletableFuture<HttpResponseFor<T>> {
    val tracer = tracer(clientOptions) ?: return send(ApiCallClient(clientOptions))
    return traceApiCallAsync(ApiCallClient(clientOptions, tracer, params::_spanData), send)
}

/**
 * [traceApiCallAsync] for a method that started the span itself ([startApiCall]). [call] is `null`
 * when the client emits no span. The span is open while the request is prepared, so it also ends
 * when preparing it fails.
 */
@JvmSynthetic
internal fun <T> traceApiCallAsync(
    clientOptions: ClientOptions,
    call: ApiCall?,
    send: (ApiCallClient) -> CompletableFuture<HttpResponseFor<T>>,
): CompletableFuture<HttpResponseFor<T>> {
    if (call == null) {
        return send(ApiCallClient(clientOptions))
    }
    return traceApiCallAsync(ApiCallClient(clientOptions, call = call), send)
}

@Suppress("ForbiddenMethodCall") // The callbacks run SDK code only.
private fun <T> traceApiCallAsync(
    client: ApiCallClient,
    send: (ApiCallClient) -> CompletableFuture<HttpResponseFor<T>>,
): CompletableFuture<HttpResponseFor<T>> {
    val fail = { error: Throwable ->
        client.call?.let { endApiCall(it, ApiCallOutcome.Failed(unwrapFutureException(error))) }
    }
    val sent =
        try {
            send(client)
        } catch (e: Throwable) {
            fail(e)
            throw e
        }
    val response = CompletableFuture<HttpResponseFor<T>>()
    response.whenComplete { _, error ->
        if (error is CancellationException) {
            fail(error)
        }
    }
    sent.whenComplete { value, error ->
        // Whatever ending the span throws, the returned future completes: it would hang otherwise.
        try {
            if (response.isCancelled) {
                // The application cancelled the returned future before the span started.
                fail(CancellationException())
            }
            if (error != null) {
                fail(error)
            }
        } finally {
            val call = client.call
            if (error != null) {
                response.completeExceptionally(error)
            } else {
                response.complete(if (call == null) value else TracedHttpResponseFor(value, call))
            }
        }
    }
    return response
}

/** [traceRawApiCall] for an asynchronous method. */
@Suppress("ForbiddenMethodCall") // The callback runs SDK code only.
@JvmSynthetic
internal fun traceRawApiCallAsync(
    clientOptions: ClientOptions,
    params: Params,
    send: (ApiCallClient) -> CompletableFuture<out HttpResponse>,
): CompletableFuture<HttpResponse> =
    traceApiCallAsync(clientOptions, params) { client ->
            send(client).thenApply { response -> response.parseable { response } }
        }
        .thenParse()

/**
 * A future of the parsed body of this future's response. Cancelling it cancels this one, so that
 * the span ends as cancelled.
 */
@Suppress("ForbiddenMethodCall") // The callbacks run SDK code, as the services' callbacks do.
@JvmSynthetic
internal fun <T> CompletableFuture<HttpResponseFor<T>>.thenParse(): CompletableFuture<T> {
    val parsed = thenApply { it.parse() }
    parsed.whenComplete { _, error ->
        if (error is CancellationException) {
            cancel(false)
        }
    }
    return parsed
}

/**
 * What a method sends its request with inside [traceApiCall]. Sending starts the call's span, so
 * that the span starts with the request's options, and the request is sent with the span current. A
 * call sends one request: a second one is sent in the span of the first.
 */
internal class ApiCallClient(
    private val clientOptions: ClientOptions,
    private val tracer: Tracer? = null,
    /** What the span of a call starts with, while the span is still to start. */
    private var unstartedSpanData: (() -> SpanData)? = null,
    call: ApiCall? = null,
) : HttpClient {

    /** The call once its span has started. It stays `null` when the client emits no span for it. */
    @Volatile
    var call: ApiCall? = call
        private set

    // Read here because an asynchronous method sends its request from another thread. It is
    // `null` for the client that emits no span.
    private val parentOtelContext: Context? =
        if (tracer == null && call == null) null else Context.current()

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        val apiCallOtelContext =
            apiCallOtelContext(request, requestOptions)
                ?: return clientOptions.httpClient.execute(request, requestOptions)
        return apiCallOtelContext.makeCurrent().use {
            clientOptions.httpClient.execute(
                request,
                requestOptions.withApiCallOtelContext(apiCallOtelContext),
            )
        }
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val apiCallOtelContext =
            apiCallOtelContext(request, requestOptions)
                ?: return clientOptions.httpClient.executeAsync(request, requestOptions)
        return apiCallOtelContext.makeCurrent().use {
            clientOptions.httpClient.executeAsync(
                request,
                requestOptions.withApiCallOtelContext(apiCallOtelContext),
            )
        }
    }

    /** Does nothing: the client's HTTP client outlives the call. */
    override fun close() {}

    /** Sets [attribute] of the call's span to [id], the ID of the object that the call created. */
    fun recordCreatedId(attribute: String, id: JsonField<String>) {
        val span = call?.span ?: return
        id.asString().getOrNull()?.let { span.setAttribute(attribute, it) }
    }

    /**
     * The context with the call and its span, which [request] starts when it is the call's first,
     * or `null` without a span.
     */
    private fun apiCallOtelContext(request: HttpRequest, requestOptions: RequestOptions): Context? {
        val parentOtelContext = parentOtelContext ?: return null
        val unstartedSpanData = unstartedSpanData
        if (unstartedSpanData != null && tracer != null) {
            this.unstartedSpanData = null
            val spanData = unstartedSpanData()
            call =
                startApiCallSpan(
                        clientOptions,
                        tracer,
                        spanData,
                        request.method,
                        requestOptions,
                        parentOtelContext,
                    )
                    ?.let {
                        ApiCall(
                            it,
                            clientOptions,
                            queryParams = spanData.queryParams,
                            contentQueryParams = spanData.contentQueryParams,
                        )
                    }
        }
        val call = call ?: return null
        call.recordFirstRequest(request)
        return parentOtelContext.with(call.span).with(API_CALL, call)
    }
}

/**
 * Starts the span of an API call for a method that sets attributes of its own: [attributes] from
 * its params here, and more on [ApiCall.span] before it hands the call to [traceApiCall]. [method]
 * is the HTTP method of the request that the method sends. Returns `null` when the client emits no
 * span.
 */
@JvmSynthetic
internal fun startApiCall(
    clientOptions: ClientOptions,
    spanData: SpanData,
    method: HttpMethod,
    requestOptions: RequestOptions,
    attributes: Attributes = Attributes.empty(),
): ApiCall? {
    val tracer = tracer(clientOptions) ?: return null
    return startApiCallSpan(
            clientOptions,
            tracer,
            spanData,
            method,
            requestOptions,
            Context.current(),
            attributes,
        )
        ?.let {
            ApiCall(
                it,
                clientOptions,
                queryParams = spanData.queryParams,
                contentQueryParams = spanData.contentQueryParams,
            )
        }
}

/** An API call in flight: its call span, and what ending the span needs. */
internal class ApiCall(
    val span: Span,
    /** The options of the client that sent the call, which say whether its spans hold content. */
    val clientOptions: ClientOptions,
    /** The names of the query parameters that the call's endpoint defines, for `url.query`. */
    private val queryParams: List<String> = emptyList(),
    /** The query parameters that are content on the call's endpoint only. */
    private val contentQueryParams: List<String> = emptyList(),
    /**
     * When the call started, on the [System.nanoTime] clock, to measure
     * `gen_ai.response.time_to_first_chunk` from. A span cannot report its own start time.
     */
    val startNanoTime: Long = System.nanoTime(),
) {
    /**
     * Records a stream that has an end event on the span as the application reads it. The method
     * sets it when it parses the response into the stream.
     */
    @Volatile var streamRecording: StreamRecording<*>? = null

    private val ended = AtomicBoolean()

    private val hasUrl = AtomicBoolean()

    /**
     * Whether the call's first request is a `GET`. The API never ends the event stream that answers
     * one, where it ends the stream that answers a `POST` once it has sent everything.
     */
    @Volatile
    var isGet = false
        private set

    private val requestCount = AtomicInteger()

    /**
     * Sets the `url.*` attributes from [request] when it is the first request of the call. A retry
     * or a fallback sends a request of its own, and leaves the attributes as they are.
     */
    fun recordFirstRequest(request: HttpRequest) {
        if (span.isRecording && hasUrl.compareAndSet(false, true)) {
            isGet = request.method == HttpMethod.GET
            span.setAllAttributes(
                urlAttributes(
                    request,
                    queryParams,
                    contentQueryParams,
                    clientOptions.openTelemetry.traces().contentMode() ==
                        OpenTelemetryContentMode.CONTENT,
                )
            )
        }
    }

    /** Counts a request that the call sends: its first one, and every one that resends it. */
    fun countRequest() {
        requestCount.incrementAndGet()
    }

    /**
     * Sets `http.request.resend_count`, right before the span ends, when the SDK resent the call's
     * request, whether as a retry or as a fallback.
     */
    fun recordResendCount() {
        val resendCount = requestCount.get() - 1
        if (resendCount > 0) {
            span.setAttribute(HTTP_REQUEST_RESEND_COUNT, resendCount.toLong())
        }
    }

    /**
     * Whether the caller is the one that ends the span: `true` for the first caller only, whatever
     * the span's implementation reports once it has ended.
     */
    fun markEnded(): Boolean = ended.compareAndSet(false, true)
}

/**
 * What records a stream that has an end event on the span of its API call, as the application reads
 * it.
 */
internal interface StreamRecording<in T> {

    /** Called with each event, after the application's read of it. */
    fun onEvent(event: T)

    /**
     * Called once, when the read ends and before the span does. Returns whether the events reached
     * the stream's end event.
     */
    fun onEnd(): Boolean
}

/**
 * [response], ending the span of [call] when [traceApiCall] says it ends. An application that
 * neither parses nor closes this response leaves the span open until the runtime reclaims this
 * response, which ends the span as if the application had closed it then.
 */
private class TracedHttpResponseFor<T>(
    private val response: HttpResponseFor<T>,
    private val call: ApiCall,
) : HttpResponseFor<T> {

    init {
        closeWhenPhantomReachable(this, endApiCallUnparsed(response, call))
    }

    private val parsed = lazy {
        val data =
            try {
                response.parse()
            } catch (e: Throwable) {
                end(ApiCallOutcome.ParseFailed(response, e))
                throw e
            }
        if (data is StreamResponse<*>) {
            if (!call.markEnded()) {
                return@lazy data
            }
            // The stream takes the span with it, and ends it once read or closed.
            @Suppress("UNCHECKED_CAST")
            val stream =
                endApiCallSpanWhenStreamEnds(
                    call,
                    data as StreamResponse<Any?>,
                    response,
                    call.streamRecording as StreamRecording<Any?>?,
                )
                    as T
            return@lazy stream
        }
        end(ApiCallOutcome.Responded(response))
        data
    }

    private fun end(outcome: ApiCallOutcome) = endApiCall(call, outcome)

    override fun parse(): T = parsed.value

    override fun statusCode(): Int = response.statusCode()

    override fun headers(): Headers = response.headers()

    override fun body(): InputStream = response.body()

    override fun close() {
        if (parsed.isInitialized()) {
            // A parsed stream owns the span, and closing the response under it closes it.
            (parsed.value as? StreamResponse<*>)?.close()
        } else {
            end(ApiCallOutcome.Responded(response))
        }
        response.close()
    }
}

/**
 * Ends the span of [call] as that of a call whose [response] is closed unparsed. It references
 * neither the traced response nor anything that does, so that the runtime can reclaim that one.
 */
private fun endApiCallUnparsed(response: HttpResponse, call: ApiCall): () -> Unit = {
    endApiCall(call, ApiCallOutcome.Responded(response))
}

/**
 * Ends the span of [call] with [outcome]. Several paths can end a call, and only the first does.
 */
private fun endApiCall(call: ApiCall, outcome: ApiCallOutcome) {
    if (!call.markEnded()) {
        return
    }
    // Ending the span runs the application's span processors, whose failure is not the call's.
    try {
        call.recordResendCount()
        endApiCallSpan(call.span, outcome)
    } catch (e: Exception) {
        logTelemetryFailure(call.clientOptions, "Could not end the span of the API call", e)
    }
}

/**
 * Starts the call span: one `CLIENT` span for a whole API call, its retries and the reading of a
 * streamed body included. The SDK emits no span per attempt (one HTTP request). The application's
 * HTTP instrumentation parents those to the call span, which is current while each is sent.
 *
 * The span starts with all that is known before the request is sent, so that a sampler sees it.
 * Returns `null` when the span neither records nor propagates a trace context: the call then runs
 * as if tracing were off. With the client's `propagation` off, a span that does not record is kept:
 * it is current while its requests are sent, so that the spans of an HTTP client instrumentation
 * share its sampling decision, as they do when the SDK propagates it.
 */
private fun startApiCallSpan(
    clientOptions: ClientOptions,
    tracer: Tracer,
    spanData: SpanData,
    method: HttpMethod,
    requestOptions: RequestOptions,
    parentOtelContext: Context,
    attributes: Attributes = Attributes.empty(),
): Span? {
    val builder =
        tracer.spanBuilder(spanData.name).setSpanKind(SpanKind.CLIENT).setParent(parentOtelContext)
    builder.setAttribute(HTTP_REQUEST_METHOD, method.name)
    builder.setAttribute(HTTP_ROUTE, spanData.route)
    builder.setAllAttributes(serverAttributes(clientOptions.baseUrl()))
    builder.setAllAttributes(attributes)
    spanData.attributes.forEach { (attribute, id) -> builder.setAttribute(attribute, id) }
    // Starting the span runs the application's sampler, span processors and propagator, whose
    // failure is not the call's: the call then runs as if tracing were off.
    return try {
        builder.startSpan().takeIf { span ->
            span.isRecording ||
                !clientOptions.openTelemetry.propagation() ||
                injectContext(parentOtelContext.with(span)).isNotEmpty()
        }
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not start the span of the API call", e)
        null
    }
}

/** What the params of a method give the span of a call to it. */
class SpanData
internal constructor(
    @get:JvmSynthetic internal val name: String,
    @get:JvmSynthetic internal val route: String,
    /** Maps span attribute names to the resource IDs in the request's path. */
    @get:JvmSynthetic internal val attributes: Map<String, String> = emptyMap(),
    @get:JvmSynthetic internal val queryParams: List<String> = emptyList(),
    /** The query parameters that are content on this endpoint only. */
    @get:JvmSynthetic internal val contentQueryParams: List<String> = emptyList(),
)

/**
 * The tracer of [clientOptions], or `null` when the client emits no span: its spans are disabled,
 * or the tracer provider is the API's no-op instance, whose spans never record, and nothing carries
 * their context: the propagator is the no-op instance too, or the client's `propagation` is off.
 */
@JvmSynthetic
internal fun tracer(clientOptions: ClientOptions): Tracer? {
    val options = clientOptions.openTelemetry
    if (!options.traces().enabled()) {
        return null
    }
    val global = globalOpenTelemetry(clientOptions)
    val tracerProvider = options.tracerProvider().getOrNull() ?: global.tracerProvider
    if (
        tracerProvider === TracerProvider.noop() &&
            (!options.propagation() ||
                global.propagators.textMapPropagator === TextMapPropagator.noop())
    ) {
        return null
    }
    // A client of another platform has the platform after the SDK's scope name.
    val scope =
        clientOptions.openTelemetryPlatform().getOrNull()?.let { "$INSTRUMENTATION_SCOPE.$it" }
            ?: INSTRUMENTATION_SCOPE
    // The tracer provider can be the application's own, whose failure is not the call's.
    return try {
        tracerProvider.get(scope, INSTRUMENTATION_VERSION)
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not get the tracer of the client", e)
        null
    }
}

/**
 * The [OpenTelemetry] the application registered, or a no-op one. Never [GlobalOpenTelemetry.get],
 * which registers a no-op instance when none is registered, so that the application's own
 * registration afterward fails. An API version older than `getOrNoop` gets the no-op instance, and
 * the first client that then looks for its tracer says so at the `debug` log level.
 */
@JvmSynthetic
internal fun globalOpenTelemetry(clientOptions: ClientOptions? = null): OpenTelemetry =
    try {
        GlobalOpenTelemetry.getOrNoop()
    } catch (e: NoSuchMethodError) {
        if (
            clientOptions != null &&
                clientOptions.logLevel.shouldLog(LogLevel.DEBUG) &&
                OLD_API_LOGGED.compareAndSet(false, true)
        ) {
            System.err.println(
                "The OpenTelemetry API on the classpath is older than 1.57.0, so the SDK cannot " +
                    "read the tracer provider and the propagator that the application registered: " +
                    "it emits spans only with a tracer provider set in its `openTelemetry` " +
                    "option, and sends no trace context headers."
            )
        }
        OpenTelemetry.noop()
    }

private val OLD_API_LOGGED = AtomicBoolean()

/** `server.address` and `server.port` of [baseUrl]. Empty when [baseUrl] is not a URL. */
private fun serverAttributes(baseUrl: String): Attributes {
    val uri =
        try {
            URI(baseUrl)
        } catch (e: URISyntaxException) {
            return Attributes.empty()
        }
    val attributes = Attributes.builder()
    // `URI.getHost` keeps an IPv6 literal's brackets, which delimit it in a URL; `server.address`
    // is the bare address.
    uri.host?.let { attributes.put(SERVER_ADDRESS, it.removeSurrounding("[", "]")) }
    attributes.put(
        SERVER_PORT,
        when {
            uri.port != -1 -> uri.port.toLong()
            uri.scheme.equals("http", ignoreCase = true) -> 80L
            else -> 443L
        },
    )
    return attributes.build()
}

/**
 * The `url.*` attributes of [request]: the parts of its URL, encoded as the SDK's HTTP client sends
 * them. The query keeps the parameters whose whole name is one of [queryParams], or one of them
 * followed by `[]`, as the items of an array are sent, each as it is sent and in its place. That
 * query is content when it has one of [CONTENT_QUERY_PARAMS] or of [contentQueryParams]: without
 * [withContent], the attributes then leave out `url.query` and `url.full`, which contains it. Empty
 * when the request's base URL is not a URL.
 */
private fun urlAttributes(
    request: HttpRequest,
    queryParams: List<String>,
    contentQueryParams: List<String>,
    withContent: Boolean,
): Attributes {
    val baseUrl =
        try {
            URI(request.baseUrl)
        } catch (e: URISyntaxException) {
            return Attributes.empty()
        }
    val scheme = baseUrl.scheme?.lowercase() ?: return Attributes.empty()
    val host = baseUrl.host ?: return Attributes.empty()
    val path =
        request.pathSegments
            .joinToString("", baseUrl.rawPath.orEmpty().removeSuffix("/")) {
                "/" + percentEncode(it, PATH_SEGMENT_ENCODE_SET)
            }
            .ifEmpty { "/" }
    val names =
        request.queryParams
            .keys()
            // A defined name is listed whole, as `created_at[gte]` is, and only the items of an
            // array add `[]` to it: any other name in brackets is one that the application added.
            .filter { it in queryParams || it.removeSuffix("[]") in queryParams }
    val contentNames = CONTENT_QUERY_PARAMS + contentQueryParams
    val queryIsContent = names.any { it in contentNames || it.removeSuffix("[]") in contentNames }
    val query =
        names
            .flatMap { name ->
                request.queryParams.values(name).map { value ->
                    percentEncode(name, QUERY_ENCODE_SET) +
                        "=" +
                        percentEncode(value, QUERY_ENCODE_SET)
                }
            }
            .joinToString("&")
            .ifEmpty { null }
    val port = if (baseUrl.port == -1) "" else ":${baseUrl.port}"
    val attributes = Attributes.builder()
    attributes.put(URL_SCHEME, scheme)
    attributes.put(URL_PATH, path)
    if (withContent || !queryIsContent) {
        query?.let { attributes.put(URL_QUERY, it) }
        attributes.put(URL_FULL, "$scheme://$host$port$path" + query?.let { "?$it" }.orEmpty())
    }
    return attributes.build()
}

/**
 * The query parameters whose value is content on every endpoint that defines them, which make a
 * query string that has one of them content too.
 */
private val CONTENT_QUERY_PARAMS = setOf("email", "path_prefix", "expected_content_sha256")

/**
 * Percent-encodes the bytes of [value] that are in [encodeSet], controls, or not ASCII, which is
 * how OkHttp encodes the request's URL.
 */
private fun percentEncode(value: String, encodeSet: String): String = buildString {
    for (byte in value.toByteArray()) {
        val code = byte.toInt() and 0xFF
        if (code <= 0x20 || code >= 0x7F || code.toChar() in encodeSet) {
            append('%')
                .append("0123456789ABCDEF"[code shr 4])
                .append("0123456789ABCDEF"[code and 0xF])
        } else {
            append(code.toChar())
        }
    }
}

private const val PATH_SEGMENT_ENCODE_SET = "\"<>^`{}|/\\?#%"

private const val QUERY_ENCODE_SET = "!\"#$&'(),/:;<=>?@[]\\^`{|}~%+"

/** How far an API call got when its span ends. */
internal sealed class ApiCallOutcome {

    /** The error that the call failed with, or `null` when it did not fail. */
    abstract val error: Throwable?

    /** The status code of the response, when one arrived. */
    abstract val statusCode: Int?

    /** The headers of the response, when one arrived. */
    abstract val headers: Headers?

    /**
     * The call failed with [error] before it had a response to parse: the request could not be
     * prepared or sent, or the response had an error status, which [error] then carries.
     */
    class Failed(override val error: Throwable) : ApiCallOutcome() {
        override val statusCode
            get() = (error as? AnthropicServiceException)?.statusCode()

        override val headers
            get() = (error as? AnthropicServiceException)?.headers()
    }

    /** [response] arrived with a success status, and parsing its body failed with [error]. */
    class ParseFailed(private val response: HttpResponse, override val error: Throwable) :
        ApiCallOutcome() {
        override val statusCode
            get() = response.statusCode()

        override val headers
            get() = response.headers()
    }

    /**
     * [response] arrived with a success status. Its body was parsed, or the application closed it
     * unparsed.
     */
    class Responded(private val response: HttpResponse) : ApiCallOutcome() {
        override val error: Throwable?
            get() = null

        override val statusCode
            get() = response.statusCode()

        override val headers
            get() = response.headers()
    }
}

/**
 * Ends [span] with [outcome]: status `ERROR` with `error.type` when the call failed, none when the
 * application cancelled it, `OK` otherwise. Does nothing to a span that is not recording.
 */
@JvmSynthetic
internal fun endApiCallSpan(span: Span, outcome: ApiCallOutcome) {
    if (!span.isRecording) {
        return
    }
    outcome.statusCode?.let { span.setAttribute(HTTP_RESPONSE_STATUS_CODE, it.toLong()) }
    val error = outcome.error
    if (error != null) {
        setErrorStatus(span, error)
    } else {
        span.setStatus(StatusCode.OK)
    }
    span.end()
}

/**
 * Returns [stream], the body of [response], wrapped so that the span of [call] ends when the
 * application's read of it ends: `OK` when read to the end, an error when reading fails, no status
 * when closed early. An [AsyncStreamResponse] reads through the same wrapper.
 *
 * The server cutting an event stream short looks like the stream's end. Only [recording] can tell,
 * by the stream's end event: without it the span fails with `api_error`. An event stream that
 * answers a `GET`, which the API never ends, never counts as read to the end. Returns [stream] as
 * it is when the span is not recording.
 */
@JvmSynthetic
internal fun <T> endApiCallSpanWhenStreamEnds(
    call: ApiCall,
    stream: StreamResponse<T>,
    response: HttpResponse,
    recording: StreamRecording<T>? = null,
): StreamResponse<T> {
    val span = call.span
    if (!span.isRecording) {
        return stream
    }
    span.setAttribute(HTTP_RESPONSE_STATUS_CODE, response.statusCode().toLong())
    val isEventStream =
        call.isGet &&
            response.headers().values("Content-Type").any { it.startsWith("text/event-stream") }
    return TracedStreamResponse(stream, call, recording, isEventStream)
}

/**
 * The first [stream] call owns the span. A later one reads [response] directly, and so fails as a
 * second read does, without touching the span.
 *
 * An application that stops reading without closing this response leaves the span open until the
 * runtime reclaims this response, which ends the span as if the application had closed it then.
 */
private class TracedStreamResponse<T>(
    private val response: StreamResponse<T>,
    private val call: ApiCall,
    private val recording: StreamRecording<T>?,
    isEventStream: Boolean,
) : StreamResponse<T> {

    private val claimed = AtomicBoolean()
    // Kept apart from this response, which it does not reference, so that the runtime can reclaim
    // this response and then close the span's end in its place.
    private val spanEnd = StreamSpanEnd(response, call, recording, isEventStream)

    init {
        closeWhenPhantomReachable(this, spanEnd)
    }

    override fun stream(): Stream<T> {
        val source = response.stream()
        if (!claimed.compareAndSet(false, true)) {
            return source
        }
        // The supplier defers reading until the application consumes the stream, as `source` does.
        return StreamSupport.stream(
                {
                    Spliterators.spliteratorUnknownSize(
                        read(source.iterator()),
                        Spliterator.ORDERED,
                    )
                },
                Spliterator.ORDERED,
                false,
            )
            .onClose(source::close)
    }

    /**
     * [source], ending the span when it runs out or fails. The iterator reaches [spanEnd] through
     * this response, so an application that holds only the stream keeps this response from being
     * reclaimed.
     */
    @Suppress("IteratorNotThrowingNoSuchElementException") // `next` throws what `source` throws.
    private fun read(source: Iterator<T>): Iterator<T> =
        object : Iterator<T> {

            override fun hasNext(): Boolean {
                val hasNext =
                    try {
                        source.hasNext()
                    } catch (e: Throwable) {
                        spanEnd.failed(e)
                        throw e
                    }
                if (!hasNext) {
                    spanEnd.ended()
                }
                return hasNext
            }

            override fun next(): T {
                val event =
                    try {
                        source.next()
                    } catch (e: Throwable) {
                        spanEnd.failed(e)
                        throw e
                    }
                // The recording is telemetry, whose failure is not the read's.
                try {
                    recording?.onEvent(event)
                } catch (e: Exception) {
                    logTelemetryFailure(
                        call.clientOptions,
                        "Could not record an event of the stream",
                        e,
                    )
                }
                return event
            }
        }

    override fun close() = spanEnd.close()
}

/**
 * How the span of a [TracedStreamResponse] that reads [response] ends: whichever of [ended],
 * [failed] and [close] comes first decides the span's status.
 */
private class StreamSpanEnd(
    private val response: StreamResponse<*>,
    private val call: ApiCall,
    private val recording: StreamRecording<*>?,
    private val isEventStream: Boolean,
) : AutoCloseable {

    private val span = call.span

    // Whether the span's status is still to be decided: reading has neither ended nor failed, and
    // the stream is not closed.
    private val open = AtomicBoolean(true)

    /**
     * The server ended the stream. Before the end event, that cut it short: the application sees no
     * error, so only the span fails, and the failure is the API's. An event stream without an end
     * event is never complete, so its status stays unset.
     */
    fun ended() = end { complete ->
        if (complete != null) {
            if (complete) {
                span.setStatus(StatusCode.OK)
            } else {
                setErrorStatus(
                    span,
                    AnthropicException("The stream ended before its end event."),
                    "api_error",
                )
            }
        } else if (!isEventStream) {
            span.setStatus(StatusCode.OK)
        }
    }

    /** Reading failed: an `error` event, or a lost connection. */
    fun failed(error: Throwable) = end { setErrorStatus(span, error) }

    /**
     * Closed before the server ended the stream, by the application or, for a stream that the
     * application abandoned, by the runtime: a cancellation, even after the end event, so the
     * status stays unset.
     */
    override fun close() {
        val ended = end {}
        if (!ended) {
            response.close()
        }
    }

    /**
     * Ends the span with the status that [setStatus] gives it, unless it was decided before.
     * [setStatus] gets whether the recorded stream is complete, or `null` for any other stream.
     */
    private inline fun end(setStatus: (complete: Boolean?) -> Unit): Boolean {
        if (!open.compareAndSet(true, false)) {
            return false
        }
        try {
            response.close()
        } finally {
            // The recording is telemetry, whose failure is not the read's: the span then ends as
            // that of a stream with no recording.
            val complete =
                try {
                    recording?.onEnd()
                } catch (e: Exception) {
                    logTelemetryFailure(
                        call.clientOptions,
                        "Could not record the end of the stream",
                        e,
                    )
                    null
                }
            // Ending the span runs the application's span processors, whose failure is not the
            // read's.
            try {
                setStatus(complete)
                // A fallback can resend the request while the application reads the stream.
                call.recordResendCount()
                span.end()
            } catch (e: Exception) {
                logTelemetryFailure(call.clientOptions, "Could not end the span of the stream", e)
            }
        }
        return true
    }
}

/**
 * Gives [span] the status `ERROR`, with `error.type` set to [type] or else derived from [error]. A
 * cancellation by the application, which cancelled a future or interrupted the thread, is not a
 * failure, and leaves the status unset.
 */
@JvmSynthetic
internal fun setErrorStatus(span: Span, error: Throwable, type: String? = null) {
    val cause = unwrapFutureException(error)
    if (
        cause is CancellationException ||
            cause is InterruptedException ||
            Thread.currentThread().isInterrupted
    ) {
        return
    }
    span.setStatus(StatusCode.ERROR)
    span.setAttribute(ERROR_TYPE, type ?: errorType(cause))
}

/**
 * The `error.type` of an operation that failed with [error]: for an error response, its status
 * code.
 */
private fun errorType(error: Throwable): String =
    when {
        // A stream's error event arrives with the stream's successful status.
        error is AnthropicServiceException && error.statusCode() >= 400 ->
            error.statusCode().toString()
        error is InterruptedIOException || error.cause is InterruptedIOException ->
            "client_timeout_error"
        error is AnthropicIoException || error is IOException -> "connection_error"
        else -> "client_error"
    }

/** The error that a future's [error] wraps, or [error] itself. */
@JvmSynthetic
internal fun unwrapFutureException(error: Throwable): Throwable {
    var cause = error
    while (cause is CompletionException || cause is ExecutionException) {
        cause = cause.cause ?: break
    }
    return cause
}

// Copies of the semantic conventions' constants, so that the SDK does not depend on their artifact.

/** The `error.type` attribute: what kind of error an operation failed with. */
@get:JvmSynthetic
internal val ERROR_TYPE: AttributeKey<String> = AttributeKey.stringKey("error.type")

/** The `http.response.status_code` attribute: the status code of the last response. */
@get:JvmSynthetic
internal val HTTP_RESPONSE_STATUS_CODE: AttributeKey<Long> =
    AttributeKey.longKey("http.response.status_code")

/** The `http.request.method` attribute: the HTTP method of the call's request. */
@get:JvmSynthetic
internal val HTTP_REQUEST_METHOD: AttributeKey<String> =
    AttributeKey.stringKey("http.request.method")

/**
 * The `http.request.resend_count` attribute: how many times the SDK resent the call's request.
 * Absent when it did not.
 */
@get:JvmSynthetic
internal val HTTP_REQUEST_RESEND_COUNT: AttributeKey<Long> =
    AttributeKey.longKey("http.request.resend_count")

/** The `http.route` attribute: the path of the call's endpoint, as the API's description has it. */
@get:JvmSynthetic
internal val HTTP_ROUTE: AttributeKey<String> = AttributeKey.stringKey("http.route")

/** The `server.address` attribute: the host of the client's base URL. */
@get:JvmSynthetic
internal val SERVER_ADDRESS: AttributeKey<String> = AttributeKey.stringKey("server.address")

/** The `server.port` attribute: the port of the client's base URL, or its scheme's default. */
@get:JvmSynthetic internal val SERVER_PORT: AttributeKey<Long> = AttributeKey.longKey("server.port")

/**
 * The `url.full` attribute: the URL of the call's first request, with the query of `url.query`. It
 * can differ from the URL that was sent. Absent when that query is content that the span does not
 * record.
 */
@get:JvmSynthetic internal val URL_FULL: AttributeKey<String> = AttributeKey.stringKey("url.full")

/** The `url.path` attribute: the path of the URL of the call's first request. */
@get:JvmSynthetic internal val URL_PATH: AttributeKey<String> = AttributeKey.stringKey("url.path")

/**
 * The `url.query` attribute: the query string of the URL of the call's first request, with only the
 * parameters that the endpoint defines. Absent when none is left, or when it is content that the
 * span does not record.
 */
@get:JvmSynthetic internal val URL_QUERY: AttributeKey<String> = AttributeKey.stringKey("url.query")

/** The `url.scheme` attribute: the scheme of the URL of the call's first request. */
@get:JvmSynthetic
internal val URL_SCHEME: AttributeKey<String> = AttributeKey.stringKey("url.scheme")

/** Where a request finds the API call that sends it, in the call's OpenTelemetry context. */
@get:JvmSynthetic
internal val API_CALL: ContextKey<ApiCall> = ContextKey.named("anthropic-api-call")

private const val INSTRUMENTATION_SCOPE = "com.anthropic.sdk.java"

private val INSTRUMENTATION_VERSION: String by lazy { getPackageVersion() }
