package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInvalidConfigTypeChangeErrorDetailsTest {

    @Test
    fun create() {
        val betaInvalidConfigTypeChangeErrorDetails =
            BetaInvalidConfigTypeChangeErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInvalidConfigTypeChangeErrorDetails =
            BetaInvalidConfigTypeChangeErrorDetails.builder().build()

        val roundtrippedBetaInvalidConfigTypeChangeErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInvalidConfigTypeChangeErrorDetails),
                jacksonTypeRef<BetaInvalidConfigTypeChangeErrorDetails>(),
            )

        assertThat(roundtrippedBetaInvalidConfigTypeChangeErrorDetails)
            .isEqualTo(betaInvalidConfigTypeChangeErrorDetails)
    }
}
