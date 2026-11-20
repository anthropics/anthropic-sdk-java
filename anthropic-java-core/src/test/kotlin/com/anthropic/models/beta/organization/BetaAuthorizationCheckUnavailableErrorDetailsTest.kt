package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAuthorizationCheckUnavailableErrorDetailsTest {

    @Test
    fun create() {
        val betaAuthorizationCheckUnavailableErrorDetails =
            BetaAuthorizationCheckUnavailableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAuthorizationCheckUnavailableErrorDetails =
            BetaAuthorizationCheckUnavailableErrorDetails.builder().build()

        val roundtrippedBetaAuthorizationCheckUnavailableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAuthorizationCheckUnavailableErrorDetails),
                jacksonTypeRef<BetaAuthorizationCheckUnavailableErrorDetails>(),
            )

        assertThat(roundtrippedBetaAuthorizationCheckUnavailableErrorDetails)
            .isEqualTo(betaAuthorizationCheckUnavailableErrorDetails)
    }
}
