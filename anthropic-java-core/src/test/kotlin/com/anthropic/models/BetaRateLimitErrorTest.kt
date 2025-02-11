// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRateLimitErrorTest {

    @Test
    fun createBetaRateLimitError() {
        val betaRateLimitError = BetaRateLimitError.builder().message("message").build()
        assertThat(betaRateLimitError).isNotNull
        assertThat(betaRateLimitError.message()).isEqualTo("message")
    }
}
