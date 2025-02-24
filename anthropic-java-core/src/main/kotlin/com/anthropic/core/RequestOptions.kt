package com.anthropic.core

import java.time.Duration

class RequestOptions private constructor(val responseValidation: Boolean?, val timeout: Timeout?) {
    fun applyDefaults(options: RequestOptions): RequestOptions {
        return RequestOptions(
            responseValidation = this.responseValidation ?: options.responseValidation,
            timeout = this.timeout ?: options.timeout,
        )
    }

    @JvmSynthetic
    internal fun applyDefaultTimeoutFromMaxTokens(
        maxTokens: Long,
        isStreaming: Boolean,
    ): RequestOptions {
        if (timeout != null) {
            // We only want to warn below if the user didn't set a custom timeout.
            return this
        }

        val requestOptions = applyDefaults(builder().timeoutFromMaxTokens(maxTokens).build())
        require(isStreaming || requestOptions.timeout!!.total <= Duration.ofMinutes(10)) {
            """
                Streaming is required for operations that may take longer than 10 minutes.

                See https://github.com/anthropics/anthropic-sdk-java#streaming for more details.
            """
                .trimIndent()
        }

        return requestOptions
    }

    companion object {

        private val NONE = builder().build()

        @JvmStatic fun none() = NONE

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var responseValidation: Boolean? = null
        private var timeout: Timeout? = null

        fun responseValidation(responseValidation: Boolean) = apply {
            this.responseValidation = responseValidation
        }

        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        fun timeout(timeout: Duration) = timeout(Timeout.builder().total(timeout).build())

        @JvmSynthetic
        internal fun timeoutFromMaxTokens(maxTokens: Long) = apply {
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
            timeout(Timeout.builder().read(timeout).total(timeout).build())
        }

        fun build(): RequestOptions {
            return RequestOptions(responseValidation, timeout)
        }
    }
}
