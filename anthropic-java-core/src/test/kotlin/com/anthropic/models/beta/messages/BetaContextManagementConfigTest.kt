// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContextManagementConfigTest {

    @Test
    fun create() {
        val betaContextManagementConfig =
            BetaContextManagementConfig.builder()
                .addEdit(
                    BetaClearToolUses20250919Edit.builder()
                        .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                        .clearToolInputs(true)
                        .addExcludeTool("string")
                        .keep(BetaToolUsesKeep.builder().value(0L).build())
                        .inputTokensTrigger(1L)
                        .build()
                )
                .build()

        assertThat(betaContextManagementConfig.edits().getOrNull())
            .containsExactly(
                BetaContextManagementConfig.Edit.ofClearToolUses20250919(
                    BetaClearToolUses20250919Edit.builder()
                        .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                        .clearToolInputs(true)
                        .addExcludeTool("string")
                        .keep(BetaToolUsesKeep.builder().value(0L).build())
                        .inputTokensTrigger(1L)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContextManagementConfig =
            BetaContextManagementConfig.builder()
                .addEdit(
                    BetaClearToolUses20250919Edit.builder()
                        .clearAtLeast(BetaInputTokensClearAtLeast.builder().value(0L).build())
                        .clearToolInputs(true)
                        .addExcludeTool("string")
                        .keep(BetaToolUsesKeep.builder().value(0L).build())
                        .inputTokensTrigger(1L)
                        .build()
                )
                .build()

        val roundtrippedBetaContextManagementConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContextManagementConfig),
                jacksonTypeRef<BetaContextManagementConfig>(),
            )

        assertThat(roundtrippedBetaContextManagementConfig).isEqualTo(betaContextManagementConfig)
    }
}
