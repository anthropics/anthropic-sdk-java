package com.anthropic.core.opentelemetry

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.TracerProvider
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The `openTelemetry` client option. */
class OpenTelemetryOptions
private constructor(
    private val tracerProvider: TracerProvider?,
    private val propagation: Boolean,
    private val traces: OpenTelemetryTraces,
) {

    /**
     * The tracer provider that creates the client's spans, in place of the one registered with
     * [GlobalOpenTelemetry].
     */
    fun tracerProvider(): Optional<TracerProvider> = Optional.ofNullable(tracerProvider)

    /**
     * Whether the client's requests carry the trace context of their span, in the headers of the
     * propagator registered with [GlobalOpenTelemetry]. Defaults to `true`. Set it to `false` when
     * an instrumentation of the HTTP client already propagates the trace context, so that a request
     * does not carry it twice.
     */
    fun propagation(): Boolean = propagation

    /**
     * The settings of the client's spans: whether it emits them, and what they hold of the content
     * of its requests and responses. Defaults to spans without content.
     */
    fun traces(): OpenTelemetryTraces = traces

    fun toBuilder() = Builder().from(this)

    companion object {

        private val NONE = builder().build()

        /** Returns an instance of [OpenTelemetryOptions] with every option at its default. */
        @JvmStatic fun none() = NONE

        /** Returns a mutable builder for constructing an instance of [OpenTelemetryOptions]. */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns options that only set whether the client emits spans: [traces] with
         * [OpenTelemetryTraces.enabled] set to [enabled].
         */
        @JvmStatic
        fun ofEnabled(enabled: Boolean): OpenTelemetryOptions = builder().traces(enabled).build()

        /**
         * Returns options based on system properties and environment variables.
         *
         * |Setter                  |System property                               |Environment variable                               |Effect                                              |
         * |------------------------|----------------------------------------------|---------------------------------------------------|----------------------------------------------------|
         * |`traces.enabled`        |`anthropic.openTelemetry`                     |`ANTHROPIC_OPEN_TELEMETRY`                         |`false` disables the spans                          |
         * |`traces.contentMode`    |`anthropic.openTelemetryTracesContentMode`    |`ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE`     |`content` selects [OpenTelemetryContentMode.CONTENT]|
         * |`traces.maxContentBytes`|`anthropic.openTelemetryTracesMaxContentBytes`|`ANTHROPIC_OPEN_TELEMETRY_TRACES_MAX_CONTENT_BYTES`|A whole number of at least 1 sets the bound         |
         * |`propagation`           |`anthropic.openTelemetryPropagation`          |`ANTHROPIC_OPEN_TELEMETRY_PROPAGATION`             |`false` sends no trace context headers              |
         *
         * The values are case-insensitive, and any other value selects the default. System
         * properties take precedence over environment variables.
         */
        @JvmStatic fun fromEnv(): OpenTelemetryOptions = none().withEnv()
    }

    /** A builder for [OpenTelemetryOptions]. */
    class Builder internal constructor() {

        private var tracerProvider: TracerProvider? = null
        private var propagation: Boolean = true
        private var traces: OpenTelemetryTraces = OpenTelemetryTraces.builder().build()

        @JvmSynthetic
        internal fun from(openTelemetryOptions: OpenTelemetryOptions) = apply {
            tracerProvider = openTelemetryOptions.tracerProvider
            propagation = openTelemetryOptions.propagation
            traces = openTelemetryOptions.traces
        }

        /**
         * The tracer provider that creates the client's spans, in place of the one registered with
         * [GlobalOpenTelemetry].
         */
        fun tracerProvider(tracerProvider: TracerProvider?) = apply {
            this.tracerProvider = tracerProvider
        }

        /** Alias for calling [Builder.tracerProvider] with `tracerProvider.orElse(null)`. */
        fun tracerProvider(tracerProvider: Optional<TracerProvider>) =
            tracerProvider(tracerProvider.getOrNull())

        /**
         * Whether the client's requests carry the trace context of their span, in the headers of
         * the propagator registered with [GlobalOpenTelemetry]. Defaults to `true`. Set it to
         * `false` when an instrumentation of the HTTP client already propagates the trace context,
         * so that a request does not carry it twice.
         */
        fun propagation(propagation: Boolean) = apply { this.propagation = propagation }

        /**
         * The settings of the client's spans: whether it emits them, and what they hold of the
         * content of its requests and responses. Defaults to spans without content.
         */
        fun traces(traces: OpenTelemetryTraces) = apply { this.traces = traces }

        /**
         * Enables or disables the client's spans.
         *
         * A short form of [traces] with only [OpenTelemetryTraces.enabled] set to [enabled].
         */
        fun traces(enabled: Boolean) =
            traces(OpenTelemetryTraces.builder().enabled(enabled).build())

        /**
         * Returns an immutable instance of [OpenTelemetryOptions].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): OpenTelemetryOptions =
            OpenTelemetryOptions(tracerProvider, propagation, traces)
    }

    /**
     * Returns these options with each one replaced that a system property or an environment
     * variable sets (see [fromEnv]). The others stay as they are.
     */
    @JvmSynthetic
    internal fun withEnv(): OpenTelemetryOptions {
        val enabled =
            System.getProperty("anthropic.openTelemetry")
                ?: System.getenv("ANTHROPIC_OPEN_TELEMETRY")
        val contentMode =
            System.getProperty("anthropic.openTelemetryTracesContentMode")
                ?: System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE")
        val maxContentBytes =
            System.getProperty("anthropic.openTelemetryTracesMaxContentBytes")
                ?: System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_MAX_CONTENT_BYTES")
        val propagation =
            System.getProperty("anthropic.openTelemetryPropagation")
                ?: System.getenv("ANTHROPIC_OPEN_TELEMETRY_PROPAGATION")
        if (
            enabled == null && contentMode == null && maxContentBytes == null && propagation == null
        ) {
            return this
        }
        val options = toBuilder()
        if (propagation != null) {
            options.propagation(!propagation.equals("false", ignoreCase = true))
        }
        val builder = traces.toBuilder()
        if (enabled != null) {
            builder.enabled(!enabled.equals("false", ignoreCase = true))
        }
        if (contentMode != null) {
            builder.contentMode(
                if (contentMode.equals("content", ignoreCase = true))
                    OpenTelemetryContentMode.CONTENT
                else OpenTelemetryContentMode.METADATA_ONLY
            )
        }
        if (maxContentBytes != null) {
            builder.maxContentBytes(maxContentBytes.toLongOrNull()?.takeIf { it >= 1 })
        }
        return options.traces(builder.build()).build()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OpenTelemetryOptions &&
            tracerProvider == other.tracerProvider &&
            propagation == other.propagation &&
            traces == other.traces
    }

    override fun hashCode(): Int = Objects.hash(tracerProvider, propagation, traces)

    override fun toString() =
        "OpenTelemetryOptions{tracerProvider=${if (tracerProvider == null) "null" else "<set>"}, propagation=$propagation, traces=$traces}"
}
