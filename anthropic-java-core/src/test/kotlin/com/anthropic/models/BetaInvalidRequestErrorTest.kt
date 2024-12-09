// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaInvalidRequestErrorTest {

    @Test
    fun createBetaInvalidRequestError() {
        val betaInvalidRequestError =
            BetaInvalidRequestError.builder()
                .message("message")
                .type(BetaInvalidRequestError.Type.INVALID_REQUEST_ERROR)
                .build()
        assertThat(betaInvalidRequestError).isNotNull
        assertThat(betaInvalidRequestError.message()).isEqualTo("message")
        assertThat(betaInvalidRequestError.type())
            .isEqualTo(BetaInvalidRequestError.Type.INVALID_REQUEST_ERROR)
    }
}
