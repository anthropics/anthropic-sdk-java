// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaAuthenticationErrorTest {

    @Test
    fun createBetaAuthenticationError() {
        val betaAuthenticationError = BetaAuthenticationError.builder().message("message").build()
        assertThat(betaAuthenticationError).isNotNull
        assertThat(betaAuthenticationError.message()).isEqualTo("message")
    }
}
