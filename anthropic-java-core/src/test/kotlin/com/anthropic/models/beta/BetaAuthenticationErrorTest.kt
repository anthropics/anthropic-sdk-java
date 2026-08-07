// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAuthenticationErrorTest {

    @Test
    fun create() {
        val betaAuthenticationError = BetaAuthenticationError.of("message")

        assertThat(betaAuthenticationError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAuthenticationError = BetaAuthenticationError.of("message")

        val roundtrippedBetaAuthenticationError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAuthenticationError),
                jacksonTypeRef<BetaAuthenticationError>(),
            )

        assertThat(roundtrippedBetaAuthenticationError).isEqualTo(betaAuthenticationError)
    }
}
