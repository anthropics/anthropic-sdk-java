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
    }

    @JvmSynthetic
    internal fun applyDefaultTimeoutFromMaxTokens(
        maxTokens: Long,
        isStreaming: Boolean,
    ): RequestOptions {
        if (timeout != null) {
            // We only want to throw below if the user didn't set a custom timeout.
            return this
        }

        val requestOptions = applyDefaults(builder().timeoutFromMaxTokens(maxTokens).build())
        require(isStreaming || requestOptions.timeout!!.request() <= Duration.ofMinutes(10)) {
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

        fun responseValidation(responseValidation: Boolean) = apply {
            this.responseValidation = responseValidation
        }

        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

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
            timeout(Timeout.builder().read(timeout).request(timeout).build())
        }

        fun build(): RequestOptions = RequestOptions(responseValidation, timeout)
    }
}
