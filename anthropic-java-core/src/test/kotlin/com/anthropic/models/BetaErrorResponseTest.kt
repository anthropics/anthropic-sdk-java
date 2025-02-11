// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaErrorResponseTest {

    @Test
    fun createBetaErrorResponse() {
        val betaErrorResponse = BetaErrorResponse.builder().invalidRequestError("message").build()
        assertThat(betaErrorResponse).isNotNull
        assertThat(betaErrorResponse.error())
            .isEqualTo(
                BetaError.ofInvalidRequest(
                    BetaInvalidRequestError.builder().message("message").build()
                )
            )
    }
}
