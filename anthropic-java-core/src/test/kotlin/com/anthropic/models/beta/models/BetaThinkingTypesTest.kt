// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingTypesTest {

    @Test
    fun create() {
        val betaThinkingTypes =
            BetaThinkingTypes.builder()
                .adaptive(BetaCapabilitySupport.of(true))
                .enabled(BetaCapabilitySupport.of(true))
                .build()

        assertThat(betaThinkingTypes.adaptive()).isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaThinkingTypes.enabled()).isEqualTo(BetaCapabilitySupport.of(true))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingTypes =
            BetaThinkingTypes.builder()
                .adaptive(BetaCapabilitySupport.of(true))
                .enabled(BetaCapabilitySupport.of(true))
                .build()

        val roundtrippedBetaThinkingTypes =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingTypes),
                jacksonTypeRef<BetaThinkingTypes>(),
            )

        assertThat(roundtrippedBetaThinkingTypes).isEqualTo(betaThinkingTypes)
    }
}
