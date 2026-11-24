package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalKeyNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val betaExternalKeyNotFoundErrorDetails =
            BetaExternalKeyNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalKeyNotFoundErrorDetails =
            BetaExternalKeyNotFoundErrorDetails.builder().build()

        val roundtrippedBetaExternalKeyNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalKeyNotFoundErrorDetails),
                jacksonTypeRef<BetaExternalKeyNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedBetaExternalKeyNotFoundErrorDetails)
            .isEqualTo(betaExternalKeyNotFoundErrorDetails)
    }
}
