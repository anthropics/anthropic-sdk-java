// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAuthenticationErrorTest {

    @Test
    fun create() {
        val betaAuthenticationError = BetaAuthenticationError.builder().message("message").build()

        assertThat(betaAuthenticationError.message()).isEqualTo("message")
    }
}
