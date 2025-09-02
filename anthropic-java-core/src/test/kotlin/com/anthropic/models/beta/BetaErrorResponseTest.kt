// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaErrorResponseTest {

    @Test
    fun create() {
        val betaErrorResponse =
            BetaErrorResponse.builder()
                .invalidRequestError("message")
                .requestId("request_id")
                .build()

        assertThat(betaErrorResponse.error())
            .isEqualTo(
                BetaError.ofInvalidRequest(
                    BetaInvalidRequestError.builder().message("message").build()
                )
            )
        assertThat(betaErrorResponse.requestId()).contains("request_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaErrorResponse =
            BetaErrorResponse.builder()
                .invalidRequestError("message")
                .requestId("request_id")
                .build()

        val roundtrippedBetaErrorResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaErrorResponse),
                jacksonTypeRef<BetaErrorResponse>(),
            )

        assertThat(roundtrippedBetaErrorResponse).isEqualTo(betaErrorResponse)
    }
}
