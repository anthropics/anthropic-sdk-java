// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaGatewayTimeoutErrorTest {

    @Test
    fun createBetaGatewayTimeoutError() {
        val betaGatewayTimeoutError =
            BetaGatewayTimeoutError.builder()
                .message("message")
                .type(BetaGatewayTimeoutError.Type.TIMEOUT_ERROR)
                .build()
        assertThat(betaGatewayTimeoutError).isNotNull
        assertThat(betaGatewayTimeoutError.message()).isEqualTo("message")
        assertThat(betaGatewayTimeoutError.type())
            .isEqualTo(BetaGatewayTimeoutError.Type.TIMEOUT_ERROR)
    }
}
