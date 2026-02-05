// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingConfigAdaptiveTest {

    @Test
    fun create() {
        val betaThinkingConfigAdaptive = BetaThinkingConfigAdaptive.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigAdaptive = BetaThinkingConfigAdaptive.builder().build()

        val roundtrippedBetaThinkingConfigAdaptive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigAdaptive),
                jacksonTypeRef<BetaThinkingConfigAdaptive>(),
            )

        assertThat(roundtrippedBetaThinkingConfigAdaptive).isEqualTo(betaThinkingConfigAdaptive)
    }
}
