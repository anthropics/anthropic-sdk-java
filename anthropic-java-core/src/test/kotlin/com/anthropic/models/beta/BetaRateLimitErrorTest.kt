// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRateLimitErrorTest {

    @Test
    fun create() {
        val betaRateLimitError = BetaRateLimitError.of("message")

        assertThat(betaRateLimitError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRateLimitError = BetaRateLimitError.of("message")

        val roundtrippedBetaRateLimitError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRateLimitError),
                jacksonTypeRef<BetaRateLimitError>(),
            )

        assertThat(roundtrippedBetaRateLimitError).isEqualTo(betaRateLimitError)
    }
}
