// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingDeltaTest {

    @Test
    fun create() {
        val betaThinkingDelta = BetaThinkingDelta.builder().thinking("thinking").build()

        assertThat(betaThinkingDelta.thinking()).isEqualTo("thinking")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingDelta = BetaThinkingDelta.builder().thinking("thinking").build()

        val roundtrippedBetaThinkingDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingDelta),
                jacksonTypeRef<BetaThinkingDelta>(),
            )

        assertThat(roundtrippedBetaThinkingDelta).isEqualTo(betaThinkingDelta)
    }
}
