// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGatewayTimeoutErrorTest {

    @Test
    fun create() {
        val betaGatewayTimeoutError = BetaGatewayTimeoutError.of("message")

        assertThat(betaGatewayTimeoutError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGatewayTimeoutError = BetaGatewayTimeoutError.of("message")

        val roundtrippedBetaGatewayTimeoutError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGatewayTimeoutError),
                jacksonTypeRef<BetaGatewayTimeoutError>(),
            )

        assertThat(roundtrippedBetaGatewayTimeoutError).isEqualTo(betaGatewayTimeoutError)
    }
}
