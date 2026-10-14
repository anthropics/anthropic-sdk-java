@file:JvmName("TraceContextHeaders")

package com.anthropic.internal.telemetry

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry

/**
 * The names, in lower case, of the request headers that carry a trace context: `traceparent`,
 * `tracestate`, `baggage`, and the fields of the propagator the application registered, read anew
 * at each call.
 *
 * A backend that signs its requests sends these headers and leaves them out of the signature. An
 * HTTP client instrumentation may replace them with those of its own span after the request is
 * signed, and a signature that covered them would then no longer match.
 *
 * **This is an internal function.** It is public only because the SDK's other modules call it; its
 * API is not stable and may change without notice.
 */
fun traceContextHeaderNames(): Set<String> {
    val names = mutableSetOf("traceparent", "tracestate", "baggage")
    // Never `GlobalOpenTelemetry.get`, which registers a no-op instance when the application has
    // registered none. An application that forces an API version older than `getOrNoop` has no
    // propagator that the SDK injects with.
    val openTelemetry =
        try {
            GlobalOpenTelemetry.getOrNoop()
        } catch (e: NoSuchMethodError) {
            OpenTelemetry.noop()
        }
    openTelemetry.propagators.textMapPropagator.fields().mapTo(names) { it.lowercase() }
    return names
}
