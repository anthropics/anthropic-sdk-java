// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGatewayTimeoutErrorTest {

    @Test
    fun create() {
        val betaGatewayTimeoutError = BetaGatewayTimeoutError.builder().message("message").build()

        assertThat(betaGatewayTimeoutError.message()).isEqualTo("message")
    }
}
