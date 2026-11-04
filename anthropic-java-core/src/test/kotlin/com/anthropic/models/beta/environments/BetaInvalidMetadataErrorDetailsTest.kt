package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInvalidMetadataErrorDetailsTest {

    @Test
    fun create() {
        val betaInvalidMetadataErrorDetails = BetaInvalidMetadataErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInvalidMetadataErrorDetails = BetaInvalidMetadataErrorDetails.builder().build()

        val roundtrippedBetaInvalidMetadataErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInvalidMetadataErrorDetails),
                jacksonTypeRef<BetaInvalidMetadataErrorDetails>(),
            )

        assertThat(roundtrippedBetaInvalidMetadataErrorDetails)
            .isEqualTo(betaInvalidMetadataErrorDetails)
    }
}
