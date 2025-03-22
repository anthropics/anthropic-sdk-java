// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaNotFoundErrorTest {

    @Test
    fun create() {
        val betaNotFoundError = BetaNotFoundError.builder().message("message").build()

        assertThat(betaNotFoundError.message()).isEqualTo("message")
    }
}
