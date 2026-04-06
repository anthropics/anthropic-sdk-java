// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingCapabilityTest {

    @Test
    fun create() {
        val betaThinkingCapability =
            BetaThinkingCapability.builder()
                .supported(true)
                .types(
                    BetaThinkingTypes.builder()
                        .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                        .enabled(BetaCapabilitySupport.builder().supported(true).build())
                        .build()
                )
                .build()

        assertThat(betaThinkingCapability.supported()).isEqualTo(true)
        assertThat(betaThinkingCapability.types())
            .isEqualTo(
                BetaThinkingTypes.builder()
                    .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                    .enabled(BetaCapabilitySupport.builder().supported(true).build())
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingCapability =
            BetaThinkingCapability.builder()
                .supported(true)
                .types(
                    BetaThinkingTypes.builder()
                        .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                        .enabled(BetaCapabilitySupport.builder().supported(true).build())
                        .build()
                )
                .build()

        val roundtrippedBetaThinkingCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingCapability),
                jacksonTypeRef<BetaThinkingCapability>(),
            )

        assertThat(roundtrippedBetaThinkingCapability).isEqualTo(betaThinkingCapability)
    }
}
