// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GatewayTimeoutErrorTest {

    @Test
    fun create() {
        val gatewayTimeoutError = GatewayTimeoutError.builder().message("message").build()

        assertThat(gatewayTimeoutError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val gatewayTimeoutError = GatewayTimeoutError.builder().message("message").build()

        val roundtrippedGatewayTimeoutError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(gatewayTimeoutError),
                jacksonTypeRef<GatewayTimeoutError>(),
            )

        assertThat(roundtrippedGatewayTimeoutError).isEqualTo(gatewayTimeoutError)
    }
}
