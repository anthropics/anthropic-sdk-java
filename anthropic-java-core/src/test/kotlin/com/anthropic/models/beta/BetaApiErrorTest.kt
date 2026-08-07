// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiErrorTest {

    @Test
    fun create() {
        val betaApiError = BetaApiError.of("message")

        assertThat(betaApiError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiError = BetaApiError.of("message")

        val roundtrippedBetaApiError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiError),
                jacksonTypeRef<BetaApiError>(),
            )

        assertThat(roundtrippedBetaApiError).isEqualTo(betaApiError)
    }
}
