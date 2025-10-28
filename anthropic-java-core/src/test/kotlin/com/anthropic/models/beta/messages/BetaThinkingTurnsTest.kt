// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingTurnsTest {

    @Test
    fun create() {
        val betaThinkingTurns = BetaThinkingTurns.builder().value(1L).build()

        assertThat(betaThinkingTurns.value()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingTurns = BetaThinkingTurns.builder().value(1L).build()

        val roundtrippedBetaThinkingTurns =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingTurns),
                jacksonTypeRef<BetaThinkingTurns>(),
            )

        assertThat(roundtrippedBetaThinkingTurns).isEqualTo(betaThinkingTurns)
    }
}
