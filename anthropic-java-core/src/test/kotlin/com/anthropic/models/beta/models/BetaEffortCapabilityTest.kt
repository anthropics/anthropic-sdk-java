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
                .high(BetaCapabilitySupport.of(true))
                .low(BetaCapabilitySupport.of(true))
                .max(BetaCapabilitySupport.of(true))
                .medium(BetaCapabilitySupport.of(true))
                .supported(true)
                .xhigh(BetaCapabilitySupport.of(true))
                .build()

        assertThat(betaEffortCapability.high()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaEffortCapability.low()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaEffortCapability.max()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaEffortCapability.medium()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaEffortCapability.supported()).isEqualTo(true)
        assertThat(betaEffortCapability.xhigh()).contains(BetaCapabilitySupport.of(true))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEffortCapability =
            BetaEffortCapability.builder()
                .high(BetaCapabilitySupport.of(true))
                .low(BetaCapabilitySupport.of(true))
                .max(BetaCapabilitySupport.of(true))
                .medium(BetaCapabilitySupport.of(true))
                .supported(true)
                .xhigh(BetaCapabilitySupport.of(true))
                .build()

        val roundtrippedBetaEffortCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEffortCapability),
                jacksonTypeRef<BetaEffortCapability>(),
            )

        assertThat(roundtrippedBetaEffortCapability).isEqualTo(betaEffortCapability)
    }
}
