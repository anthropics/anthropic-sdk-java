// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingConfigEnabledTest {

    @Test
    fun create() {
        val betaThinkingConfigEnabled =
            BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        assertThat(betaThinkingConfigEnabled.budgetTokens()).isEqualTo(1024L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigEnabled =
            BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        val roundtrippedBetaThinkingConfigEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigEnabled),
                jacksonTypeRef<BetaThinkingConfigEnabled>(),
            )

        assertThat(roundtrippedBetaThinkingConfigEnabled).isEqualTo(betaThinkingConfigEnabled)
    }
}
