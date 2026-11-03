package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEnvironmentNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val betaEnvironmentNotFoundErrorDetails =
            BetaEnvironmentNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEnvironmentNotFoundErrorDetails =
            BetaEnvironmentNotFoundErrorDetails.builder().build()

        val roundtrippedBetaEnvironmentNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEnvironmentNotFoundErrorDetails),
                jacksonTypeRef<BetaEnvironmentNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedBetaEnvironmentNotFoundErrorDetails)
            .isEqualTo(betaEnvironmentNotFoundErrorDetails)
    }
}
