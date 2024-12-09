// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaAuthenticationErrorTest {

    @Test
    fun createBetaAuthenticationError() {
        val betaAuthenticationError =
            BetaAuthenticationError.builder()
                .message("message")
                .type(BetaAuthenticationError.Type.AUTHENTICATION_ERROR)
                .build()
        assertThat(betaAuthenticationError).isNotNull
        assertThat(betaAuthenticationError.message()).isEqualTo("message")
        assertThat(betaAuthenticationError.type())
            .isEqualTo(BetaAuthenticationError.Type.AUTHENTICATION_ERROR)
    }
}
