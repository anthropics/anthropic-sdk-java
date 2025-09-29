// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClearToolUses20250919EditTest {

    @Test
    fun create() {
        val betaClearToolUses20250919Edit =
            BetaClearToolUses20250919Edit.builder()
                .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                .clearToolInputs(true)
                .addExcludeTool("string")
                .keep(BetaToolUsesKeep.builder().value(0L).build())
                .inputTokensTrigger(1L)
                .build()

        assertThat(betaClearToolUses20250919Edit.clearAtLeast())
            .contains(BetaInputTokensClearAtLeast.builder().value(0L).build())
        assertThat(betaClearToolUses20250919Edit.clearToolInputs())
            .contains(BetaClearToolUses20250919Edit.ClearToolInputs.ofBool(true))
        assertThat(betaClearToolUses20250919Edit.excludeTools().getOrNull())
            .containsExactly("string")
        assertThat(betaClearToolUses20250919Edit.keep())
            .contains(BetaToolUsesKeep.builder().value(0L).build())
        assertThat(betaClearToolUses20250919Edit.trigger())
            .contains(
                BetaClearToolUses20250919Edit.Trigger.ofInputTokens(
                    BetaInputTokensTrigger.builder().value(1L).build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClearToolUses20250919Edit =
            BetaClearToolUses20250919Edit.builder()
                .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                .clearToolInputs(true)
                .addExcludeTool("string")
                .keep(BetaToolUsesKeep.builder().value(0L).build())
                .inputTokensTrigger(1L)
                .build()

        val roundtrippedBetaClearToolUses20250919Edit =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClearToolUses20250919Edit),
                jacksonTypeRef<BetaClearToolUses20250919Edit>(),
            )

        assertThat(roundtrippedBetaClearToolUses20250919Edit)
            .isEqualTo(betaClearToolUses20250919Edit)
    }
}
