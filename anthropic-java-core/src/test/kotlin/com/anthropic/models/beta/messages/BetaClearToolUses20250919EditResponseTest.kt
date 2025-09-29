// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClearToolUses20250919EditResponseTest {

    @Test
    fun create() {
        val betaClearToolUses20250919EditResponse =
            BetaClearToolUses20250919EditResponse.builder()
                .clearedInputTokens(0L)
                .clearedToolUses(0L)
                .build()

        assertThat(betaClearToolUses20250919EditResponse.clearedInputTokens()).isEqualTo(0L)
        assertThat(betaClearToolUses20250919EditResponse.clearedToolUses()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClearToolUses20250919EditResponse =
            BetaClearToolUses20250919EditResponse.builder()
                .clearedInputTokens(0L)
                .clearedToolUses(0L)
                .build()

        val roundtrippedBetaClearToolUses20250919EditResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClearToolUses20250919EditResponse),
                jacksonTypeRef<BetaClearToolUses20250919EditResponse>(),
            )

        assertThat(roundtrippedBetaClearToolUses20250919EditResponse)
            .isEqualTo(betaClearToolUses20250919EditResponse)
    }
}
