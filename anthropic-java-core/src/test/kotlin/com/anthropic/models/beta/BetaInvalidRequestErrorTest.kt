// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInvalidRequestErrorTest {

    @Test
    fun create() {
        val betaInvalidRequestError = BetaInvalidRequestError.builder().message("message").build()

        assertThat(betaInvalidRequestError.message()).isEqualTo("message")
    }
}
