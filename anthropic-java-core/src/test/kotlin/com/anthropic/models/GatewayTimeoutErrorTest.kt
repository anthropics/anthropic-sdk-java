// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GatewayTimeoutErrorTest {

    @Test
    fun createGatewayTimeoutError() {
        val gatewayTimeoutError =
            GatewayTimeoutError.builder()
                .message("message")
                .type(GatewayTimeoutError.Type.TIMEOUT_ERROR)
                .build()
        assertThat(gatewayTimeoutError).isNotNull
        assertThat(gatewayTimeoutError.message()).isEqualTo("message")
        assertThat(gatewayTimeoutError.type()).isEqualTo(GatewayTimeoutError.Type.TIMEOUT_ERROR)
    }
}
