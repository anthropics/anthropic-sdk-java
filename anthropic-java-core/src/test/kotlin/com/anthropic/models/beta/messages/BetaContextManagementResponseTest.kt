// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContextManagementResponseTest {

    @Test
    fun create() {
        val betaContextManagementResponse =
            BetaContextManagementResponse.builder()
                .addAppliedEdit(
                    BetaClearToolUses20250919EditResponse.builder()
                        .clearedInputTokens(0L)
                        .clearedToolUses(0L)
                        .build()
                )
                .build()

        assertThat(betaContextManagementResponse.appliedEdits())
            .containsExactly(
                BetaClearToolUses20250919EditResponse.builder()
                    .clearedInputTokens(0L)
                    .clearedToolUses(0L)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContextManagementResponse =
            BetaContextManagementResponse.builder()
                .addAppliedEdit(
                    BetaClearToolUses20250919EditResponse.builder()
                        .clearedInputTokens(0L)
                        .clearedToolUses(0L)
                        .build()
                )
                .build()

        val roundtrippedBetaContextManagementResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContextManagementResponse),
                jacksonTypeRef<BetaContextManagementResponse>(),
            )

        assertThat(roundtrippedBetaContextManagementResponse)
            .isEqualTo(betaContextManagementResponse)
    }
}
