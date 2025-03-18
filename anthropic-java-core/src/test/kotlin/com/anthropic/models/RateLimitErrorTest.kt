// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitErrorTest {

    @Test
    fun create() {
        val rateLimitError = RateLimitError.builder().message("message").build()

        assertThat(rateLimitError.message()).isEqualTo("message")
    }
}
