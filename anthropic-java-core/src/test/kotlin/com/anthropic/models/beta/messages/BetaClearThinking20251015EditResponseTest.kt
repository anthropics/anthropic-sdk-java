// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClearThinking20251015EditResponseTest {

    @Test
    fun create() {
        val betaClearThinking20251015EditResponse =
            BetaClearThinking20251015EditResponse.builder()
                .clearedInputTokens(0L)
                .clearedThinkingTurns(0L)
                .build()

        assertThat(betaClearThinking20251015EditResponse.clearedInputTokens()).isEqualTo(0L)
        assertThat(betaClearThinking20251015EditResponse.clearedThinkingTurns()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClearThinking20251015EditResponse =
            BetaClearThinking20251015EditResponse.builder()
                .clearedInputTokens(0L)
                .clearedThinkingTurns(0L)
                .build()

        val roundtrippedBetaClearThinking20251015EditResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClearThinking20251015EditResponse),
                jacksonTypeRef<BetaClearThinking20251015EditResponse>(),
            )

        assertThat(roundtrippedBetaClearThinking20251015EditResponse)
            .isEqualTo(betaClearThinking20251015EditResponse)
    }
}
