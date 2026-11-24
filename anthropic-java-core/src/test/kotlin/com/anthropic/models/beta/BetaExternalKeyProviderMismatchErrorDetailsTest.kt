package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalKeyProviderMismatchErrorDetailsTest {

    @Test
    fun create() {
        val betaExternalKeyProviderMismatchErrorDetails =
            BetaExternalKeyProviderMismatchErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalKeyProviderMismatchErrorDetails =
            BetaExternalKeyProviderMismatchErrorDetails.builder().build()

        val roundtrippedBetaExternalKeyProviderMismatchErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalKeyProviderMismatchErrorDetails),
                jacksonTypeRef<BetaExternalKeyProviderMismatchErrorDetails>(),
            )

        assertThat(roundtrippedBetaExternalKeyProviderMismatchErrorDetails)
            .isEqualTo(betaExternalKeyProviderMismatchErrorDetails)
    }
}
