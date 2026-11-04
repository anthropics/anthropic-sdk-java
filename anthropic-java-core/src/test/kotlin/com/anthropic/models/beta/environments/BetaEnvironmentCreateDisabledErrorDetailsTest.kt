package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEnvironmentCreateDisabledErrorDetailsTest {

    @Test
    fun create() {
        val betaEnvironmentCreateDisabledErrorDetails =
            BetaEnvironmentCreateDisabledErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEnvironmentCreateDisabledErrorDetails =
            BetaEnvironmentCreateDisabledErrorDetails.builder().build()

        val roundtrippedBetaEnvironmentCreateDisabledErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEnvironmentCreateDisabledErrorDetails),
                jacksonTypeRef<BetaEnvironmentCreateDisabledErrorDetails>(),
            )

        assertThat(roundtrippedBetaEnvironmentCreateDisabledErrorDetails)
            .isEqualTo(betaEnvironmentCreateDisabledErrorDetails)
    }
}
