// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInvalidRequestErrorTest {

    @Test
    fun create() {
        val betaInvalidRequestError = BetaInvalidRequestError.of("message")

        assertThat(betaInvalidRequestError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInvalidRequestError = BetaInvalidRequestError.of("message")

        val roundtrippedBetaInvalidRequestError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInvalidRequestError),
                jacksonTypeRef<BetaInvalidRequestError>(),
            )

        assertThat(roundtrippedBetaInvalidRequestError).isEqualTo(betaInvalidRequestError)
    }
}
