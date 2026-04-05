package com.anthropic.core

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class RequestOptions private constructor(val responseValidation: Boolean?, val timeout: Timeout?) {

    companion object {

        private val NONE = builder().build()

        @JvmStatic fun none() = NONE

        @JvmSynthetic fun from(clientOptions: ClientOptions): RequestOptions =
            builder()
                .responseValidation(clientOptions.responseValidation)
                .timeout(clientOptions.timeout)
                .build()

        @JvmStatic fun builder() = Builder()

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

    fun applyDefaultTimeoutFromMaxTokens(
        maxTokens: Long,
        isStreaming: Boolean,
        model: String? = null,
    ): RequestOptions {
        if (timeout != null) return this

        val requestOptions =
            if (isStreaming) applyDefaults(builder().timeoutFromMaxTokensStreaming(maxTokens).build())
            else applyDefaults(builder().timeoutFromMaxTokensNonStreaming(maxTokens).build())

        val maxNonStreamingTokens = model?.let { MODEL_NONSTREAMING_TOKENS[it] }
        val exceedsModelLimit = maxNonStreamingTokens != null && maxTokens > maxNonStreamingTokens

        require(
            isStreaming ||
                !(exceedsModelLimit || requestOptions.timeout!!.request() > 10.minutes)
        ) {
            "Streaming is required for operations that may take longer than 10 minutes.\n\nSee https://github.com/anthropics/anthropic-sdk-java#streaming for more details."
        }

        return requestOptions
    }

    fun applyDefaults(options: RequestOptions): RequestOptions =
        RequestOptions(
            responseValidation = responseValidation ?: options.responseValidation,
            timeout = if (options.timeout != null && timeout != null) timeout.assign(options.timeout)
            else timeout ?: options.timeout,
        )

    class Builder internal constructor() {
        private var responseValidation: Boolean? = null
        private var timeout: Timeout? = null

        fun responseValidation(responseValidation: Boolean) = apply { this.responseValidation = responseValidation }
        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }
        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        fun timeoutFromMaxTokensStreaming(maxTokens: Long) = apply {
            val t = minOf(60 * 60, maxOf(10 * 60, 60 * 60 * maxTokens / 128_000)).seconds
            timeout(Timeout.builder().read(t).request(t).build())
        }

        fun timeoutFromMaxTokensNonStreaming(maxTokens: Long) = apply {
            val t = minOf(10 * 60, maxOf(30, 30 * maxTokens / 1_000)).seconds
            timeout(Timeout.builder().read(t).request(t).build())
        }

        fun build(): RequestOptions = RequestOptions(responseValidation, timeout)
    }
}
