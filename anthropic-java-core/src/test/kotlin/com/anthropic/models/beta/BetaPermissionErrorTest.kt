// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPermissionErrorTest {

    @Test
    fun create() {
        val betaPermissionError = BetaPermissionError.of("message")

        assertThat(betaPermissionError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPermissionError = BetaPermissionError.of("message")

        val roundtrippedBetaPermissionError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPermissionError),
                jacksonTypeRef<BetaPermissionError>(),
            )

        assertThat(roundtrippedBetaPermissionError).isEqualTo(betaPermissionError)
    }
}
