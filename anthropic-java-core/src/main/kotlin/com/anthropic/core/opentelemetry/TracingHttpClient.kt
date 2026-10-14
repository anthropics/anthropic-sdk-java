package com.anthropic.core.opentelemetry

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.internal.telemetry.logTelemetryFailure
import io.opentelemetry.context.Context
import io.opentelemetry.context.propagation.TextMapSetter
import java.util.concurrent.CompletableFuture

/**
 * Sends each request with the trace context of its API call ([RequestOptions.apiCallOtelContext])
 * in its headers, unless the client's `propagation` is off, and with that context current, and
 * counts it as one that the call sent.
 *
 * It is the innermost client because an asynchronous step above it, such as a retry after its
 * backoff, runs where the context is no longer current. Restoring it here lets an instrumentation
 * of [httpClient] parent its attempt spans to the call span.
 */
internal class TracingHttpClient(private val httpClient: HttpClient) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        val apiCallOtelContext =
            requestOptions.apiCallOtelContext ?: return httpClient.execute(request, requestOptions)
        apiCallOtelContext.get(API_CALL)?.countRequest()
        return apiCallOtelContext.makeCurrent().use {
            httpClient.execute(injectTraceHeaders(apiCallOtelContext, request), requestOptions)
        }
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val apiCallOtelContext =
            requestOptions.apiCallOtelContext
                ?: return httpClient.executeAsync(request, requestOptions)
        apiCallOtelContext.get(API_CALL)?.countRequest()
        return apiCallOtelContext.makeCurrent().use {
            httpClient.executeAsync(injectTraceHeaders(apiCallOtelContext, request), requestOptions)
        }
    }

    override fun close() = httpClient.close()
}

/**
 * Returns [request] with the headers that carry [apiCallOtelContext], in place of any such headers
 * already present, so that a retry carries the context afresh. Returns [request] as it is when the
 * `propagation` of the call's client is off.
 */
@JvmSynthetic
internal fun injectTraceHeaders(apiCallOtelContext: Context, request: HttpRequest): HttpRequest {
    if (apiCallOtelContext.get(API_CALL)?.clientOptions?.openTelemetry?.propagation() == false) {
        return request
    }
    val headers =
        try {
            injectContext(apiCallOtelContext)
        } catch (e: Exception) {
            // The propagator is the application's, whose failure is not the request's.
            apiCallOtelContext.get(API_CALL)?.let {
                logTelemetryFailure(it.clientOptions, "Could not inject the trace context", e)
            }
            return request
        }
    if (headers.isEmpty()) {
        return request
    }
    return request
        .toBuilder()
        .apply { headers.forEach { (name, value) -> replaceHeaders(name, value) } }
        .build()
}

/** The headers that carry [otelContext], from the propagator the application registered. */
@JvmSynthetic
internal fun injectContext(otelContext: Context): Map<String, String> {
    val headers = mutableMapOf<String, String>()
    globalOpenTelemetry().propagators.textMapPropagator.inject(otelContext, headers, HEADER_SETTER)
    return headers
}

private val HEADER_SETTER =
    TextMapSetter<MutableMap<String, String>> { headers, name, value -> headers?.put(name, value) }
