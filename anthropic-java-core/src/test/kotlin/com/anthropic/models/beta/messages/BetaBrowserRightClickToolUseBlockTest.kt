package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserRightClickToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserRightClickToolUseBlock =
            BetaBrowserRightClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserRightClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserRightClickToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserRightClickToolUseBlock.input())
            .isEqualTo(
                BetaBrowserRightClickInput.builder()
                    .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                    .modifiers("modifiers")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserRightClickToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserRightClickToolUseBlock =
            BetaBrowserRightClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserRightClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserRightClickToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserRightClickToolUseBlock),
                jacksonTypeRef<BetaBrowserRightClickToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserRightClickToolUseBlock)
            .isEqualTo(betaBrowserRightClickToolUseBlock)
    }
}
