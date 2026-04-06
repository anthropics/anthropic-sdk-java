// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingTypesTest {

    @Test
    fun create() {
        val betaThinkingTypes =
            BetaThinkingTypes.builder()
                .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                .enabled(BetaCapabilitySupport.builder().supported(true).build())
                .build()

        assertThat(betaThinkingTypes.adaptive())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
        assertThat(betaThinkingTypes.enabled())
            .isEqualTo(BetaCapabilitySupport.builder().supported(true).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingTypes =
            BetaThinkingTypes.builder()
                .adaptive(BetaCapabilitySupport.builder().supported(true).build())
                .enabled(BetaCapabilitySupport.builder().supported(true).build())
                .build()

        val roundtrippedBetaThinkingTypes =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingTypes),
                jacksonTypeRef<BetaThinkingTypes>(),
            )

        assertThat(roundtrippedBetaThinkingTypes).isEqualTo(betaThinkingTypes)
    }
}
