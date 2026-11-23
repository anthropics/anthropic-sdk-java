package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUnsupportedRegionErrorDetailsTest {

    @Test
    fun create() {
        val betaUnsupportedRegionErrorDetails = BetaUnsupportedRegionErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUnsupportedRegionErrorDetails = BetaUnsupportedRegionErrorDetails.builder().build()

        val roundtrippedBetaUnsupportedRegionErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUnsupportedRegionErrorDetails),
                jacksonTypeRef<BetaUnsupportedRegionErrorDetails>(),
            )

        assertThat(roundtrippedBetaUnsupportedRegionErrorDetails)
            .isEqualTo(betaUnsupportedRegionErrorDetails)
    }
}
