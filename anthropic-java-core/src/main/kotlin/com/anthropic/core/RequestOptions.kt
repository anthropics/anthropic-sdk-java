package com.anthropic.core

import java.time.Duration

class RequestOptions private constructor(val responseValidation: Boolean?, val timeout: Timeout?) {

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
                "claude-opus-4-1-20250805" to 8192,
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

            See https://github.com/anthropics/anthropic-sdk-java#streaming for more details.
            """
                .trimIndent()
        }

        return requestOptions
    }

    fun applyDefaults(options: RequestOptions): RequestOptions =
        RequestOptions(
            responseValidation = responseValidation ?: options.responseValidation,
            timeout =
                if (options.timeout != null && timeout != null) timeout.assign(options.timeout)
                else timeout ?: options.timeout,
        )

    class Builder internal constructor() {

        private var responseValidation: Boolean? = null
        private var timeout: Timeout? = null

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

        fun build(): RequestOptions = RequestOptions(responseValidation, timeout)
    }
}
