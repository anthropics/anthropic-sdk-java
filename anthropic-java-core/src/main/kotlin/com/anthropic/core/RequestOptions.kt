package com.anthropic.core

import com.anthropic.core.opentelemetry.OpenTelemetryRequestOptions
import com.anthropic.helpers.BetaFallbackState
import com.anthropic.helpers.BetaMessageAccumulator
import io.opentelemetry.context.Context
import java.time.Duration
import java.util.Optional

class RequestOptions
private constructor(
    private val responseValidation: Boolean?,
    private val timeout: Timeout?,
    private val followRedirects: Boolean?,
    private val fallbackState: BetaFallbackState?,
    private val openTelemetry: OpenTelemetryRequestOptions?,
    /**
     * The query pairs that tell the operation apart from the others with its path in the OpenAPI
     * document. Set by the SDK method that makes the request.
     */
    @get:JvmSynthetic internal val qualifiers: Map<String, String>? = null,
    /**
     * The accumulator that an SDK helper feeds with the events of the API call's streamed Message
     * as the application reads them. The call's span reads the Message from it, in place of an
     * accumulator of its own, so that the stream is accumulated once.
     */
    @get:JvmSynthetic internal val streamAccumulator: BetaMessageAccumulator? = null,
    /** The OpenTelemetry context of the API call that sends the request, while its span is open. */
    @get:JvmSynthetic internal val apiCallOtelContext: Context? = null,
) {

    fun responseValidation(): Optional<Boolean> = Optional.ofNullable(responseValidation)

    fun timeout(): Optional<Timeout> = Optional.ofNullable(timeout)

    fun followRedirects(): Optional<Boolean> = Optional.ofNullable(followRedirects)

    fun openTelemetry(): Optional<OpenTelemetryRequestOptions> = Optional.ofNullable(openTelemetry)

    fun fallbackState(): Optional<BetaFallbackState> = Optional.ofNullable(fallbackState)

    companion object {

        private val NONE = builder().build()

        @JvmStatic fun none() = NONE

        @JvmSynthetic
        internal fun from(clientOptions: ClientOptions): RequestOptions =
            builder()
                .responseValidation(clientOptions.responseValidation)
                .timeout(clientOptions.timeout)
                .build()

        @JvmStatic fun builder() = Builder()

        /**
         * Maximum token count for non-streaming requests for specific models. Requests with models
         * in this map will require streaming for token counts exceeding the specified limit.
         */
        private val MODEL_NONSTREAMING_TOKENS =
            mapOf(
                "claude-opus-4-20250514" to 8_192,
                "claude-4-opus-20250514" to 8_192,
                "claude-opus-4-0" to 8_192,
                "anthropic.claude-opus-4-20250514-v1:0" to 8_192,
                "claude-opus-4@20250514" to 8_192,
                "anthropic.claude-opus-4-1-20250805-v1:0" to 8192,
                "claude-opus-4-1@20250805" to 8192,
            )
    }

    @JvmSynthetic
    internal fun applyDefaultTimeoutFromMaxTokens(
        maxTokens: Long,
        isStreaming: Boolean,
        model: String? = null,
    ): RequestOptions {
        if (timeout != null) {
            // We only want to throw below if the user didn't set a custom timeout.
            return this
        }

        val requestOptions =
            if (isStreaming) {
                applyDefaults(builder().timeoutFromMaxTokensStreaming(maxTokens).build())
            } else {
                applyDefaults(builder().timeoutFromMaxTokensNonStreaming(maxTokens).build())
            }

        // Check model-specific token limits for non-streaming requests
        val maxNonStreamingTokens = model?.let { MODEL_NONSTREAMING_TOKENS[it] }
        val exceedsModelLimit = maxNonStreamingTokens != null && maxTokens > maxNonStreamingTokens

        require(
            isStreaming ||
                !(exceedsModelLimit || requestOptions.timeout!!.request() > Duration.ofMinutes(10))
        ) {
            """
            Streaming is required for operations that may take longer than 10 minutes.

            See https://platform.claude.com/docs/en/cli-sdks-libraries/sdks/java#streaming for more details.
            """
                .trimIndent()
        }

        return requestOptions
    }

    fun toBuilder(): Builder = Builder().from(this)

    fun applyDefaults(options: RequestOptions): RequestOptions =
        RequestOptions(
            responseValidation = responseValidation ?: options.responseValidation,
            timeout =
                if (options.timeout != null && timeout != null) timeout.assign(options.timeout)
                else timeout ?: options.timeout,
            followRedirects = followRedirects ?: options.followRedirects,
            fallbackState = fallbackState ?: options.fallbackState,
            openTelemetry = openTelemetry ?: options.openTelemetry,
            qualifiers = qualifiers ?: options.qualifiers,
            streamAccumulator = streamAccumulator ?: options.streamAccumulator,
            apiCallOtelContext = apiCallOtelContext ?: options.apiCallOtelContext,
        )

    /** These options for a request to the operation that [qualifiers] tell apart. */
    @JvmSynthetic
    internal fun withQualifiers(qualifiers: Map<String, String>): RequestOptions =
        copy(qualifiers = qualifiers)

    /**
     * These options for an API call whose streamed Message an SDK helper accumulates in
     * [accumulator], which it feeds with every event that the application reads.
     */
    @JvmSynthetic
    internal fun withStreamAccumulator(accumulator: BetaMessageAccumulator): RequestOptions =
        copy(streamAccumulator = accumulator)

    /** These options for a request that the API call with [apiCallOtelContext] sends. */
    @JvmSynthetic
    internal fun withApiCallOtelContext(apiCallOtelContext: Context): RequestOptions =
        copy(apiCallOtelContext = apiCallOtelContext)

    private fun copy(
        qualifiers: Map<String, String>? = this.qualifiers,
        streamAccumulator: BetaMessageAccumulator? = this.streamAccumulator,
        apiCallOtelContext: Context? = this.apiCallOtelContext,
    ): RequestOptions =
        RequestOptions(
            responseValidation,
            timeout,
            followRedirects,
            fallbackState,
            openTelemetry,
            qualifiers,
            streamAccumulator,
            apiCallOtelContext,
        )

    class Builder internal constructor() {

        private var responseValidation: Boolean? = null
        private var timeout: Timeout? = null
        private var followRedirects: Boolean? = null
        private var fallbackState: BetaFallbackState? = null
        private var openTelemetry: OpenTelemetryRequestOptions? = null
        private var qualifiers: Map<String, String>? = null
        private var streamAccumulator: BetaMessageAccumulator? = null
        private var apiCallOtelContext: Context? = null

        @JvmSynthetic
        internal fun from(requestOptions: RequestOptions) = apply {
            responseValidation = requestOptions.responseValidation
            timeout = requestOptions.timeout
            followRedirects = requestOptions.followRedirects
            fallbackState = requestOptions.fallbackState
            openTelemetry = requestOptions.openTelemetry
            qualifiers = requestOptions.qualifiers
            streamAccumulator = requestOptions.streamAccumulator
            apiCallOtelContext = requestOptions.apiCallOtelContext
        }

        /**
         * Whether to call `validate` on the response before returning it.
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

        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        /**
         * Whether to follow redirects.
         *
         * Defaults to true.
         */
        fun followRedirects(followRedirects: Boolean) = apply {
            this.followRedirects = followRedirects
        }

        /** The OpenTelemetry tracing of this request. */
        fun openTelemetry(openTelemetry: OpenTelemetryRequestOptions) = apply {
            this.openTelemetry = openTelemetry
        }

        /**
         * Sticky state for [com.anthropic.helpers.BetaRefusalFallbackInterceptor].
         *
         * The interceptor records which fallback it settled on, so requests sharing the state skip
         * models that already refused. Pass the same object across whatever scope the pin should
         * apply to — typically a conversation.
         */
        fun fallbackState(fallbackState: BetaFallbackState) = apply {
            this.fallbackState = fallbackState
        }

        @JvmSynthetic
        internal fun timeoutFromMaxTokensStreaming(maxTokens: Long) = apply {
            // default to a 10 minute timeout and steadily increase that based on token count with a
            // cap at 1 hour
            val timeout =
                Duration.ofSeconds(
                    minOf(
                        60 * 60, // 1 hour max
                        maxOf(
                            10 * 60, // 10 minute minimum
                            60 * 60 * maxTokens / 128_000,
                        ),
                    )
                )
            timeout(Timeout.builder().read(timeout).request(timeout).build())
        }

        @JvmSynthetic
        internal fun timeoutFromMaxTokensNonStreaming(maxTokens: Long) = apply {
            // For non-streaming requests, we generally want a shorter timeout
            // Default to 30 seconds and scale up with token count, capping at 10 minutes
            val timeout =
                Duration.ofSeconds(
                    minOf(
                        10 * 60, // 10 minute max
                        maxOf(
                            30, // 30 second minimum
                            30 * maxTokens / 1_000,
                        ),
                    )
                )
            timeout(Timeout.builder().read(timeout).request(timeout).build())
        }

        fun build(): RequestOptions =
            RequestOptions(
                responseValidation,
                timeout,
                followRedirects,
                fallbackState,
                openTelemetry,
                qualifiers,
                streamAccumulator,
                apiCallOtelContext,
            )
    }
}
