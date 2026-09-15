package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionCapabilityTest {

    @Test
    fun create() {
        val betaCompactionCapability =
            BetaCompactionCapability.builder()
                .summarize(BetaCapabilitySupport.of(true))
                .supported(true)
                .build()

        assertThat(betaCompactionCapability.summarize()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaCompactionCapability.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionCapability =
            BetaCompactionCapability.builder()
                .summarize(BetaCapabilitySupport.of(true))
                .supported(true)
                .build()

        val roundtrippedBetaCompactionCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionCapability),
                jacksonTypeRef<BetaCompactionCapability>(),
            )

        assertThat(roundtrippedBetaCompactionCapability).isEqualTo(betaCompactionCapability)
    }
}
