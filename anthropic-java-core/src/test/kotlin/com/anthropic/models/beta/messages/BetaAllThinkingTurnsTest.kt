// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAllThinkingTurnsTest {

    @Test
    fun create() {
        val betaAllThinkingTurns = BetaAllThinkingTurns.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAllThinkingTurns = BetaAllThinkingTurns.builder().build()

        val roundtrippedBetaAllThinkingTurns =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAllThinkingTurns),
                jacksonTypeRef<BetaAllThinkingTurns>(),
            )

        assertThat(roundtrippedBetaAllThinkingTurns).isEqualTo(betaAllThinkingTurns)
    }
}
