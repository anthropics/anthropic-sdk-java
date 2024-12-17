// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RateLimitErrorTest {

    @Test
    fun createRateLimitError() {
        val rateLimitError =
            RateLimitError.builder()
                .message("message")
                .type(RateLimitError.Type.RATE_LIMIT_ERROR)
                .build()
        assertThat(rateLimitError).isNotNull
        assertThat(rateLimitError.message()).isEqualTo("message")
        assertThat(rateLimitError.type()).isEqualTo(RateLimitError.Type.RATE_LIMIT_ERROR)
    }
}
