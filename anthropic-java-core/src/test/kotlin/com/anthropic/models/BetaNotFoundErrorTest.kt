// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaNotFoundErrorTest {

    @Test
    fun createBetaNotFoundError() {
        val betaNotFoundError =
            BetaNotFoundError.builder()
                .message("message")
                .type(BetaNotFoundError.Type.NOT_FOUND_ERROR)
                .build()
        assertThat(betaNotFoundError).isNotNull
        assertThat(betaNotFoundError.message()).isEqualTo("message")
        assertThat(betaNotFoundError.type()).isEqualTo(BetaNotFoundError.Type.NOT_FOUND_ERROR)
    }
}