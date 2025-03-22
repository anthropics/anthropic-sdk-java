// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRateLimitErrorTest {

    @Test
    fun create() {
        val betaRateLimitError = BetaRateLimitError.builder().message("message").build()

        assertThat(betaRateLimitError.message()).isEqualTo("message")
    }
}
