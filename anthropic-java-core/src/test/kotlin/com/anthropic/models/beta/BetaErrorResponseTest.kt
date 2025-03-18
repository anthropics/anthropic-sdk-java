// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaErrorResponseTest {

    @Test
    fun create() {
        val betaErrorResponse = BetaErrorResponse.builder().invalidRequestError("message").build()

        assertThat(betaErrorResponse.error())
            .isEqualTo(
                BetaError.ofInvalidRequest(
                    BetaInvalidRequestError.builder().message("message").build()
                )
            )
    }
}
