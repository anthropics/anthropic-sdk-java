// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEffortCapabilityTest {

    @Test
    fun create() {
        val betaEffortCapability =
            BetaEffortCapability.builder()
                .high(BetaCapabilitySupport.builder().supported(true).build())
                .low(BetaCapabilitySupport.builder().supported(true).build())
                .max(BetaCapabilitySupport.builder().supported(true).build())
                .medium(BetaCapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        assertThat(betaEffortCapability.high())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaEffortCapability.low())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaEffortCapability.max())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaEffortCapability.medium())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaEffortCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEffortCapability =
            BetaEffortCapability.builder()
                .high(BetaCapabilitySupport.builder().supported(true).build())
                .low(BetaCapabilitySupport.builder().supported(true).build())
                .max(BetaCapabilitySupport.builder().supported(true).build())
                .medium(BetaCapabilitySupport.builder().supported(true).build())
                .supported(true)
                .build()

        val roundtrippedBetaEffortCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEffortCapability),
                jacksonTypeRef<BetaEffortCapability>(),
            )

        assertThat(roundtrippedBetaEffortCapability).isEqualTo(betaEffortCapability)
    }
}
