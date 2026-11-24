package com.anthropic.models.beta

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRegionNotSupportedErrorDetailsTest {

    @Test
    fun create() {
        val betaRegionNotSupportedErrorDetails =
            BetaRegionNotSupportedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRegionNotSupportedErrorDetails =
            BetaRegionNotSupportedErrorDetails.builder().build()

        val roundtrippedBetaRegionNotSupportedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRegionNotSupportedErrorDetails),
                jacksonTypeRef<BetaRegionNotSupportedErrorDetails>(),
            )

        assertThat(roundtrippedBetaRegionNotSupportedErrorDetails)
            .isEqualTo(betaRegionNotSupportedErrorDetails)
    }
}
