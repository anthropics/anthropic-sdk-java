// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRateLimitErrorTest {

    @Test
    fun createBetaRateLimitError() {
        val betaRateLimitError =
            BetaRateLimitError.builder()
                .message("message")
                .type(BetaRateLimitError.Type.RATE_LIMIT_ERROR)
                .build()
        assertThat(betaRateLimitError).isNotNull
        assertThat(betaRateLimitError.message()).isEqualTo("message")
        assertThat(betaRateLimitError.type()).isEqualTo(BetaRateLimitError.Type.RATE_LIMIT_ERROR)
    }
}
