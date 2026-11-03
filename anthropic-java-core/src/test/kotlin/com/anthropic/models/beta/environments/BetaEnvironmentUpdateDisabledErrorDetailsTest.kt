package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEnvironmentUpdateDisabledErrorDetailsTest {

    @Test
    fun create() {
        val betaEnvironmentUpdateDisabledErrorDetails =
            BetaEnvironmentUpdateDisabledErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEnvironmentUpdateDisabledErrorDetails =
            BetaEnvironmentUpdateDisabledErrorDetails.builder().build()

        val roundtrippedBetaEnvironmentUpdateDisabledErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEnvironmentUpdateDisabledErrorDetails),
                jacksonTypeRef<BetaEnvironmentUpdateDisabledErrorDetails>(),
            )

        assertThat(roundtrippedBetaEnvironmentUpdateDisabledErrorDetails)
            .isEqualTo(betaEnvironmentUpdateDisabledErrorDetails)
    }
}
